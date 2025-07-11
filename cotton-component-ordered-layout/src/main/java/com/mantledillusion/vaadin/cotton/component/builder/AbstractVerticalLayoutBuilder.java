package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public abstract class AbstractVerticalLayoutBuilder<C extends VerticalLayout, B extends AbstractVerticalLayoutBuilder<C, B>> extends AbstractComponentBuilder<C, B> implements
        ClickNotifierBuilder<C, VerticalLayout, B>,
        FlexComponentBuilder<C, B>,
        HasComponentsBuilder<C, B>,
        HasElementBuilder<C, B>,
        HasEnabledBuilder<C, B>,
        HasSizeBuilder<C, B>,
        HasStyleBuilder<C, B>,
        ThemableLayoutBuilder<C, B> {

    /**
     * Builder method, configures the {@link Component}'s horizontal child {@link Component} alignment.
     *
     * @param alignment         the individual alignment for the children components. Setting
     *                          <code>null</code> will reset the alignment to its default
     * @param componentsToAlign The components to which the individual alignment should be set
     * @return this
     * @see VerticalLayout#setHorizontalComponentAlignment(FlexComponent.Alignment, Component...)
     */
    public B setHorizontalComponentAlignment(FlexComponent.Alignment alignment,
                                                                 Component... componentsToAlign) {
        return configure(verticalLayout -> verticalLayout.setHorizontalComponentAlignment(alignment, componentsToAlign));
    }

    /**
     * Builder method, configures the {@link Component}'s default horizontal child {@link Component} alignment.
     *
     * @param alignment the alignment to apply to the components. Setting
     *                  <code>null</code> will reset the alignment to its default
     * @return this
     * @see VerticalLayout#setDefaultHorizontalComponentAlignment(FlexComponent.Alignment)
     */
    public B setDefaultHorizontalComponentAlignment(FlexComponent.Alignment alignment) {
        return configure(verticalLayout -> verticalLayout.setDefaultHorizontalComponentAlignment(alignment));
    }

    /**
     * Builder method, configures the {@link Component}'s child {@link Component}s.
     *
     * @param alignment  The alignment to align the given components with; might <b>not</b> be null.
     * @param components The {@link Component}s to add; might be null, might <b>not</b> contain nulls.
     * @return this
     * @see VerticalLayout#add(Component...)
     * @see VerticalLayout#setHorizontalComponentAlignment(FlexComponent.Alignment, Component...)
     */
    public B add(FlexComponent.Alignment alignment, Component... components) {
        return configure(verticalLayout -> {
            verticalLayout.add(components);
            verticalLayout.setHorizontalComponentAlignment(alignment, components);
        });
    }
}
