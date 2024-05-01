package com.mantledillusion.vaadin.cotton.demo.security.view;

import com.mantledillusion.vaadin.cotton.component.builder.HorizontalLayoutBuilder;
import com.mantledillusion.vaadin.cotton.component.builder.LabelBuilder;
import com.mantledillusion.vaadin.cotton.component.builder.RouterLinkBuilder;
import com.mantledillusion.vaadin.cotton.component.builder.VerticalLayoutBuilder;
import com.mantledillusion.vaadin.cotton.demo.IndexView;
import com.mantledillusion.vaadin.cotton.viewpresenter.AbstractView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("unsecured")
@AnonymousAllowed
public class UnsecuredView extends AbstractView {

    @Override
    protected Component buildUI() throws Exception {
        return VerticalLayoutBuilder.create()
                .setSizeFull()
                .setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER)
                .add(HorizontalLayoutBuilder.create()
                        .setHeightFull()
                        .setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER)
                        .add(VerticalLayoutBuilder.create()
                                .add(LabelBuilder.create()
                                        .setText("Access to this view is allowed to everybody.")
                                        .build())
                                .add(RouterLinkBuilder.create()
                                        .setText("To permitted View")
                                        .setRoute(PermittedView.class)
                                        .build())
                                .add(RouterLinkBuilder.create()
                                        .setText("To restricted View")
                                        .setRoute(RestrictedView.class)
                                        .build())
                                .build())
                        .build())
                .add(RouterLinkBuilder.create()
                        .setText("Back to Index")
                        .setRoute(IndexView.class)
                        .build())
                .build();
    }
}
