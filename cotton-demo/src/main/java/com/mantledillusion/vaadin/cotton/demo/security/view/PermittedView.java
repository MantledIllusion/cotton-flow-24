package com.mantledillusion.vaadin.cotton.demo.security.view;

import com.mantledillusion.vaadin.cotton.component.builder.HorizontalLayoutBuilder;
import com.mantledillusion.vaadin.cotton.component.builder.LabelBuilder;
import com.mantledillusion.vaadin.cotton.component.builder.RouterLinkBuilder;
import com.mantledillusion.vaadin.cotton.component.builder.VerticalLayoutBuilder;
import com.mantledillusion.vaadin.cotton.demo.IndexView;
import com.mantledillusion.vaadin.cotton.demo.UserView;
import com.mantledillusion.vaadin.cotton.view.AbstractView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@Route("secured/permitted")
@PermitAll
public class PermittedView extends AbstractView {

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
                                        .setText("Access to this view permitted to logged in users.")
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