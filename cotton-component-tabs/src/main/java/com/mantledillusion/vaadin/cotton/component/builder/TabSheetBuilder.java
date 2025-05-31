package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.ConfigurationCustomizer;
import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.tabs.TabSheetVariant;

import java.util.function.BiConsumer;

/**
 * {@link ConfigurationBuilder} for {@link TabSheet}.
 */
public class TabSheetBuilder extends AbstractComponentBuilder<TabSheet, TabSheetBuilder> implements
        HasElementBuilder<TabSheet, TabSheetBuilder>,
        HasPrefixBuilder<TabSheet, TabSheetBuilder>,
        HasSizeBuilder<TabSheet, TabSheetBuilder>,
        HasStyleBuilder<TabSheet, TabSheetBuilder>,
        HasSuffixBuilder<TabSheet, TabSheetBuilder>,
        HasThemeVariantBuilder<TabSheet, TabSheetVariant, TabSheetBuilder> {

    /**
     * {@link ConfigurationBuilder} for {@link Tab}s.
     */
    public class TabBuilder extends AbstractTabBuilder<TabSheet, TabBuilder> {

        private TabBuilder(BiConsumer<TabSheet, Tab> tabAdder) {
            super(TabSheetBuilder.this, TabSheet.class, tabAdder, TabSheet::setSelectedTab);
        }

        /**
         * Builder method, configures a new {@link ComponentEventListener} for {@link TabSheet.SelectedChangeEvent}s when a
         * tab that is currently being configured {@link Tab} is selected.
         *
         * @see TabSheet#addSelectedChangeListener(ComponentEventListener)
         * @param listener The listener to add; might <b>not</b> be null.
         * @return this
         */
        public TabBuilder addSelectedChangeListenerForTab(ComponentEventListener<TabSheet.SelectedChangeEvent> listener) {
            return configure(tab -> TabSheetBuilder.this.get(TabSheet.class).addSelectedChangeListener(event -> {
                if (tab == event.getSelectedTab()) {
                    listener.onComponentEvent(event);
                }
            }));
        }
    }

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

    /**
     * Builder method, configures a new {@link Tab}.
     *
     * @see TabSheet#add(String, Component)
     * @param label The label to set; might be null.
     * @param sheetContent The {@link Component} to link to the added {@link Tab}; might <b>not</b> be null.
     * @return this
     */
    public TabSheetBuilder addTab(String label, Component sheetContent) {
        return addTab(label, sheetContent, -1);
    }

    /**
     * Builder method, configures a new {@link Tab}.
     *
     * @see TabSheet#add(Tab, Component, int)
     * @param label The label to set; might be null.
     * @param sheetContent The {@link Component} to link to the added {@link Tab}; might <b>not</b> be null.
     * @param position The index at which to add the {@link Tab}, at the end if negative
     * @return this
     */
    public TabSheetBuilder addTab(String label, Component sheetContent, int position) {
        return configure(tabSheet -> tabSheet.add(new Tab(label), sheetContent, position));
    }

    /**
     * Builder method, configures a new {@link Tab}.
     *
     * @see TabSheet#add(Component, Component)
     * @param tabContent The {@link Component} to use as {@link Tab}; might <b>not</b> be null.
     * @param sheetContent The {@link Component} to link to the added {@link Tab}; might <b>not</b> be null.
     * @return this
     */
    public TabSheetBuilder addTab(Component tabContent, Component sheetContent) {
        return addTab(tabContent, sheetContent, -1);
    }

    /**
     * Builder method, configures a new {@link Tab}.
     *
     * @see TabSheet#add(Tab, Component, int)
     * @param tabContent The {@link Component} to use as {@link Tab}; might <b>not</b> be null.
     * @param sheetContent The {@link Component} to link to the added {@link Tab}; might <b>not</b> be null.
     * @param position The index at which to add the {@link Tab}, at the end if negative
     * @return this
     */
    public TabSheetBuilder addTab(Component tabContent, Component sheetContent, int position) {
        return addTab(new Tab(tabContent), sheetContent, position);
    }

    /**
     * Builder method, configures a new {@link Tab}.
     *
     * @see TabSheet#add(Tab, Component)
     * @param tab The {@link Tab} to add; might <b>not</b> be null.
     * @param sheetContent The {@link Component} to link to the added {@link Tab}; might <b>not</b> be null.
     * @return this
     */
    public TabSheetBuilder addTab(Tab tab, Component sheetContent) {
        return addTab(tab, sheetContent, -1);
    }

    /**
     * Builder method, configures a new {@link Tab}.
     *
     * @see TabSheet#add(Tab, Component, int)
     * @param tab The {@link Tab} to add; might <b>not</b> be null.
     * @param sheetContent The {@link Component} to link to the added {@link Tab}; might <b>not</b> be null.
     * @param position The index at which to add the {@link Tab}, at the end if negative
     * @return this
     */
    public TabSheetBuilder addTab(Tab tab, Component sheetContent, int position) {
        return configure(tabSheet -> tabSheet.add(tab, sheetContent, position));
    }

    /**
     * Builder method, configures a new {@link Tab}.
     *
     * @see TabSheet#add(Tab, Component)
     * @param customizer A {@link ConfigurationCustomizer} for the {@link TabBuilder}; might <b>not</b> be null.
     * @param sheetContent The {@link Component} to link to the added {@link Tab}; might <b>not</b> be null.
     * @return this
     */
    public TabSheetBuilder addTab(ConfigurationCustomizer<TabBuilder> customizer, Component sheetContent) {
        return addTab(customizer, sheetContent, -1);
    }

    /**
     * Builder method, configures a new {@link Tab}.
     *
     * @see TabSheet#add(Tab, Component, int)
     * @param customizer A {@link ConfigurationCustomizer} for the {@link TabBuilder}; might <b>not</b> be null.
     * @param sheetContent The {@link Component} to link to the added {@link Tab}; might <b>not</b> be null.
     * @param position The index at which to add the {@link Tab}, at the end if negative
     * @return this
     */
    public TabSheetBuilder addTab(ConfigurationCustomizer<TabBuilder> customizer, Component sheetContent, int position) {
        if (customizer == null) {
            throw new IllegalArgumentException("Cannot add a tab using a null customizer.");
        }
        TabBuilder tabBuilder = new TabBuilder((tabSheet, tab) -> tabSheet.add(tab, sheetContent, position));
        customizer.customize(tabBuilder);
        return configure(tabBuilder);
    }

    /**
     * Builder method, configures the {@link Component}'s selected {@link Tab}.
     *
     * @see TabSheet#setSelectedIndex(int)
     * @param selectedIndex The zero-based index of the selected tab, -1 to unselect all
     * @return this
     */
    public TabSheetBuilder setSelectedIndex(int selectedIndex) {
        return configure(tabSheet -> tabSheet.setSelectedIndex(selectedIndex));
    }

    /**
     * Builder method, configures the {@link Component}'s selected {@link Tab}.
     *
     * @see TabSheet#setSelectedTab(Tab)
     * @param selectedTab The tab to select, {@code null} to unselect all
     * @return this
     */
    public TabSheetBuilder setSelectedTab(Tab selectedTab) {
        return configure(tabSheet -> tabSheet.setSelectedTab(selectedTab));
    }

    /**
     * Builder method, configures a new {@link ComponentEventListener} for {@link TabSheet.SelectedChangeEvent}s when a
     * different {@link Tab} is selected.
     *
     * @see TabSheet#addSelectedChangeListener(ComponentEventListener)
     * @param listener The listener to add; might <b>not</b> be null.
     * @return this
     */
    public TabSheetBuilder addSelectedChangeListener(ComponentEventListener<TabSheet.SelectedChangeEvent> listener) {
        return configure(tabSheet -> tabSheet.addSelectedChangeListener(listener));
    }
}
