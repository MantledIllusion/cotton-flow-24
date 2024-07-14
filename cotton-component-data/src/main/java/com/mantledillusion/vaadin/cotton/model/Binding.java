package com.mantledillusion.vaadin.cotton.model;

import com.mantledillusion.data.epiphy.context.Context;
import com.mantledillusion.essentials.expression.Expression;
import com.vaadin.flow.shared.Registration;

import java.util.function.Consumer;

/**
 * Represents the configurable binding of a property.
 */
public abstract class Binding<FieldValueType> implements AuditingConfigurer<Binding<FieldValueType>> {

    /**
     * Represents the modes a {@link Binding} can allow or restrict access of a properties' data from.
     */
    public enum AuditMode {

        /**
         * Allow access completely using {@link AccessMode#READ_WRITE}, any further auditing will limit access.
         */
        GENEROUS(AccessMode.READ_WRITE),

        /**
         * Limit access completely using {@link AccessMode#HIDDEN}, any further auditing will allow access.
         */
        RESTRICTIVE(AccessMode.HIDDEN);

        private final AccessMode defaultAccessMode;

        AuditMode(AccessMode defaultAccessMode) {
            this.defaultAccessMode = defaultAccessMode;
        }

        public AccessMode getDefaultAccessMode() {
            return defaultAccessMode;
        }

        AccessMode reduce(AccessMode mode1, AccessMode mode2) {
            return mode1.ordinal() < mode2.ordinal() ? (this == GENEROUS ? mode2 : mode1) : (this == GENEROUS ? mode1 : mode2);
        }
    }

    /**
     * Represents the modes a {@link Binding} can allow or limit access of a properties' data with.
     */
    public enum AccessMode {

        /**
         * Full access to the property, reading and writing.
         */
        READ_WRITE(true),

        /**
         * Reading access on the property only, no writing.
         */
        READ_ONLY(true),

        /**
         * Neither reading nor writing access, but the properties' existence is not denied.
         */
        MASKED(false),

        /**
         * Neither reading nor writing access, even the properties' existence is denied.
         */
        HIDDEN(false);

        private final boolean coupled;

        AccessMode(boolean coupled) {
            this.coupled = coupled;
        }
    }

    enum UpdateType {
        EXCHANGE, ADD, REMOVE;
    }

    enum UpdateDirection {
        PARENT(true, false),
        SELF(true, true),
        CHILD(false, true);

        public final boolean isUpStream;
        public final boolean isDownStream;

        UpdateDirection(boolean isUpStream, boolean isDownStream) {
            this.isUpStream = isUpStream;
            this.isDownStream = isDownStream;
        }
    }

    private final Auditor bindingAuditor;
    private final Consumer<Binding<FieldValueType>> registration;
    private AccessMode accessMode;
    private FieldValueType maskedValue;

    Binding(Auditor baseAuditor, Consumer<Binding<FieldValueType>> registration) {
        this.bindingAuditor = new Auditor(baseAuditor);
        this.registration = registration;
    }

    /**
     * Returns this {@link Binding}'s {@link AccessMode}.
     *
     * @return The {@link AccessMode}, never null
     */
    protected final AccessMode getAccessMode() {
        return this.accessMode;
    }

    /**
     * Refresh the {@link Binding}'s {@link AccessMode} using {@link Auditor#audit()}.
     */
    protected final void refreshAccessMode() {
        this.accessMode = this.bindingAuditor.audit();
        accessModeChanged(this.accessMode.coupled);
    }

    @Override
    public Binding<FieldValueType> setAudit(AccessMode mode, boolean requiresLogin, Expression<String> roleExpression) {
        this.bindingAuditor.set(mode, requiresLogin, roleExpression);
        refreshAccessMode();
        return this;
    }

    /**
     * Returns the value used by this {@link Binding} when {@link AccessMode#MASKED} is applied.
     *
     * @return The masked value, might be null
     */
    protected FieldValueType getMaskedValue() {
        return maskedValue;
    }

    /**
     * Builder method, sets the value to use when the {@link Binding} is {@link AccessMode#MASKED}.
     * <p>
     * Note that different types of {@link Binding} implementations might handle using this value differently.
     *
     * @param maskedValue The value to use instead of the bound one; might be null.
     * @return this
     */
    public final Binding<FieldValueType> withMaskedValue(FieldValueType maskedValue) {
        this.maskedValue = maskedValue;
        refreshAccessMode();
        return this;
    }

    /**
     * Unbinds this {@link Binding} from its {@link ModelContainer}.
     */
    public void unbind() {
        this.registration.accept(this);
    }

    void accessModeChanged(boolean couple) {

    }

    abstract void valueChanged(Context context, UpdateType type, UpdateDirection direction);
}
