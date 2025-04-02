package com.mantledillusion.vaadin.cotton.component.mixin;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.shared.HasAutoOpen;

/**
 * {@link ConfigurationBuilder} for {@link HasAutoOpen} implementing {@link Component}s.
 *
 * @param <C> The {@link Component} type implementing {@link HasAutoOpen}.
 * @param <B> The final implementation type of {@link HasAutoOpenBuilder}.
 */
public interface HasAutoOpenBuilder<C extends HasAutoOpen, B extends HasAutoOpenBuilder<C, B>> extends
        HasElementBuilder<C, B> {

    /**
     * Builder method, sets whether {@link HasAutoOpen} should open automatically.
     *
     * @see HasAutoOpen#setAutoOpen(boolean)
     * @return this
     */
    default B setAutoOpen(boolean autoOpen) {
        return configure(hasAutoOpen -> hasAutoOpen.setAutoOpen(autoOpen));
    }
}
