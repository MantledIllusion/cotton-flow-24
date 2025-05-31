package com.mantledillusion.vaadin.cotton.component.mixin;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasLabel;

/**
 * {@link ConfigurationBuilder} for {@link HasLabel} implementing {@link Component}s.
 *
 * @param <C>
 *            The {@link Component} type implementing {@link HasLabel}.
 * @param <B>
 *            The final implementation type of {@link HasLabelBuilder}.
 */
public interface HasLabelBuilder<C extends HasLabel, B extends HasLabelBuilder<C, B>> extends
        HasElementBuilder<C, B> {

    /**
     * Builder method, sets the {@link HasLabel}s label.
     *
     * @see HasLabel#setLabel(String)
     * @param label The label to set; might be null.
     * @return this
     */
    default B setLabel(String label) {
        return configure(hasLabel -> hasLabel.setLabel(label));
    }
}
