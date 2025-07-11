package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.FlexLayout;

public abstract class AbstractFlexLayoutBuilder<C extends FlexLayout, B extends AbstractFlexLayoutBuilder<C, B>> extends AbstractComponentBuilder<C, B> implements
        ClickNotifierBuilder<C, FlexLayout, B>,
        FlexComponentBuilder<C, B>,
        HasComponentsBuilder<C, B>,
        HasElementBuilder<C, B>,
        HasEnabledBuilder<C, B>,
        HasSizeBuilder<C, B>,
        HasStyleBuilder<C, B> {

    /**
     * Builder method, configures the {@link Component}'s {@link FlexLayout.FlexWrap}.
     *
     * @param wrapMode the flex wrap mode of the layout, never <code>null</code>
     * @return this
     * @see FlexLayout#setFlexWrap(FlexLayout.FlexWrap)
     */
    public B setFlexWrap(FlexLayout.FlexWrap wrapMode) {
        return configure(flexLayout -> flexLayout.setFlexWrap(wrapMode));
    }
}
