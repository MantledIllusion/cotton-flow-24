package com.mantledillusion.vaadin.cotton.demo.injected.view;

import com.mantledillusion.vaadin.cotton.component.builder.HorizontalLayoutBuilder;
import com.mantledillusion.vaadin.cotton.component.builder.RouterLinkBuilder;
import com.mantledillusion.vaadin.cotton.component.builder.VerticalLayoutBuilder;
import com.mantledillusion.vaadin.cotton.demo.IndexView;
import com.mantledillusion.vaadin.cotton.demo.UserView;
import com.mantledillusion.vaadin.cotton.viewpresenter.AbstractView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.spring.annotation.RouteScope;
import org.springframework.beans.factory.annotation.Autowired;

@Route("injected")
@AnonymousAllowed
@RouteScope
public class InjectedView extends AbstractView {

    @Autowired
    private SubView injectedViewA;
    @Autowired
    private SubView injectedViewB;

    @Override
    protected Component buildUI() throws Exception {
        return VerticalLayoutBuilder.create()
                .setSizeFull()
                .setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER)
                .add(new UserView())
                .add(HorizontalLayoutBuilder.create()
                        .setHeightFull()
                        .setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER)
                        .add(injectedViewA, injectedViewB)
                        .build())
                .add(RouterLinkBuilder.create()
                        .setText("Back to Index")
                        .setRoute(IndexView.class)
                        .build())
                .build();
    }
}
