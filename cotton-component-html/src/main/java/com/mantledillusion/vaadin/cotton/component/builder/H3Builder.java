package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.html.H3;

/**
 * {@link ConfigurationBuilder} for {@link H3}.
 */
public class H3Builder extends AbstractClickableHtmlContainerBuilder<H3, H3Builder> {

    private H3Builder() {
        super(H3::new);
    }

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static H3Builder create() {
        return new H3Builder();
    }
}
