package com.mantledillusion.vaadin.cotton.component.mixin;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasPlaceholder;

/**
 * {@link ConfigurationBuilder} for {@link HasPlaceholder} implementing {@link Component}s.
 *
 * @param <C> The {@link Component} type implementing {@link HasPlaceholder}.
 * @param <B> The final implementation type of {@link HasPlaceholderBuilder}.
 */
public interface HasPlaceholderBuilder<C extends HasPlaceholder, B extends HasPlaceholderBuilder<C, B>> extends
        HasElementBuilder<C, B> {

    /**
     * Builder method, configures the placeholder text that might be displayed when nothing is selected.
     *
     * @see HasPlaceholder#setPlaceholder(String)
     * @param placeholder The placeholder to set; might be null.
     * @return this
     */
    default B setPlaceholder(String placeholder) {
        return configure(datePicker -> datePicker.setPlaceholder(placeholder));
    }
}
