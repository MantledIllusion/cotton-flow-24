package com.mantledillusion.vaadin.cotton.demo.security.view;

import com.mantledillusion.vaadin.cotton.component.builder.HorizontalLayoutBuilder;
import com.mantledillusion.vaadin.cotton.component.builder.LabelBuilder;
import com.mantledillusion.vaadin.cotton.component.builder.RouterLinkBuilder;
import com.mantledillusion.vaadin.cotton.component.builder.VerticalLayoutBuilder;
import com.mantledillusion.vaadin.cotton.demo.IndexView;
import com.mantledillusion.vaadin.cotton.demo.UserView;
import com.mantledillusion.vaadin.cotton.demo.security.SecurityConfiguration;
import com.mantledillusion.vaadin.cotton.view.AbstractView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@Route("secured/restricted")
@RolesAllowed(SecurityConfiguration.Roles.ADMIN)
public class RestrictedView extends AbstractView {

    @Override
    protected Component buildUI() throws Exception {
        return VerticalLayoutBuilder.create()
                .setSizeFull()
                .setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER)
                .add(new UserView())
                .add(HorizontalLayoutBuilder.create()
                        .setHeightFull()
                        .setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER)
                        .add(VerticalLayoutBuilder.create()
                                .add(LabelBuilder.create()
                                        .setText("Access to this view restricted to logged in users with privileged rights.")
                                        .build())
                                .add(RouterLinkBuilder.create()
                                        .setText("To unsecured View")
                                        .setRoute(UnsecuredView.class)
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