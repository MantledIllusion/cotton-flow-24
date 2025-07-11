package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.vaadin.flow.component.HtmlContainer;

import java.util.function.Supplier;

public abstract class AbstractHtmlContainerBuilder<C extends HtmlContainer, B extends AbstractHtmlContainerBuilder<C, B>> extends AbstractComponentBuilder<C, B> implements
        HasComponentsBuilder<C, B>,
        HasElementBuilder<C, B>,
        HasSizeBuilder<C, B>,
        HasTextBuilder<C, B>{

    private final Supplier<C> componentSupplier;

    public AbstractHtmlContainerBuilder(Supplier<C> componentSupplier) {
        this.componentSupplier = componentSupplier;
    }

    @Override
    protected C instantiate() {
        return this.componentSupplier.get();
    }
}
