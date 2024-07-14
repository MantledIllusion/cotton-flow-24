package com.mantledillusion.vaadin.cotton.component.mixin;

import com.mantledillusion.essentials.expression.Expression;
import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.Configurer;
import com.mantledillusion.vaadin.cotton.model.AuditingConfigurer;
import com.mantledillusion.vaadin.cotton.model.Binding;
import com.vaadin.flow.component.Component;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Builder to configure a {@link Binding} of a {@link Component} being build with with.
 *
 * @param <C> The bound component type.
 */
public class BindingBuilder<C, V, B extends ConfigurationBuilder<C, B>> implements Configurer<C>, AuditingConfigurer<BindingBuilder<C, V, B>> {

    private final B builder;
    private final Function<C, Binding<V>> bindingCallback;

    private final List<Triple<Binding.AccessMode, Boolean, Expression<String>>> bindingAuditors = new ArrayList<>();
    private V maskedValue;

    BindingBuilder(B builder, Function<C, Binding<V>> bindingCallback) {
        this.builder = builder;
        this.bindingCallback = bindingCallback;
    }

    @Override
    public void configure(C component) {
        Binding<V> binding = this.bindingCallback.apply(component)
                .withMaskedValue(this.maskedValue);
        this.bindingAuditors.forEach(auditor -> binding.setAudit(auditor.getLeft(), auditor.getMiddle(), auditor.getRight()));
    }

    @Override
    public BindingBuilder<C, V, B> setAudit(Binding.AccessMode mode, boolean requiresLogin, Expression<String> rightExpression) {
        this.bindingAuditors.add(Triple.of(mode, requiresLogin, rightExpression));
        return this;
    }

    /**
     * Builder method, sets the value to use when the {@link Binding} is {@link Binding.AccessMode#MASKED}.
     * <p>
     * Note that different types of {@link Binding} implementations might handle using this value differently.
     *
     * @param maskedValue The value to use instead of the bound one; might be null.
     * @return this
     */
    public BindingBuilder<C, V, B> setMaskedValue(V maskedValue) {
        this.maskedValue = maskedValue;
        return this;
    }

    /**
     * Finalizes binding the {@link Component} of the parent {@link ConfigurationBuilder}.
     *
     * @return The parent {@link ConfigurationBuilder} instance, never null
     */
    public B bind() {
        return this.builder.configure(this);
    }
}
