package com.mantledillusion.vaadin.cotton.demo.injected.view;

import com.helger.css.ECSSUnit;
import com.mantledillusion.vaadin.cotton.component.css.CssStyle;
import com.mantledillusion.vaadin.cotton.demo.injected.Injectable;
import com.mantledillusion.vaadin.cotton.demo.injected.InjectionConfiguration;
import com.mantledillusion.vaadin.cotton.spring.annotation.AttachedComponent;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@AttachedComponent
public class SubView extends FormLayout {

    @Autowired
    public SubView(@Qualifier(InjectionConfiguration.SESSION) Injectable sessionInjectable,
                   @Qualifier(InjectionConfiguration.UI) Injectable uiInjectable,
                   @Qualifier(InjectionConfiguration.ROUTE) Injectable routeInjectable) {
        setWidth(null);
        setHeight(null);
        setResponsiveSteps(new FormLayout.ResponsiveStep("0px", 1));
        CssStyle.of(CssStyle.BACKGROUND_COLOR.getStylePropertyName(), "lightgrey").apply(this);
        CssStyle.PADDING.of(10, ECSSUnit.PX).apply(this);

        addFormItem(new NativeLabel(String.valueOf(System.identityHashCode(sessionInjectable))), "Session hash:");
        addFormItem(new NativeLabel(String.valueOf(System.identityHashCode(uiInjectable))), "UI hash:");
        addFormItem(new NativeLabel(String.valueOf(System.identityHashCode(routeInjectable))), "Route hash:");
    }

    @PostConstruct
    public void construct() {
        addFormItem(new NativeLabel(String.valueOf(System.identityHashCode(this))), "Component hash:");
    }

    @PreDestroy
    public void destroy() {
        System.out.println(System.identityHashCode(this) + " destroyed!");
    }
}
