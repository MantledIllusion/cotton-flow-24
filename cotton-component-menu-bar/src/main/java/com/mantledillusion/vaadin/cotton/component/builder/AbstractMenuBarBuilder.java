package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;

public abstract class AbstractMenuBarBuilder<C extends MenuBar, B extends AbstractMenuBarBuilder<C, B>> extends AbstractComponentBuilder<C, B> implements
        HasElementBuilder<C, B>,
        HasMenuItemBuilder<C, B>,
        HasSizeBuilder<C, B>,
        HasStyleBuilder<C, B>,
        HasThemeVariantBuilder<C, MenuBarVariant, B> {

    /**
     * Builder method, configures if items with sub-items of the {@link MenuBar} should open upon mouse hover.
     *
     * @param openOnHover True if the items should open on hover, false otherwise.
     * @return this
     * @see MenuBar#setOpenOnHover(boolean)
     */
    public B setOpenOnHover(boolean openOnHover) {
        return configure(menuBar -> menuBar.setOpenOnHover(openOnHover));
    }
}
