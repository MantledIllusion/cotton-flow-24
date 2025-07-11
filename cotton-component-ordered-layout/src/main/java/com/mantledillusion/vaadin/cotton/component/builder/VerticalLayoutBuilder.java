package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

/**
 * {@link ConfigurationBuilder} for {@link VerticalLayout}s.
 */
public class VerticalLayoutBuilder extends AbstractVerticalLayoutBuilder<VerticalLayout, VerticalLayoutBuilder> {

    private VerticalLayoutBuilder() {}

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static VerticalLayoutBuilder create() {
        return new VerticalLayoutBuilder();
    }

    @Override
    protected VerticalLayout instantiate() {
        return new VerticalLayout();
    }

}
