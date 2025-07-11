package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.mixin.HasElementBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.HasTextBuilder;
import com.vaadin.flow.component.Text;

public abstract class AbstractTextBuilder<C extends Text, B extends AbstractTextBuilder<C, B>> extends AbstractComponentBuilder<C, B> implements
        HasElementBuilder<C, B>,
        HasTextBuilder<C, B> {
}
