package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.mixin.ClickNotifierBuilder;
import com.vaadin.flow.component.ClickNotifier;
import com.vaadin.flow.component.HtmlContainer;

import java.util.function.Supplier;

public abstract class AbstractClickableHtmlContainerBuilder<C extends HtmlContainer & ClickNotifier<C>, B extends AbstractClickableHtmlContainerBuilder<C, B>> extends AbstractHtmlContainerBuilder<C, B> implements
        ClickNotifierBuilder<C, C, B> {

    public AbstractClickableHtmlContainerBuilder(Supplier<C> componentSupplier) {
        super(componentSupplier);
    }
}
