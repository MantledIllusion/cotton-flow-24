package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;

/**
 * {@link ConfigurationBuilder} for {@link Anchor}s.
 */
public class AnchorBuilder extends AbstractHtmlContainerBuilder<Anchor, AnchorBuilder> {

    private AnchorBuilder() {
        super(Anchor::new);
    }

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static AnchorBuilder create() {
        return new AnchorBuilder();
    }
}
