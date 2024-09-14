package com.mantledillusion.vaadin.cotton.component.mixin;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;

import java.util.Collection;

/**
 * {@link ConfigurationBuilder} for {@link HasComponents} implementing {@link Component}s.
 *
 * @param <C>
 *            The {@link Component} type implementing {@link HasComponents}.
 * @param <B>
 *            The final implementation type of {@link HasComponentsBuilder}.
 */
public interface HasComponentsBuilder<C extends HasComponents, B extends HasComponentsBuilder<C, B>> extends
        HasEnabledBuilder<C, B> {

    /**
     * Builder method, configures a child {@link com.vaadin.flow.component.Text} to the {@link Component}.
     *
     * @see HasComponents#add(Component...)
     * @param text The {@link com.vaadin.flow.component.Text} to add; might be null, might <b>not</b> contain nulls.
     * @return this
     */
    default B add(String text) {
        return configure(hasComponents -> hasComponents.add(text));
    }

    /**
     * Builder method, configures the {@link Component}'s child {@link Component}s.
     *
     * @see HasComponents#add(Component...)
     * @param components The {@link Component}s to add; might be null, might <b>not</b> contain nulls.
     * @return this
     */
    default B add(Component... components) {
        return configure(hasComponents -> hasComponents.add(components));
    }

    /**
     * Builder method, configures the {@link Component}'s child {@link Component}s.
     *
     * @see HasComponents#add(Collection)
     * @param components The {@link Component}s to add; might be null, might <b>not</b> contain nulls.
     * @return this
     */
    default B add(Collection<Component> components) {
        return configure(hasComponents -> hasComponents.add(components));
    }

    /**
     * Builder method, configures the {@link Component}'s first child {@link Component}.
     *
     * @see HasComponents#addComponentAsFirst(Component)
     * @param component The {@link Component} to add as first; might <b>not</b> be null.
     * @return this
     */
    default B addComponentAsFirst(Component component) {
        return configure(hasComponents -> hasComponents.addComponentAsFirst(component));
    }

    /**
     * Builder method, configures the {@link Component}'s nth child {@link Component}.
     *
     * @see HasComponents#addComponentAtIndex(int, Component)
     * @param index The index to add the {@link Component} at.
     * @param component The {@link Component} to add; might <b>not</b> be null.
     * @return this
     */
    default B addComponentAtIndex(int index, Component component) {
        return configure(hasComponents -> hasComponents.addComponentAtIndex(index, component));
    }
}
