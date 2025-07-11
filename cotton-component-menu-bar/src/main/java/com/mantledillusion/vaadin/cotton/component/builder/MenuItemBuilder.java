package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.ConfigurationCustomizer;
import com.mantledillusion.vaadin.cotton.component.Configurer;
import com.mantledillusion.vaadin.cotton.component.mixin.ClickNotifierBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.HasComponentsBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.HasEnabledBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.HasMenuItemBuilder;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.contextmenu.HasMenuItems;
import com.vaadin.flow.component.contextmenu.MenuItem;

import java.util.function.Function;

/**
 * {@link ConfigurationBuilder} for {@link MenuItem}s.
 *
 * @param <PC> The parent menu component type of the builder's {@link MenuItem}.
 */
public class MenuItemBuilder<PC> extends AbstractConfigurationBuilder<MenuItem, MenuItemBuilder<PC>> implements
        Configurer<PC>,
        ClickNotifierBuilder<MenuItem, MenuItem, MenuItemBuilder<PC>>,
        HasEnabledBuilder<MenuItem, MenuItemBuilder<PC>>,
        HasComponentsBuilder<MenuItem, MenuItemBuilder<PC>> {

    private final Function<PC, MenuItem> itemSupplier;

    private MenuItemBuilder(Function<PC, MenuItem> itemSupplier) {
        this.itemSupplier = itemSupplier;
    }

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static <PC extends HasMenuItems, PB extends HasMenuItemBuilder<PC, PB>> MenuItemBuilder<PC> create(Function<PC, MenuItem> itemSupplier) {
        return new MenuItemBuilder<>(itemSupplier);
    }

    @Override
    public void configure(PC component) {
        apply(this.itemSupplier.apply(component));
    }

    /**
     * Builder method, configures the {@link MenuItem} to be toggleable.
     *
     * @param checkable True if the item should be toggleable, false otherwise.
     * @return this
     * @see MenuItem#setCheckable(boolean)
     */
    public MenuItemBuilder<PC> setCheckable(boolean checkable) {
        return configure(menuItem -> menuItem.setCheckable(checkable));
    }

    /**
     * Builder method, configures the {@link MenuItem} to be toggled on.
     * <p>
     * Requires {@link #setCheckable(boolean)} to be called with true.
     *
     * @param checked True if the item should be toggled on, false otherwise.
     * @return this
     * @see MenuItem#setChecked(boolean)
     */
    public MenuItemBuilder<PC> setChecked(boolean checked) {
        return configure(menuItem -> menuItem.setChecked(checked));
    }

    /**
     * Builder method, configures a new listener for {@link ClickEvent}s.
     *
     * @param listener The listener to add; might <b>not</b> be null.
     * @return this
     * @see MenuItem#addClickListener(ComponentEventListener)
     */
    public MenuItemBuilder<PC> addClickListener(ComponentEventListener<ClickEvent<MenuItem>> listener) {
        return configure(menuItem -> menuItem.addClickListener(listener));
    }

    /**
     * Builder method, configures a key combination to trigger a {@link ClickEvent}.
     *
     * @param key          The key to react to; might <b>not</b> be null.
     * @param keyModifiers The modifiers that have to be applied; might be null.
     * @return this
     * @see MenuItem#addClickShortcut(Key, KeyModifier...)
     */
    public MenuItemBuilder<PC> addClickShortcut(Key key, KeyModifier... keyModifiers) {
        return configure(menuItem -> menuItem.addClickShortcut(key, keyModifiers));
    }

    /**
     * Builder method, configures a new {@link MenuItem}.
     *
     * @see com.vaadin.flow.component.contextmenu.SubMenu#addItem(String)
     * @param text The text to set; might be null.
     * @return A new {@link MenuItemBuilder}, never null
     */
    public MenuItemBuilder<PC> addItem(String text) {
        return addItem(text, builder -> {});
    }

    /**
     * Builder method, configures a new {@link MenuItem}.
     *
     * @see com.vaadin.flow.component.contextmenu.SubMenu#addItem(String)
     * @param text The text to set; might be null.
     * @param customizer A {@link ConfigurationCustomizer} for the {@link MenuItemBuilder}; might <b>not</b> be null.
     * @return A new {@link MenuItemBuilder}, never null
     */
    public MenuItemBuilder<PC> addItem(String text, ConfigurationCustomizer<MenuItemBuilder<MenuItem>> customizer) {
        if (customizer == null) {
            throw new IllegalArgumentException("Cannot add an item using a null customizer.");
        }
        MenuItemBuilder<MenuItem> itemBuilder = new MenuItemBuilder<>( menuItem ->
                menuItem.getSubMenu().addItem(text));
        customizer.customize(itemBuilder);
        return configure(itemBuilder);
    }

    /**
     * Builder method, configures a new {@link MenuItem}.
     *
     * @see com.vaadin.flow.component.contextmenu.SubMenu#addItem(Component)
     * @param component The MenuItemBuilder to use as the item; might <b>not</b> be null.
     * @return this
     */
    public MenuItemBuilder<PC> addItem(Component component) {
        return addItem(component, builder -> {});
    }

    /**
     * Builder method, configures a new {@link MenuItem}.
     *
     * @see com.vaadin.flow.component.contextmenu.SubMenu#addItem(Component)
     * @param component The MenuItemBuilder to use as the item; might <b>not</b> be null.
     * @param customizer A {@link ConfigurationCustomizer} for the {@link MenuItemBuilder}; might <b>not</b> be null.
     * @return this
     */
    public MenuItemBuilder<PC> addItem(Component component, ConfigurationCustomizer<MenuItemBuilder<MenuItem>> customizer) {
        if (customizer == null) {
            throw new IllegalArgumentException("Cannot add an item using a null customizer.");
        }
        MenuItemBuilder<MenuItem> itemBuilder = new MenuItemBuilder<>(menuItem ->
                menuItem.getSubMenu().addItem(component));
        customizer.customize(itemBuilder);
        return configure(itemBuilder);
    }
}
