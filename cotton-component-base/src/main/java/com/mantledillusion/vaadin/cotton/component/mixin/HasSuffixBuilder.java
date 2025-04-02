package com.mantledillusion.vaadin.cotton.component.mixin;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.shared.HasSuffix;

/**
 * {@link ConfigurationBuilder} for {@link HasSuffix} implementing {@link Component}s.
 *
 * @param <C> The {@link Component} type implementing {@link HasSuffix}.
 * @param <B> The final implementation type of {@link HasSuffixBuilder}.
 */
public interface HasSuffixBuilder<C extends HasSuffix, B extends HasSuffixBuilder<C, B>> extends
        HasElementBuilder<C, B> {

    /**
     * Builder method, sets the {@link HasSuffix}'s component.
     *
     * @see HasSuffix#setSuffixComponent(Component)
     * @return this
     */
    default B setSuffixComponent(Component suffixComponent) {
        return configure(hasPrefix -> hasPrefix.setSuffixComponent(suffixComponent));
    }
}
