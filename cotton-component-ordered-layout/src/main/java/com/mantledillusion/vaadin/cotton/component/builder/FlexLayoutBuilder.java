package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.orderedlayout.FlexLayout;

/**
 * {@link ConfigurationBuilder} for {@link FlexLayout}s.
 */
public class FlexLayoutBuilder extends AbstractFlexLayoutBuilder<FlexLayout, FlexLayoutBuilder> {

    private FlexLayoutBuilder() {}

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static FlexLayoutBuilder create() {
        return new FlexLayoutBuilder();
    }

    @Override
    protected FlexLayout instantiate() {
        return new FlexLayout();
    }

}
