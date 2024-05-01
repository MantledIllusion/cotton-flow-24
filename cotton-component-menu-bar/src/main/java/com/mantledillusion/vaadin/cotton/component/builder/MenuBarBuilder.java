package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;

/**
 * {@link ConfigurationBuilder} for {@link MenuBar}s.
 */
public class MenuBarBuilder extends AbstractComponentBuilder<MenuBar, MenuBarBuilder> implements
        HasElementBuilder<MenuBar, MenuBarBuilder>,
        HasMenuItemBuilder<MenuBar, MenuBarBuilder>,
        HasSizeBuilder<MenuBar, MenuBarBuilder>,
        HasStyleBuilder<MenuBar, MenuBarBuilder>,
        HasThemeVariantBuilder<MenuBar, MenuBarVariant, MenuBarBuilder> {

    private MenuBarBuilder() {}

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static MenuBarBuilder create() {
        return new MenuBarBuilder();
    }

    @Override
    protected MenuBar instantiate() {
        return new MenuBar();
    }

    /**
     * Builder method, configures if items with sub-items of the {@link MenuBar} should open upon mouse hover.
     *
     * @see MenuBar#setOpenOnHover(boolean)
     * @param openOnHover
     *            True if the items should open on hover, false otherwise.
     * @return this
     */
    public MenuBarBuilder setOpenOnHover(boolean openOnHover) {
        return configure(menuBar -> menuBar.setOpenOnHover(openOnHover));
    }
}
