package com.mantledillusion.vaadin.cotton.component.mixin;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasTheme;

/**
 * {@link ConfigurationBuilder} for {@link HasTheme} implementing {@link Component}s.
 *
 * @param <C>
 *            The {@link Component} type implementing {@link HasTheme}.
 * @param <B>
 *            The final implementation type of {@link HasThemeBuilder}.
 */
public interface HasThemeBuilder<C extends HasTheme, B extends HasThemeBuilder<C, B>> extends
        HasElementBuilder<C, B> {

    /**
     * Builder method, add the given theme name to the {@link Component}.
     *
     * @see HasTheme#addThemeName(String)
     * @param themeName The theme name to add; might <b>not</b> be null.
     * @return this
     */
    default B addThemeName(String themeName) {
        return configure(hasTheme -> hasTheme.addThemeName(themeName));
    }

    /**
     * Builder method, add the given theme names to the {@link Component}.
     *
     * @see HasTheme#addThemeNames(String...)
     * @param themeName The theme names to add; might <b>not</b> be null.
     * @return this
     */
    default B addThemeNames(String... themeName) {
        return configure(hasTheme -> hasTheme.addThemeNames(themeName));
    }

    /**
     * Builder method, removes the given theme name from the {@link Component}.
     *
     * @see HasTheme#removeThemeName(String)
     * @param themeName The theme name to remove; might <b>not</b> be null.
     * @return this
     */
    default B removeThemeName(String themeName) {
        return configure(hasTheme -> hasTheme.removeThemeName(themeName));
    }

    /**
     * Builder method, removes the given theme names from the {@link Component}.
     *
     * @see HasTheme#removeThemeNames(String...)
     * @param themeName The theme names to remove; might <b>not</b> be null.
     * @return this
     */
    default B removeThemeNames(String... themeName) {
        return configure(hasTheme -> hasTheme.removeThemeNames(themeName));
    }

    /**
     * Builder method, sets the given theme name from the {@link Component}.
     *
     * @see HasTheme#setThemeName(String)
     * @param themeName The theme name to set; might <b>not</b> be null.
     * @return this
     */
    default B setThemeName(String themeName) {
        return configure(hasTheme -> hasTheme.setThemeName(themeName));
    }
}
