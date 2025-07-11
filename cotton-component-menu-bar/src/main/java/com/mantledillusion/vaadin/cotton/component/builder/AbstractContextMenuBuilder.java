package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.mixin.HasElementBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.HasMenuItemBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.HasStyleBuilder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.contextmenu.ContextMenu;

public abstract class AbstractContextMenuBuilder<C extends ContextMenu, B extends AbstractContextMenuBuilder<C, B>> extends AbstractComponentBuilder<C, B> implements
        HasElementBuilder<C, B>,
        HasMenuItemBuilder<C, B>,
        HasStyleBuilder<C, B> {

    /**
     * Builder method, configures the target component of the {@link ContextMenu}.
     *
     * @param target The target component for the context menu; might <b>not</b> be null.
     * @return this
     * @see ContextMenu#setTarget(Component)
     */
    public B setTarget(Component target) {
        return configure(menuBar -> menuBar.setTarget(target));
    }
}
