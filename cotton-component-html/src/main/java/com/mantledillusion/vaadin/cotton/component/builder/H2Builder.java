package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.html.H2;

/**
 * {@link ConfigurationBuilder} for {@link H2}.
 */
public class H2Builder extends AbstractClickableHtmlContainerBuilder<H2, H2Builder> {

    private H2Builder() {
        super(H2::new);
    }

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static H2Builder create() {
        return new H2Builder();
    }
}
