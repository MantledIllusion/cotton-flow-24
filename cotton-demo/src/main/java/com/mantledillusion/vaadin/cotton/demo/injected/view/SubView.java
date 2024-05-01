package com.mantledillusion.vaadin.cotton.demo.injected.view;

import com.mantledillusion.vaadin.cotton.component.css.CssStyle;
import com.mantledillusion.vaadin.cotton.spring.annotation.AttachScope;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AttachScope
public class SubView extends VerticalLayout {

    @Autowired
    public SubView() {
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        CssStyle.of(CssStyle.BACKGROUND_COLOR.getStylePropertyName(), "lightgrey").apply(this);
        add(new NativeLabel("This view's identity hash code is:"));
    }

    @PostConstruct
    public void construct() {
        add(new NativeLabel(String.valueOf(System.identityHashCode(this))));
    }

    @PreDestroy
    public void destroy() {
        System.out.println(System.identityHashCode(this) + " destroyed!");
    }
}
