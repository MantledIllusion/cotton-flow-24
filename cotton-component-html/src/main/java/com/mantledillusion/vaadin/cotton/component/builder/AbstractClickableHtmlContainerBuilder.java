package com.mantledillusion.vaadin.cotton.component.builder;

import com.vaadin.flow.component.ClickNotifier;
import com.vaadin.flow.component.HtmlContainer;

import java.util.function.Supplier;

abstract class AbstractClickableHtmlContainerBuilder<C extends HtmlContainer & ClickNotifier<C>, B extends AbstractClickableHtmlContainerBuilder<C, B>> extends AbstractHtmlContainerBuilder<C, B> {

    AbstractClickableHtmlContainerBuilder(Supplier<C> componentSupplier) {
        super(componentSupplier);
    }
}
