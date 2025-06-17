package com.mantledillusion.vaadin.cotton.demo.injected.view;

import com.helger.css.ECSSUnit;
import com.helger.css.property.ECSSProperty;
import com.helger.css.utils.ECSSColor;
import com.mantledillusion.vaadin.cotton.demo.injected.Injectable;
import com.mantledillusion.vaadin.cotton.demo.injected.InjectionConfiguration;
import com.mantledillusion.vaadin.cotton.spring.annotation.AttachedComponent;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.NativeLabel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@AttachedComponent
public class SubView extends FormLayout {

    @Autowired
    public SubView(@Qualifier(InjectionConfiguration.SESSION) Injectable sessionInjectable,
                   @Qualifier(InjectionConfiguration.UI) Injectable uiInjectable,
                   @Qualifier(InjectionConfiguration.ROUTE) Injectable routeInjectable,
                   @Qualifier(InjectionConfiguration.ATTACH) Injectable attachInjectable) {
        setWidth(null);
        setHeight(null);
        setResponsiveSteps(new FormLayout.ResponsiveStep(ECSSUnit.px(0), 1));
        getStyle().set(ECSSProperty.BACKGROUND_COLOR.getName(), ECSSColor.LIGHTGRAY.getName());
        getStyle().set(ECSSProperty.PADDING.getName(), ECSSUnit.px(10));

        addFormItem(new NativeLabel(String.valueOf(System.identityHashCode(sessionInjectable))), "Session hash:");
        addFormItem(new NativeLabel(String.valueOf(System.identityHashCode(uiInjectable))), "UI hash:");
        addFormItem(new NativeLabel(String.valueOf(System.identityHashCode(routeInjectable))), "Route hash:");
        addFormItem(new NativeLabel(String.valueOf(System.identityHashCode(attachInjectable))), "Attach hash:");
    }
}
