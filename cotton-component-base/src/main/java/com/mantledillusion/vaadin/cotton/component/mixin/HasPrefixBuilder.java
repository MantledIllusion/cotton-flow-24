package com.mantledillusion.vaadin.cotton.component.mixin;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.shared.HasPrefix;

/**
 * {@link ConfigurationBuilder} for {@link HasPrefix} implementing {@link Component}s.
 *
 * @param <C> The {@link Component} type implementing {@link HasPrefix}.
 * @param <B> The final implementation type of {@link HasPrefixBuilder}.
 */
public interface HasPrefixBuilder<C extends HasPrefix, B extends HasPrefixBuilder<C, B>> extends
        HasElementBuilder<C, B> {

    /**
     * Builder method, sets the {@link HasPrefix}'s component.
     *
     * @see HasPrefix#setPrefixComponent(Component)
     * @return this
     */
    default B setPrefixComponent(Component prefixComponent) {
        return configure(hasPrefix -> hasPrefix.setPrefixComponent(prefixComponent));
    }
}
