package com.mantledillusion.vaadin.cotton.component.mixin;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.data.binder.HasValidator;
import com.vaadin.flow.data.binder.ValidationStatusChangeListener;

/**
 * {@link ConfigurationBuilder} for {@link HasValidatorBuilder} implementing {@link Component}s.
 *
 * @param <C>
 *            The {@link Component} type implementing {@link HasValidatorBuilder}.
 * @param <T>
 *            The value type of the {@link HasValidatorBuilder}.
 * @param <B>
 *            The final implementation type of {@link HasValidatorBuilder}.
 */
public interface HasValidatorBuilder<C extends HasValidator<T>, T, B extends HasValidatorBuilder<C, T, B>> extends
        ConfigurationBuilder<C, B> {

    /**
     * Builder method, adds a {@link ValidationStatusChangeListener}.
     *
     * @see HasValidator#addValidationStatusChangeListener(ValidationStatusChangeListener)
     * @param listener
     *            The element {@link ValidationStatusChangeListener} to add; might <b>not</b> be null.
     * @return this
     */
    default B addValidationStatusChangeListener(ValidationStatusChangeListener<T> listener) {
        return configure(hasValidator -> hasValidator.addValidationStatusChangeListener(listener));
    }
}
