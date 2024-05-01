package com.mantledillusion.vaadin.cotton.component.mixin;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.shared.HasThemeVariant;
import com.vaadin.flow.component.shared.ThemeVariant;

/**
 * {@link ConfigurationBuilder} for {@link HasThemeVariant} implementing {@link Component}s.
 *
 * @param <C> The {@link Component} type implementing {@link HasThemeVariant}.
 * @param <T> The implementation of {@link ThemeVariant} used by the {@link HasThemeVariant}.
 * @param <B> The final implementation type of {@link HasThemeVariantBuilder}.
 */
public interface HasThemeVariantBuilder<C extends HasThemeVariant<T>, T extends ThemeVariant, B extends HasThemeVariantBuilder<C, T, B>> extends
        HasThemeBuilder<C, B> {

    /**
     * Builder method, adds the theme name of the given variant to the {@link Component}.
     *
     * @see HasThemeVariant#addThemeVariants(ThemeVariant[])
     * @param variants The variants whose theme name to add; might <b>not</b> be null.
     * @return this
     */

    default B addThemeVariants(T... variants) {
        return configure(hasTheme -> hasTheme.addThemeVariants(variants));
    }

    /**
     * Builder method, removes the theme name of the given variant from the {@link Component}.
     *
     * @see HasThemeVariant#removeThemeVariants(ThemeVariant[])
     * @param variants The variants whose theme name to remove; might <b>not</b> be null.
     * @return this
     */
    default B removeThemeVariants(T... variants) {
        return configure(hasTheme -> hasTheme.removeThemeVariants(variants));
    }
}
