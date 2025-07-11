package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.tabs.Tabs;


/**
 * {@link ConfigurationBuilder} for {@link Tabs}.
 */
public class TabsBuilder extends AbstractTabsBuilder<Tabs, TabsBuilder> {

    private TabsBuilder() {}

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static TabsBuilder create() {
        return new TabsBuilder();
    }

    @Override
    protected Tabs instantiate() {
        return new Tabs();
    }

}
