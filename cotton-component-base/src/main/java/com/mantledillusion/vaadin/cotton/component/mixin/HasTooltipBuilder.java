package com.mantledillusion.vaadin.cotton.component.mixin;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.i18n.I18N;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.shared.HasTooltip;

/**
 * {@link ConfigurationBuilder} for {@link HasTooltip} implementing {@link Component}s.
 *
 * @param <C> The {@link Component} type implementing {@link HasTooltip}.
 * @param <B> The final implementation type of {@link HasTooltipBuilder}.
 */
public interface HasTooltipBuilder<C extends HasTooltip, B extends HasTooltipBuilder<C, B>> extends
        HasElementBuilder<C, B> {

    /**
     * Builder method, sets the {@link HasTooltip}'s text.
     *
     * @see HasTooltip#setTooltipText(String) (Component)
     * @param text The text to set; might be null.
     * @return this
     */
    default B setTooltip(String text) {
        return configure(hasValue -> hasValue.setTooltipText(text));
    }
}
