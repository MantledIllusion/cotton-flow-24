package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.mixin.HasSizeBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.HasStyleBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.HasThemeVariantBuilder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.ScrollerVariant;

public abstract class AbstractScrollerBuilder<C extends Scroller, B extends AbstractScrollerBuilder<C, B>> extends AbstractComponentBuilder<C, B> implements
        HasSizeBuilder<C, B>,
        HasStyleBuilder<C, B>,
        HasThemeVariantBuilder<C, ScrollerVariant, B> {

    /**
     * Builder method, sets the content.
     *
     * @param content The content to set; might be null.
     * @return this
     * @see Scroller#setContent(Component)
     */
    public B setContent(Component content) {
        return configure(scroller -> scroller.setContent(content));
    }

    /**
     * Builder method, sets the direction in which to scroll the content.
     *
     * @param scrollDirection The direction to set; might <b>not</b> be null.
     * @return this
     * @see Scroller#setScrollDirection(Scroller.ScrollDirection)
     */
    public B setScrollDirection(Scroller.ScrollDirection scrollDirection) {
        return configure(scroller -> scroller.setScrollDirection(scrollDirection));
    }
}
