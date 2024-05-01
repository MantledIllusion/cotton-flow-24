package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.HtmlContainer;

/**
 * Initializer for {@link ConfigurationBuilder}s of {@link HtmlContainer} implementations.
 */
public class HtmlContainerBuilder {

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static AnchorBuilder createAnchor() {
        return AnchorBuilder.create();
    }

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static H1Builder createH1() {
        return H1Builder.create();
    }

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static H2Builder createH2() {
        return H2Builder.create();
    }

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static H3Builder createH3() {
        return H3Builder.create();
    }

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static H4Builder createH4() {
        return H4Builder.create();
    }

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static H5Builder createH5() {
        return H5Builder.create();
    }

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static H6Builder createH6() {
        return H6Builder.create();
    }

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static HtmlLabelBuilder createLabel() {
        return HtmlLabelBuilder.create();
    }
}
