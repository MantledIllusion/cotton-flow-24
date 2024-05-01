package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.html.H6;

/**
 * {@link ConfigurationBuilder} for {@link H6}.
 */
public class H6Builder extends AbstractClickableHtmlContainerBuilder<H6, H6Builder> {

    private H6Builder() {
        super(H6::new);
    }

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static H6Builder create() {
        return new H6Builder();
    }
}
