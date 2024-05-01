package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.html.H1;

/**
 * {@link ConfigurationBuilder} for {@link H1}.
 */
public class H1Builder extends AbstractClickableHtmlContainerBuilder<H1, H1Builder> {

    private H1Builder() {
        super(H1::new);
    }

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static H1Builder create() {
        return new H1Builder();
    }
}
