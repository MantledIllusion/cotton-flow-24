package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.ConfigurationCustomizer;
import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;

public abstract class AbstractTabsBuilder<C extends Tabs, B extends AbstractTabsBuilder<C, B>> extends AbstractComponentBuilder<C, B> implements
        HasElementBuilder<C, B>,
        HasEnabledBuilder<C, B>,
        HasSizeBuilder<C, B>,
        HasStyleBuilder<C, B>,
        HasThemeVariantBuilder<C, TabsVariant, B> {

    /**
     * {@link ConfigurationBuilder} for {@link Tab}s.
     */
    public class TabBuilder extends AbstractTabBuilder<C, TabBuilder> {

        @SuppressWarnings("unchecked")
        private TabBuilder() {
            super((B) AbstractTabsBuilder.this, Tabs.class, Tabs::add, Tabs::setSelectedTab);
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

    /**
     * Builder method, configures the {@link Component}'s {@link Tab}s.
     *
     * @param tabs The tabs to enclose; might be null, might <b>not</b> contain nulls.
     * @return this
     * @see Tabs#add(Tab...)
     */
    public B addTabs(Tab... tabs) {
        return configure(tabsComponent -> tabsComponent.add(tabs));
    }

    /**
     * Builder method, configures a new {@link Tab}.
     *
     * @param customizer A {@link ConfigurationCustomizer} for the {@link TabsBuilder.TabBuilder}; might <b>not</b> be null.
     * @return this
     * @see Tabs#add(Tab...)
     */
    public B addTab(ConfigurationCustomizer<TabBuilder> customizer) {
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
     * @param customizer A {@link ConfigurationCustomizer} for the {@link TabBuilder}; might <b>not</b> be null.
     * @param content    The {@link Component} the new {@link Tab} is being configured for; will automatically call
     *                   {@link Component#setVisible(boolean)} depending on whether the {@link Tab} is being (de)selected.
     * @return this
     * @see Tabs#add(Tab...)
     */
    @SuppressWarnings("unchecked")
    public B addTab(ConfigurationCustomizer<TabBuilder> customizer, Component content) {
        if (customizer == null) {
            throw new IllegalArgumentException("Cannot add a tab using a null customizer.");
        }
        TabBuilder tabBuilder = new TabBuilder();
        customizer.customize(tabBuilder);
        configure(tabBuilder);
        tabBuilder.configure(tab -> get(Tabs.class).addSelectedChangeListener(event -> content.setVisible(event.getSelectedTab() == tab)));
        return (B) this;
    }

    /**
     * Builder method, configures the {@link Component}'s selected {@link Tab}.
     *
     * @param selectedIndex the zero-based index of the selected tab, -1 to unselect all
     * @return this
     * @see Tabs#setSelectedIndex(int)
     */
    public B setSelectedIndex(int selectedIndex) {
        return configure(tabsComponent -> tabsComponent.setSelectedIndex(selectedIndex));
    }

    /**
     * Builder method, configures the {@link Component}'s selected {@link Tab}.
     *
     * @param selectedTab the tab to select, {@code null} to unselect all
     * @return this
     * @see Tabs#setSelectedTab(Tab)
     */
    public B setSelectedTab(Tab selectedTab) {
        return configure(tabsComponent -> tabsComponent.setSelectedTab(selectedTab));
    }

    /**
     * Builder method, configures the {@link Component}'s {@link Tabs.Orientation}.
     *
     * @param orientation the orientation
     * @return this
     * @see Tabs#setOrientation(Tabs.Orientation)
     */
    public B setOrientation(Tabs.Orientation orientation) {
        return configure(tabsComponent -> tabsComponent.setOrientation(orientation));
    }

    /**
     * Builder method, configures the {@link Component}'s flex grow for enclosed {@link Tab}s.
     *
     * @param flexGrow the proportion of the available space the enclosed tabs should
     *                 take up
     * @return this
     * @see Tabs#setFlexGrowForEnclosedTabs(double)
     */
    public B setFlexGrowForEnclosedTabs(double flexGrow) {
        return configure(tabsComponent -> tabsComponent.setFlexGrowForEnclosedTabs(flexGrow));
    }

    /**
     * Builder method, configures a new {@link ComponentEventListener} for {@link Tabs.SelectedChangeEvent}s when a
     * different {@link Tab} is selected.
     *
     * @param listener The listener to add; might <b>not</b> be null.
     * @return this
     * @see Tabs#addSelectedChangeListener(ComponentEventListener)
     */
    public B addSelectedChangeListener(ComponentEventListener<Tabs.SelectedChangeEvent> listener) {
        return configure(tabsComponent -> tabsComponent.addSelectedChangeListener(listener));
    }
}
