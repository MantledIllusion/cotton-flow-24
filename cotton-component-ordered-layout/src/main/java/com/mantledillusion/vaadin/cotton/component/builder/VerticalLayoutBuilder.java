package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

/**
 * {@link ConfigurationBuilder} for {@link VerticalLayout}s.
 */
public class VerticalLayoutBuilder extends AbstractComponentBuilder<VerticalLayout, VerticalLayoutBuilder> implements
        ClickNotifierBuilder<VerticalLayout, VerticalLayoutBuilder>,
        FlexComponentBuilder<VerticalLayout, VerticalLayoutBuilder>,
        HasComponentsBuilder<VerticalLayout, VerticalLayoutBuilder>,
        HasElementBuilder<VerticalLayout, VerticalLayoutBuilder>,
        HasEnabledBuilder<VerticalLayout, VerticalLayoutBuilder>,
        HasSizeBuilder<VerticalLayout, VerticalLayoutBuilder>,
        HasStyleBuilder<VerticalLayout, VerticalLayoutBuilder>,
        ThemableLayoutBuilder<VerticalLayout, VerticalLayoutBuilder> {

    private VerticalLayoutBuilder() {}

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static VerticalLayoutBuilder create() {
        return new VerticalLayoutBuilder();
    }

    @Override
    protected VerticalLayout instantiate() {
        return new VerticalLayout();
    }

    /**
     * Builder method, configures the {@link Component}'s horizontal child {@link Component} alignment.
     *
     * @see VerticalLayout#setHorizontalComponentAlignment(FlexComponent.Alignment, Component...)
     * @param alignment
     *            the individual alignment for the children components. Setting
     *            <code>null</code> will reset the alignment to its default
     * @param componentsToAlign
     *            The components to which the individual alignment should be set
     * @return this
     */
    public VerticalLayoutBuilder setHorizontalComponentAlignment(FlexComponent.Alignment alignment,
                                                                 Component... componentsToAlign) {
        return configure(verticalLayout -> verticalLayout.setHorizontalComponentAlignment(alignment, componentsToAlign));
    }

    /**
     * Builder method, configures the {@link Component}'s default horizontal child {@link Component} alignment.
     *
     * @see VerticalLayout#setDefaultHorizontalComponentAlignment(FlexComponent.Alignment)
     * @param alignment
     *            the alignment to apply to the components. Setting
     *            <code>null</code> will reset the alignment to its default
     * @return this
     */
    public VerticalLayoutBuilder setDefaultHorizontalComponentAlignment(FlexComponent.Alignment alignment) {
        return configure(verticalLayout -> verticalLayout.setDefaultHorizontalComponentAlignment(alignment));
    }

    /**
     * Builder method, configures the {@link Component}'s child {@link Component}s.
     *
     * @see VerticalLayout#add(Component...)
     * @see VerticalLayout#setHorizontalComponentAlignment(FlexComponent.Alignment, Component...)
     * @param alignment The alignment to align the given components with; might <b>not</b> be null.
     * @param components The {@link Component}s to add; might be null, might <b>not</b> contain nulls.
     * @return this
     */
    public VerticalLayoutBuilder add(FlexComponent.Alignment alignment, Component... components) {
        return configure(verticalLayout -> {
            verticalLayout.add(components);
            verticalLayout.setHorizontalComponentAlignment(alignment, components);
        });
    }
}
