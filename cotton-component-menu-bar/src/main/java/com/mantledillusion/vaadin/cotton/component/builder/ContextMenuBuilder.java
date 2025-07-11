package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.contextmenu.ContextMenu;


/**
 * {@link ConfigurationBuilder} for {@link ContextMenu}s.
 */
public class ContextMenuBuilder extends AbstractContextMenuBuilder<ContextMenu, ContextMenuBuilder> {

    private ContextMenuBuilder() {}

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static ContextMenuBuilder create() {
        return new ContextMenuBuilder();
    }

    @Override
    protected ContextMenu instantiate() {
        return new ContextMenu();
    }

}
