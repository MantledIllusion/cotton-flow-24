package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.Configurer;
import com.mantledillusion.vaadin.cotton.component.mixin.ClickNotifierBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.HasComponentsBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.HasEnabledBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.HasMenuItemBuilder;
import com.mantledillusion.vaadin.cotton.i18n.I18N;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.contextmenu.HasMenuItems;
import com.vaadin.flow.component.contextmenu.MenuItem;

import java.util.function.Function;

/**
 * {@link ConfigurationBuilder} for {@link MenuItem}s.
 */
public class MenuItemBuilder<PC, PB> extends AbstractConfigurationBuilder<MenuItem, MenuItemBuilder<PC, PB>> implements
        Configurer<PC>,
        ClickNotifierBuilder<MenuItem, MenuItemBuilder<PC, PB>>,
        HasEnabledBuilder<MenuItem, MenuItemBuilder<PC, PB>>,
        HasComponentsBuilder<MenuItem, MenuItemBuilder<PC, PB>> {

    private final PB parentBuilder;
    private final Function<PC, MenuItem> itemSupplier;

    private MenuItemBuilder(PB parentBuilder, Function<PC, MenuItem> itemSupplier) {
        this.parentBuilder = parentBuilder;
        this.itemSupplier = itemSupplier;
    }

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static <PC extends HasMenuItems, PB extends HasMenuItemBuilder<PC, PB>> MenuItemBuilder<PC, PB> create(PB parentBuilder, Function<PC, MenuItem> itemSupplier) {
        return new MenuItemBuilder<>(parentBuilder, itemSupplier);
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
    public MenuItemBuilder<PC, PB> setCheckable(boolean checkable) {
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
    public MenuItemBuilder<PC, PB> setChecked(boolean checked) {
        return configure(menuItem -> menuItem.setChecked(checked));
    }

    /**
     * Builder method, configures a new listener for {@link ClickEvent}s.
     *
     * @param listener The listener to add; might <b>not</b> be null.
     * @return this
     * @see MenuItem#addClickListener(ComponentEventListener)
     */
    public MenuItemBuilder<PC, PB> addClickListener(ComponentEventListener<ClickEvent<MenuItem>> listener) {
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
    public MenuItemBuilder<PC, PB> addClickShortcut(Key key, KeyModifier... keyModifiers) {
        return configure(menuItem -> menuItem.addClickShortcut(key, keyModifiers));
    }

    /**
     * Builder method, configures a new {@link MenuItem}.
     *
     * @see com.vaadin.flow.component.contextmenu.SubMenu#addItem(String)
     * @param msgId A text to use on the item or message ID to localize; might be null.
     * @param indexedMessageParameters Optional parameters to replace at their index in the message; might be null.
     * @return A new {@link MenuItemBuilder}, never null
     */
    public MenuItemBuilder<MenuItem, MenuItemBuilder<PC, PB>> configureItem(Object msgId, Object... indexedMessageParameters) {
        MenuItemBuilder<MenuItem, MenuItemBuilder<PC, PB>> itemBuilder = new MenuItemBuilder<>(this, menuItem ->
                menuItem.getSubMenu().addItem(I18N.getTranslation(msgId, indexedMessageParameters)));
        configure(itemBuilder);
        return itemBuilder;
    }

    /**
     * Builder method, configures a new {@link MenuItem}.
     *
     * @param component The MenuItemBuilder to use as the item; might <b>not</b> be null.
     * @return A new {@link MenuItemBuilder}, never null
     * @see com.vaadin.flow.component.contextmenu.SubMenu#addItem(Component)
     */
    public MenuItemBuilder<MenuItem, MenuItemBuilder<PC, PB>> configureItem(Component component) {
        MenuItemBuilder<MenuItem, MenuItemBuilder<PC, PB>> itemBuilder = new MenuItemBuilder<>(this, menuItem ->
                menuItem.getSubMenu().addItem(component));
        configure(itemBuilder);
        return itemBuilder;
    }

    public PB add() {
        return this.parentBuilder;
    }
}
