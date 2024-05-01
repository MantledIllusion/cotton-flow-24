package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.html.H4;

/**
 * {@link ConfigurationBuilder} for {@link H4}.
 */
public class H4Builder extends AbstractClickableHtmlContainerBuilder<H4, H4Builder> {

    private H4Builder() {
        super(H4::new);
    }

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static H4Builder create() {
        return new H4Builder();
    }
}
