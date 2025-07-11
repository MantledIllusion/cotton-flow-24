package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public abstract class AbstractHorizontalLayoutBuilder<C extends HorizontalLayout, B extends AbstractHorizontalLayoutBuilder<C, B>> extends AbstractComponentBuilder<C, B> implements
        ClickNotifierBuilder<C, HorizontalLayout, B>,
        FlexComponentBuilder<C, B>,
        HasComponentsBuilder<C, B>,
        HasElementBuilder<C, B>,
        HasEnabledBuilder<C, B>,
        HasSizeBuilder<C, B>,
        HasStyleBuilder<C, B>,
        ThemableLayoutBuilder<C, B> {

    /**
     * Builder method, configures the {@link Component}'s vertical child {@link Component} alignment.
     *
     * @param alignment         the individual alignment for the children components. Setting
     *                          <code>null</code> will reset the alignment to its default
     * @param componentsToAlign The components to which the individual alignment should be set
     * @return this
     * @see HorizontalLayout#setVerticalComponentAlignment(FlexComponent.Alignment, Component...)
     */
    public B setVerticalComponentAlignment(FlexComponent.Alignment alignment,
                                                                 Component... componentsToAlign) {
        return configure(horizontalLayout -> horizontalLayout.setVerticalComponentAlignment(alignment, componentsToAlign));
    }

    /**
     * Builder method, configures the {@link Component}'s default vertical child {@link Component} alignment.
     *
     * @param alignment the alignment to apply to the components. Setting
     *                  <code>null</code> will reset the alignment to its default
     * @return this
     * @see HorizontalLayout#setDefaultVerticalComponentAlignment(FlexComponent.Alignment)
     */
    public B setDefaultVerticalComponentAlignment(FlexComponent.Alignment alignment) {
        return configure(horizontalLayout -> horizontalLayout.setDefaultVerticalComponentAlignment(alignment));
    }

    /**
     * Builder method, configures the {@link Component}'s child {@link Component}s.
     *
     * @param alignment  The alignment to align the given components with; might <b>not</b> be null.
     * @param components The {@link Component}s to add; might be null, might <b>not</b> contain nulls.
     * @return this
     * @see HorizontalLayout#add(Component...)
     * @see HorizontalLayout#setVerticalComponentAlignment(FlexComponent.Alignment, Component...)
     */
    public B add(FlexComponent.Alignment alignment, Component... components) {
        return configure(verticalLayout -> {
            verticalLayout.add(components);
            verticalLayout.setVerticalComponentAlignment(alignment, components);
        });
    }
}
