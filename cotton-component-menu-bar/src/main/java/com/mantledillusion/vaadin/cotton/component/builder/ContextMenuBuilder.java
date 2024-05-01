package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.contextmenu.ContextMenu;

/**
 * {@link ConfigurationBuilder} for {@link ContextMenu}s.
 */
public class ContextMenuBuilder extends AbstractComponentBuilder<ContextMenu, ContextMenuBuilder> implements
        HasElementBuilder<ContextMenu, ContextMenuBuilder>,
        HasMenuItemBuilder<ContextMenu, ContextMenuBuilder>,
        HasStyleBuilder<ContextMenu, ContextMenuBuilder> {

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

    /**
     * Builder method, configures the target component of the {@link ContextMenu}.
     *
     * @see ContextMenu#setTarget(Component)
     * @param target
     *            The target component for the context menu; might <b>not</b> be null.
     * @return this
     */
    public ContextMenuBuilder setTarget(Component target) {
        return configure(menuBar -> menuBar.setTarget(target));
    }
}
