package com.mantledillusion.vaadin.cotton.model;

import com.mantledillusion.essentials.expression.Expression;
import com.mantledillusion.vaadin.cotton.auth.Authorization;
import org.apache.commons.lang3.tuple.Pair;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

class Auditor {

    private final Map<Binding.AccessMode, Pair<Boolean, Expression<String>>> audits;
    private Supplier<Binding.AuditMode> auditModeSupplier = () -> Binding.AuditMode.GENEROUS;

    Auditor(Auditor baseAuditor) {
        this.audits = new EnumMap<>(Binding.AccessMode.class);
        if (baseAuditor != null) {
            this.audits.putAll(baseAuditor.audits);
            this.auditModeSupplier = () -> baseAuditor.auditModeSupplier.get();
        }
    }

    void setAuditMode(Binding.AuditMode mode) {
        if (mode == null) {
            throw new IllegalArgumentException("Cannot set a null audit mode");
        }
        this.auditModeSupplier = () -> mode;
    }

    void set(Binding.AccessMode mode, boolean requiresAuthentication, Expression<String> rightExpression) {
        if (mode == null) {
            throw new IllegalArgumentException("Cannot append a null access mode");
        }
        this.audits.put(mode, Pair.of(requiresAuthentication, rightExpression));
    }

    Binding.AccessMode audit() {
        Binding.AuditMode auditMode = this.auditModeSupplier.get();

        return this.audits.entrySet().stream()
                .flatMap(entry -> !entry.getValue().getLeft()
                        || Optional.ofNullable(entry.getValue().getRight())
                            .map(Authorization::isPermitted)
                            .orElse(Authorization.isPermitted())
                                ? Stream.of(entry.getKey())
                                : Stream.empty())
                .reduce(auditMode::reduce)
                .orElse(auditMode.getDefaultAccessMode());
    }
}
