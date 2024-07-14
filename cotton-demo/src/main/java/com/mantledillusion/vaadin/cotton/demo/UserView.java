package com.mantledillusion.vaadin.cotton.demo;

import com.mantledillusion.vaadin.cotton.component.builder.ButtonBuilder;
import com.mantledillusion.vaadin.cotton.component.builder.HorizontalLayoutBuilder;
import com.mantledillusion.vaadin.cotton.component.builder.LabelBuilder;
import com.mantledillusion.vaadin.cotton.demo.security.LoginView;
import com.mantledillusion.vaadin.cotton.server.auth.Authorization;
import com.mantledillusion.vaadin.cotton.viewpresenter.AbstractView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.router.QueryParameters;

public class UserView extends AbstractView {

    @Override
    protected Component buildUI() throws Exception {
        return HorizontalLayoutBuilder.create()
                .setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER)
                .add(LabelBuilder.create()
                        .setText(Authorization.getPrincipalName()
                                .map(name -> "User: " + name)
                                .orElse("No user logged in"))
                        .build())
                .add(ButtonBuilder.create()
                        .setText("Login")
                        .addClickListener(event -> {
                            UI.getCurrent().navigate(LoginView.class, QueryParameters.of(LoginView.FORWARD_URL, UI.getCurrent().getActiveViewLocation().getPathWithQueryParameters()));
                        })
                        .setVisible(!Authorization.hasPrincipal())
                        .build())
                .add(ButtonBuilder.create()
                        .setText("Logout")
                        .addClickListener(evt -> {
                            Authorization.logoutPrincipal();
                        })
                        .setVisible(Authorization.hasPrincipal())
                        .build())
                .build();
    }
}
