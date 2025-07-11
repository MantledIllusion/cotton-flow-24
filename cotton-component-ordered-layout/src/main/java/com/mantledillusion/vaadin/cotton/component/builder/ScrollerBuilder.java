package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.orderedlayout.Scroller;


/**
 * {@link ConfigurationBuilder} for {@link Scroller}s.
 */
public class ScrollerBuilder extends AbstractScrollerBuilder<Scroller, ScrollerBuilder> {

    private ScrollerBuilder() {}

    @Override
    protected Scroller instantiate() {
        return new Scroller();
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
