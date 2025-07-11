package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.tabs.TabSheet;


/**
 * {@link ConfigurationBuilder} for {@link TabSheet}.
 */
public class TabSheetBuilder extends AbstractTabSheetBuilder<TabSheet, TabSheetBuilder> {

    private TabSheetBuilder() {}

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static TabSheetBuilder create() {
        return new TabSheetBuilder();
    }

    @Override
    protected TabSheet instantiate() {
        return new TabSheet();
    }

}
