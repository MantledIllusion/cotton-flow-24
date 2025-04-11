package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.ConfigurationCustomizer;
import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;

/**
 * {@link ConfigurationBuilder} for {@link Tabs}.
 */
public class TabsBuilder extends AbstractComponentBuilder<Tabs, TabsBuilder> implements
        HasElementBuilder<Tabs, TabsBuilder>,
        HasEnabledBuilder<Tabs, TabsBuilder>,
        HasSizeBuilder<Tabs, TabsBuilder>,
        HasStyleBuilder<Tabs, TabsBuilder>,
        HasThemeVariantBuilder<Tabs, TabsVariant, TabsBuilder> {

    /**
     * {@link ConfigurationBuilder} for {@link Tab}s.
     */
    public class TabBuilder extends AbstractTabBuilder<Tabs, TabBuilder> {

        private TabBuilder() {
            super(TabsBuilder.this, Tabs.class, Tabs::add, Tabs::setSelectedTab);
        }

        /**
         * Builder method, configures a new {@link ComponentEventListener} for {@link Tabs.SelectedChangeEvent}s when a
         * tab that is currently being configured {@link Tab} is selected.
         *
         * @see Tabs#addSelectedChangeListener(ComponentEventListener)
         * @param listener The listener to add; might <b>not</b> be null.
         * @return this
         */
        public TabBuilder addSelectedChangeListenerForTab(ComponentEventListener<Tabs.SelectedChangeEvent> listener) {
            return configure(tab -> TabBuilder.this.get(Tabs.class).addSelectedChangeListener(event -> {
                if (tab == event.getSelectedTab()) {
                    listener.onComponentEvent(event);
                }
            }));
        }
    }

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

    /**
     * Builder method, configures the {@link Component}'s {@link Tab}s.
     *
     * @see Tabs#add(Tab...)
     * @param tabs The tabs to enclose; might be null, might <b>not</b> contain nulls.
     * @return this
     */
    public TabsBuilder addTabs(Tab... tabs) {
        return configure(tabsComponent -> tabsComponent.add(tabs));
    }

    /**
     * Builder method, configures a new {@link Tab}.
     *
     * @see Tabs#add(Tab...)
     * @param customizer A {@link ConfigurationCustomizer} for the {@link TabBuilder}; might <b>not</b> be null.
     * @return this
     */
    public TabsBuilder addTab(ConfigurationCustomizer<TabBuilder> customizer) {
        if (customizer == null) {
            throw new IllegalArgumentException("Cannot add a tab using a null customizer.");
        }
        TabBuilder tabBuilder = new TabBuilder();
        customizer.customize(tabBuilder);
        return configure(tabBuilder);
    }

    /**
     * Builder method, configures a new {@link Tab}.
     *
     * @see Tabs#add(Tab...)
     * @param customizer A {@link ConfigurationCustomizer} for the {@link TabBuilder}; might <b>not</b> be null.
     * @param content The {@link Component} the new {@link Tab} is being configured for; will automatically call
     *                {@link Component#setVisible(boolean)} depending on whether the {@link Tab} is being (de)selected.
     * @return this
     */
    public TabsBuilder addTab(ConfigurationCustomizer<TabBuilder> customizer, Component content) {
        if (customizer == null) {
            throw new IllegalArgumentException("Cannot add a tab using a null customizer.");
        }
        TabBuilder tabBuilder = new TabBuilder();
        customizer.customize(tabBuilder);
        configure(tabBuilder);
        tabBuilder.configure(tab -> get(Tabs.class).addSelectedChangeListener(event -> content.setVisible(event.getSelectedTab() == tab)));
        return this;
    }

    /**
     * Builder method, configures the {@link Component}'s selected {@link Tab}.
     *
     * @see Tabs#setSelectedIndex(int)
     * @param selectedIndex
     *            the zero-based index of the selected tab, -1 to unselect all
     * @return this
     */
    public TabsBuilder setSelectedIndex(int selectedIndex) {
        return configure(tabsComponent -> tabsComponent.setSelectedIndex(selectedIndex));
    }

    /**
     * Builder method, configures the {@link Component}'s selected {@link Tab}.
     *
     * @see Tabs#setSelectedTab(Tab)
     * @param selectedTab
     *            the tab to select, {@code null} to unselect all
     * @return this
     */
    public TabsBuilder setSelectedTab(Tab selectedTab) {
        return configure(tabsComponent -> tabsComponent.setSelectedTab(selectedTab));
    }

    /**
     * Builder method, configures the {@link Component}'s {@link Tabs.Orientation}.
     *
     * @see Tabs#setOrientation(Tabs.Orientation)
     * @param orientation
     *            the orientation
     * @return this
     */
    public TabsBuilder setOrientation(Tabs.Orientation orientation) {
        return configure(tabsComponent -> tabsComponent.setOrientation(orientation));
    }

    /**
     * Builder method, configures the {@link Component}'s flex grow for enclosed {@link Tab}s.
     *
     * @see Tabs#setFlexGrowForEnclosedTabs(double)
     * @param flexGrow
     *            the proportion of the available space the enclosed tabs should
     *            take up
     * @return this
     */
    public TabsBuilder setFlexGrowForEnclosedTabs(double flexGrow) {
        return configure(tabsComponent -> tabsComponent.setFlexGrowForEnclosedTabs(flexGrow));
    }

    /**
     * Builder method, configures a new {@link ComponentEventListener} for {@link Tabs.SelectedChangeEvent}s when a
     * different {@link Tab} is selected.
     *
     * @see Tabs#addSelectedChangeListener(ComponentEventListener)
     * @param listener The listener to add; might <b>not</b> be null.
     * @return this
     */
    public TabsBuilder addSelectedChangeListener(ComponentEventListener<Tabs.SelectedChangeEvent> listener) {
        return configure(tabsComponent -> tabsComponent.addSelectedChangeListener(listener));
    }
}
