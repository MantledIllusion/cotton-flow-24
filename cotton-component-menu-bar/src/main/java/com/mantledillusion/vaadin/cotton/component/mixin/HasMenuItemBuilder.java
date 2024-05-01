package com.mantledillusion.vaadin.cotton.component.mixin;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.builder.MenuItemBuilder;
import com.mantledillusion.vaadin.cotton.i18n.I18N;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.contextmenu.HasMenuItems;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.menubar.MenuBar;

/**
 * {@link ConfigurationBuilder} for {@link HasMenuItems} implementing {@link Component}s.
 *
 * @param <C>
 *            The {@link Component} type implementing {@link HasMenuItems}.
 * @param <B>
 *            The final implementation type of {@link HasMenuItemBuilder}.
 */
@SuppressWarnings("unused")
public interface HasMenuItemBuilder<C extends HasMenuItems, B extends HasMenuItemBuilder<C, B>> extends
        ConfigurationBuilder<C, B>  {

    /**
     * Builder method, configures a new {@link MenuItem}.
     *
     * @see MenuBar#addItem(String)
     * @param msgId A text to use on the item or message ID to localize; might be null.
     * @param indexedMessageParameters Optional parameters to replace at their index in the message; might be null.
     * @return A new {@link MenuItemBuilder}, never null
     */
    @SuppressWarnings("unchecked")
    default MenuItemBuilder<C, B> configureItem(Object msgId, Object... indexedMessageParameters) {
        MenuItemBuilder<C, B> itemBuilder = MenuItemBuilder.create((B) this, hasMenuItems ->
                hasMenuItems.addItem(I18N.getTranslation(msgId, indexedMessageParameters), null));
        configure(itemBuilder);
        return itemBuilder;
    }

    /**
     * Builder method, configures a new {@link MenuItem}.
     *
     * @see MenuBar#addItem(String)
     * @param component
     *            The MenuItemBuilder to use as the item; might <b>not</b> be null.
     * @return A new {@link MenuItemBuilder}, never null
     */
    @SuppressWarnings("unchecked")
    default MenuItemBuilder<C, B> configureItem(Component component) {
        MenuItemBuilder<C, B> itemBuilder = MenuItemBuilder.create((B) this, hasMenuItems ->
                hasMenuItems.addItem(component, null));
        configure(itemBuilder);
        return itemBuilder;
    }
}
