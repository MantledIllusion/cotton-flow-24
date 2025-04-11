package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.Configurer;
import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;

import java.util.function.BiConsumer;

public class AbstractTabBuilder <PC, B extends AbstractTabBuilder<PC, B>> extends AbstractConfigurationBuilder<Tab, B> implements
        Configurer<PC>,
        HasComponentsBuilder<Tab, B>,
        HasEnabledBuilder<Tab, B>,
        HasElementBuilder<Tab, B>,
        HasLabelBuilder<Tab, B>,
        HasStyleBuilder<Tab, B>,
        HasThemeVariantBuilder<Tab, TabVariant, B>,
        HasTooltipBuilder<Tab, B> {

    private final Class<PC> parentComponentType;
    private final BiConsumer<PC, Tab> tabAdder;
    private final BiConsumer<PC, Tab> tabSelector;

    <PB extends AbstractConfigurationBuilder<PC, PB>> AbstractTabBuilder(PB parentBuilder,
                                                                         Class<PC> parentComponentType,
                                                                         BiConsumer<PC, Tab> tabAdder,
                                                                         BiConsumer<PC, Tab> tabSelector) {
        super(parentBuilder);
        this.parentComponentType = parentComponentType;
        this.tabAdder = tabAdder;
        this.tabSelector = tabSelector;
    }

    @Override
    public final void configure(PC component) {
        Tab tab = new Tab();
        this.tabAdder.accept(component, tab);
        apply(tab);
    }

    /**
     * Builder method, configures the {@link Tab}'s flex grow.
     *
     * @see Tab#setFlexGrow(double)
     * @param flexGrow
     *            the proportion of the available space the tab should take up
     * @return this
     */
    public B setFlexGrow(double flexGrow) {
        return configure(tab -> tab.setFlexGrow(flexGrow));
    }

    /**
     * Builder method, configures the {@link Tab} to be selected.
     *
     * @see Tab#setSelected(boolean)
     * @param selected
     *            the boolean value to set
     * @return this
     */
    public B setSelected(boolean selected) {
        return configure(tab -> {
            tab.setSelected(selected);
            if (selected) {
                this.tabSelector.accept(AbstractTabBuilder.this.get(this.parentComponentType), tab);
            }
        });
    }

    /**
     * Builder method, configures the {@link Tab}'s enablement.
     *
     * @see Tab#setVisible(boolean)
     * @param enabled
     *          True if the {@link Tab} should be enabled, false otherwise.
     * @return this
     */
    public B setEnabled(boolean enabled) {
        return configure(tab -> tab.setEnabled(enabled));
    }

    /**
     * Builder method, configures the {@link Tab}'s visibility.
     *
     * @see Tab#setVisible(boolean)
     * @param visible
     *          True if the {@link Tab} should be invisible, false otherwise.
     * @return this
     */
    public B setVisible(boolean visible) {
        return configure(tab -> tab.setVisible(visible));
    }
}