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

public abstract class AbstractTabSheetBuilder<C extends TabSheet, B extends AbstractTabSheetBuilder<C, B>> extends AbstractComponentBuilder<C, B> implements
        HasElementBuilder<C, B>,
        HasPrefixBuilder<C, B>,
        HasSizeBuilder<C, B>,
        HasStyleBuilder<C, B>,
        HasSuffixBuilder<C, B>,
        HasThemeVariantBuilder<C, TabSheetVariant, B> {

    /**
     * {@link ConfigurationBuilder} for {@link Tab}s.
     */
    public class TabBuilder extends AbstractTabBuilder<C, TabBuilder> {

        @SuppressWarnings("unchecked")
        private TabBuilder(BiConsumer<C, Tab> tabAdder) {
            super((B) AbstractTabSheetBuilder.this, TabSheet.class, tabAdder, TabSheet::setSelectedTab);
        }

        /**
         * Builder method, configures a new {@link ComponentEventListener} for {@link TabSheet.SelectedChangeEvent}s when a
         * tab that is currently being configured {@link Tab} is selected.
         *
         * @param listener The listener to add; might <b>not</b> be null.
         * @return this
         * @see TabSheet#addSelectedChangeListener(ComponentEventListener)
         */
        public TabBuilder addSelectedChangeListenerForTab(ComponentEventListener<TabSheet.SelectedChangeEvent> listener) {
            return configure(tab -> AbstractTabSheetBuilder.this.get(TabSheet.class).addSelectedChangeListener(event -> {
                if (tab == event.getSelectedTab()) {
                    listener.onComponentEvent(event);
                }
            }));
        }
    }

    /**
     * Builder method, configures a new {@link Tab}.
     *
     * @param label        The label to set; might be null.
     * @param sheetContent The {@link Component} to link to the added {@link Tab}; might <b>not</b> be null.
     * @return this
     * @see TabSheet#add(String, Component)
     */
    public B addTab(String label, Component sheetContent) {
        return addTab(label, sheetContent, -1);
    }

    /**
     * Builder method, configures a new {@link Tab}.
     *
     * @param label        The label to set; might be null.
     * @param sheetContent The {@link Component} to link to the added {@link Tab}; might <b>not</b> be null.
     * @param position     The index at which to add the {@link Tab}, at the end if negative
     * @return this
     * @see TabSheet#add(Tab, Component, int)
     */
    public B addTab(String label, Component sheetContent, int position) {
        return configure(tabSheet -> tabSheet.add(new Tab(label), sheetContent, position));
    }

    /**
     * Builder method, configures a new {@link Tab}.
     *
     * @param tabContent   The {@link Component} to use as {@link Tab}; might <b>not</b> be null.
     * @param sheetContent The {@link Component} to link to the added {@link Tab}; might <b>not</b> be null.
     * @return this
     * @see TabSheet#add(Component, Component)
     */
    public B addTab(Component tabContent, Component sheetContent) {
        return addTab(tabContent, sheetContent, -1);
    }

    /**
     * Builder method, configures a new {@link Tab}.
     *
     * @param tabContent   The {@link Component} to use as {@link Tab}; might <b>not</b> be null.
     * @param sheetContent The {@link Component} to link to the added {@link Tab}; might <b>not</b> be null.
     * @param position     The index at which to add the {@link Tab}, at the end if negative
     * @return this
     * @see TabSheet#add(Tab, Component, int)
     */
    public B addTab(Component tabContent, Component sheetContent, int position) {
        return addTab(new Tab(tabContent), sheetContent, position);
    }

    /**
     * Builder method, configures a new {@link Tab}.
     *
     * @param tab          The {@link Tab} to add; might <b>not</b> be null.
     * @param sheetContent The {@link Component} to link to the added {@link Tab}; might <b>not</b> be null.
     * @return this
     * @see TabSheet#add(Tab, Component)
     */
    public B addTab(Tab tab, Component sheetContent) {
        return addTab(tab, sheetContent, -1);
    }

    /**
     * Builder method, configures a new {@link Tab}.
     *
     * @param tab          The {@link Tab} to add; might <b>not</b> be null.
     * @param sheetContent The {@link Component} to link to the added {@link Tab}; might <b>not</b> be null.
     * @param position     The index at which to add the {@link Tab}, at the end if negative
     * @return this
     * @see TabSheet#add(Tab, Component, int)
     */
    public B addTab(Tab tab, Component sheetContent, int position) {
        return configure(tabSheet -> tabSheet.add(tab, sheetContent, position));
    }

    /**
     * Builder method, configures a new {@link Tab}.
     *
     * @param customizer   A {@link ConfigurationCustomizer} for the {@link TabBuilder}; might <b>not</b> be null.
     * @param sheetContent The {@link Component} to link to the added {@link Tab}; might <b>not</b> be null.
     * @return this
     * @see TabSheet#add(Tab, Component)
     */
    public B addTab(ConfigurationCustomizer<TabBuilder> customizer, Component sheetContent) {
        return addTab(customizer, sheetContent, -1);
    }

    /**
     * Builder method, configures a new {@link Tab}.
     *
     * @param customizer   A {@link ConfigurationCustomizer} for the {@link TabBuilder}; might <b>not</b> be null.
     * @param sheetContent The {@link Component} to link to the added {@link Tab}; might <b>not</b> be null.
     * @param position     The index at which to add the {@link Tab}, at the end if negative
     * @return this
     * @see TabSheet#add(Tab, Component, int)
     */
    public B addTab(ConfigurationCustomizer<TabBuilder> customizer, Component sheetContent, int position) {
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
     * @param selectedIndex The zero-based index of the selected tab, -1 to unselect all
     * @return this
     * @see TabSheet#setSelectedIndex(int)
     */
    public B setSelectedIndex(int selectedIndex) {
        return configure(tabSheet -> tabSheet.setSelectedIndex(selectedIndex));
    }

    /**
     * Builder method, configures the {@link Component}'s selected {@link Tab}.
     *
     * @param selectedTab The tab to select, {@code null} to unselect all
     * @return this
     * @see TabSheet#setSelectedTab(Tab)
     */
    public B setSelectedTab(Tab selectedTab) {
        return configure(tabSheet -> tabSheet.setSelectedTab(selectedTab));
    }

    /**
     * Builder method, configures a new {@link ComponentEventListener} for {@link TabSheet.SelectedChangeEvent}s when a
     * different {@link Tab} is selected.
     *
     * @param listener The listener to add; might <b>not</b> be null.
     * @return this
     * @see TabSheet#addSelectedChangeListener(ComponentEventListener)
     */
    public B addSelectedChangeListener(ComponentEventListener<TabSheet.SelectedChangeEvent> listener) {
        return configure(tabSheet -> tabSheet.addSelectedChangeListener(listener));
    }
}
