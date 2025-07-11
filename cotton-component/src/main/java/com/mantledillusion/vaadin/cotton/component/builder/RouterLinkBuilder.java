package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.router.*;

/**
 * {@link ConfigurationBuilder} for {@link RouterLink}s.
 */
public class RouterLinkBuilder extends AbstractRouterLinkBuilder<RouterLink, RouterLinkBuilder> {

    private RouterLinkBuilder() {}

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static RouterLinkBuilder create() {
        return new RouterLinkBuilder();
    }

    @Override
    protected RouterLink instantiate() {
        return new RouterLink();
    }

}
