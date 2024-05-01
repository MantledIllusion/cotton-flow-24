package com.mantledillusion.vaadin.cotton.component.mixin;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.server.auth.Authorization;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue;

/**
 * {@link ConfigurationBuilder} for {@link HasValue} implementing {@link Component}s.
 *
 * @param <C>
 *            The {@link Component} type implementing {@link HasValue}.
 * @param <V>
 *            The value type of the {@link HasValue}.
 * @param <B>
 *            The final implementation type of {@link HasValueBuilder}.
 */
public interface HasValueBuilder<C extends HasValue<E, V>, V, E extends HasValue.ValueChangeEvent<V>, B extends HasValueBuilder<C, V, E, B>>
        extends ConfigurationBuilder<C, B> {

    /**
     * Builder method, configures whether the {@link Component} is read-only, so its value cannot be changed.
     *
     * @see HasValue#setReadOnly(boolean)
     * @param readOnly True if the {@link Component} has to be read-only, false otherwise.
     * @return this
     */
    default B setReadOnly(boolean readOnly) {
        return configure(hasValue -> hasValue.setReadOnly(readOnly));
    }

    /**
     * Builder method, configures the {@link Component} to be read only unless there is a principal in the given roles.
     *
     * @see HasValue#setReadOnly(boolean)
     * @param roles The roles the principal has to be in; might <b>not</b> be null.
     * @return this
     */
    default B setReadOnlyUnlessPermitted(String... roles) {
        return configure(hasValue -> hasValue.setReadOnly(!Authorization.permitted(roles)));
    }

    /**
     * Builder method, configures whether the required indicator should be visible.
     *
     * @see HasValue#setRequiredIndicatorVisible(boolean)
     * @param requiredIndicatorVisible True if the indicator has to be visible, false otherwise.
     * @return this
     */
    default B setRequiredIndicator(boolean requiredIndicatorVisible) {
        return configure(hasValue -> hasValue.setRequiredIndicatorVisible(requiredIndicatorVisible));
    }

    /**
     * Builder method, configures the initial value of the component after building.
     *
     * @see HasValue#setValue(Object)
     * @param value The initial value; might be null.
     * @return this
     */
    default B setValue(V value) {
        return configure(hasValue -> hasValue.setValue(value));
    }

    /**
     * Builder method, configures a value change listener.
     *
     * @see HasValue#addValueChangeListener(HasValue.ValueChangeListener)
     * @param listener The listener to add; might <b>not</b> be null
     * @return this
     */
    default B addValueChangeListener(HasValue.ValueChangeListener<? super E> listener) {
        return configure(hasValue -> hasValue.addValueChangeListener(listener));
    }
}
