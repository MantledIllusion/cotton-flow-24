package com.mantledillusion.vaadin.cotton.component.mixin;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.ConfigurationCustomizer;
import com.mantledillusion.vaadin.cotton.component.builder.MenuItemBuilder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.contextmenu.HasMenuItems;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.menubar.MenuBar;

/**
 * {@link ConfigurationBuilder} for {@link HasMenuItems} implementing {@link Component}s.
 *
 * @param <C> The {@link Component} type implementing {@link HasMenuItems}.
 * @param <B> The final implementation type of {@link HasMenuItemBuilder}.
 */
@SuppressWarnings("unused")
public interface HasMenuItemBuilder<C extends HasMenuItems, B extends HasMenuItemBuilder<C, B>> extends
        ConfigurationBuilder<C, B>  {

    /**
     * Builder method, configures a new {@link MenuItem}.
     *
     * @see MenuBar#addItem(String)
     * @param text The text to set; might be null.
     * @return this
     */
    default B addItem(String text) {
        return addItem(text, builder -> {});
    }

    /**
     * Builder method, configures a new {@link MenuItem}.
     *
     * @see MenuBar#addItem(String)
     * @param text The text to set; might be null.
     * @param customizer A {@link ConfigurationCustomizer} for the {@link MenuItemBuilder}; might <b>not</b> be null.
     * @return this
     */
    default B addItem(String text, ConfigurationCustomizer<MenuItemBuilder<C>> customizer) {
        if (customizer == null) {
            throw new IllegalArgumentException("Cannot add an item using a null customizer.");
        }
        MenuItemBuilder<C> itemBuilder = MenuItemBuilder.create(hasMenuItems ->
                hasMenuItems.addItem(text, null));
        customizer.customize(itemBuilder);
        return configure(itemBuilder);
    }

    /**
     * Builder method, configures a new {@link MenuItem}.
     *
     * @see MenuBar#addItem(Component)
     * @param component The MenuItemBuilder to use as the item; might <b>not</b> be null.
     * @return this
     */
    default B addItem(Component component) {
        return addItem(component, builder -> {});
    }

    /**
     * Builder method, configures a new {@link MenuItem}.
     *
     * @see MenuBar#addItem(Component)
     * @param component The MenuItemBuilder to use as the item; might <b>not</b> be null.
     * @param customizer A {@link ConfigurationCustomizer} for the {@link MenuItemBuilder}; might <b>not</b> be null.
     * @return this
     */
    default B addItem(Component component, ConfigurationCustomizer<MenuItemBuilder<C>> customizer) {
        if (customizer == null) {
            throw new IllegalArgumentException("Cannot add an item using a null customizer.");
        }
        MenuItemBuilder<C> itemBuilder = MenuItemBuilder.create(hasMenuItems ->
                hasMenuItems.addItem(component, null));
        customizer.customize(itemBuilder);
        return configure(itemBuilder);
    }
}
