package com.mantledillusion.vaadin.cotton.component.mixin;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasAriaLabel;

/**
 * {@link ConfigurationBuilder} for {@link HasAriaLabel} implementing {@link Component}s.
 *
 * @param <C>
 *            The {@link Component} type implementing {@link HasAriaLabel}.
 * @param <B>
 *            The final implementation type of {@link HasAriaLabelBuilder}.
 */
public interface HasAriaLabelBuilder<C extends HasAriaLabel, B extends HasAriaLabelBuilder<C, B>> extends
        HasElementBuilder<C, B> {

    /**
     * Builder method, sets the {@link HasAriaLabel}s aria label.
     *
     * @see HasAriaLabel#setAriaLabel(String) (String)
     * @param ariaLabel The label to set; might be null.
     * @return this
     */
    default B setAriaLabel(String ariaLabel) {
        return configure(hasLabel -> hasLabel.setAriaLabel(ariaLabel));
    }

    /**
     * Builder method, sets the {@link HasAriaLabel}s aria label.
     *
     * @see HasAriaLabel#setAriaLabelledBy(String) (String)
     * @param ariaComponentId The ID of the other aria component to label this component by; might <b>not</b> be null.
     * @return this
     */
    default B setAriaLabelledBy(String ariaComponentId) {
        return configure(hasLabel -> hasLabel.setAriaLabelledBy(ariaComponentId));
    }
}
