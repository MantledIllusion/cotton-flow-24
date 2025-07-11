package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.menubar.MenuBar;


/**
 * {@link ConfigurationBuilder} for {@link MenuBar}s.
 */
public class MenuBarBuilder extends AbstractMenuBarBuilder<MenuBar, MenuBarBuilder> {

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

}
