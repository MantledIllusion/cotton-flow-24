package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.HasSizeBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.HasStyleBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.HasThemeVariantBuilder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.ScrollerVariant;

/**
 * {@link ConfigurationBuilder} for {@link Scroller}s.
 */
public class ScrollerBuilder extends AbstractComponentBuilder<Scroller, ScrollerBuilder> implements
        HasSizeBuilder<Scroller, ScrollerBuilder>,
        HasStyleBuilder<Scroller, ScrollerBuilder>,
        HasThemeVariantBuilder<Scroller, ScrollerVariant, ScrollerBuilder> {

    private ScrollerBuilder() {}

    @Override
    protected Scroller instantiate() {
        return new Scroller();
    }

    /**
     * Builder method, sets the content.
     *
     * @see Scroller#setContent(Component)
     * @param content The content to set; might be null.
     * @return this
     */
    public ScrollerBuilder setContent(Component content) {
        return configure(scroller -> scroller.setContent(content));
    }

    /**
     * Builder method, sets the direction in which to scroll the content.
     *
     * @see Scroller#setScrollDirection(Scroller.ScrollDirection)
     * @param scrollDirection The direction to set; might <b>not</b> be null.
     * @return this
     */
    public ScrollerBuilder setScrollDirection(Scroller.ScrollDirection scrollDirection) {
        return configure(scroller -> scroller.setScrollDirection(scrollDirection));
    }

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static ScrollerBuilder create() {
        return new ScrollerBuilder();
    }
}
