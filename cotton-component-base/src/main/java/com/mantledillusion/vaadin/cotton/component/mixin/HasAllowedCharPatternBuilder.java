package com.mantledillusion.vaadin.cotton.component.mixin;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.shared.HasAllowedCharPattern;

/**
 * {@link ConfigurationBuilder} for {@link HasAllowedCharPattern} implementing {@link Component}s.
 *
 * @param <C> The {@link Component} type implementing {@link HasAllowedCharPattern}.
 * @param <B> The final implementation type of {@link HasAllowedCharPatternBuilder}.
 */
public interface HasAllowedCharPatternBuilder<C extends HasAllowedCharPattern, B extends HasAllowedCharPatternBuilder<C, B>> extends
        HasElementBuilder<C, B> {

    /**
     * Builder method, sets the {@link HasAllowedCharPattern}'s pattern.
     *
     * @see HasAllowedCharPattern#setAllowedCharPattern(String)
     * @param pattern The pattern to set; might be null.
     * @return this
     */
    default B setAllowedCharPattern(String pattern) {
        return configure(hasAllowedCharPattern -> hasAllowedCharPattern.setAllowedCharPattern(pattern));
    }
}
