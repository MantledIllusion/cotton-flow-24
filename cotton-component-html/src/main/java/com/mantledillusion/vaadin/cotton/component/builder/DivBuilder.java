package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;

import com.vaadin.flow.component.html.Div;

/**
 * {@link ConfigurationBuilder} for {@link Div}.
 */
public class DivBuilder extends AbstractClickableHtmlContainerBuilder<Div, DivBuilder> {

    private DivBuilder() {
        super(Div::new);
    }

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static DivBuilder create() {
        return new DivBuilder();
    }
}
