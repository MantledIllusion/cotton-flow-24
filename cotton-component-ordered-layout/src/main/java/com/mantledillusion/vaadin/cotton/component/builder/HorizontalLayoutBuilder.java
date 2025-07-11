package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;


/**
 * {@link ConfigurationBuilder} for {@link HorizontalLayout}s.
 */
public class HorizontalLayoutBuilder extends AbstractHorizontalLayoutBuilder<HorizontalLayout, HorizontalLayoutBuilder> {

    private HorizontalLayoutBuilder() {}

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static HorizontalLayoutBuilder create() {
        return new HorizontalLayoutBuilder();
    }

    @Override
    protected HorizontalLayout instantiate() {
        return new HorizontalLayout();
    }

}
