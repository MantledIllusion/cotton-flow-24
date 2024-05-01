package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.html.H5;

/**
 * {@link ConfigurationBuilder} for {@link H5}.
 */
public class H5Builder extends AbstractClickableHtmlContainerBuilder<H5, H5Builder> {

    private H5Builder() {
        super(H5::new);
    }

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static H5Builder create() {
        return new H5Builder();
    }
}
