package com.mantledillusion.vaadin.cotton.demo.event.view;

import com.mantledillusion.vaadin.cotton.component.builder.*;
import com.mantledillusion.vaadin.cotton.demo.IndexView;
import com.mantledillusion.vaadin.cotton.demo.UserView;
import com.mantledillusion.vaadin.cotton.demo.event.EventPojo;
import com.mantledillusion.vaadin.cotton.event.EventBus;
import com.mantledillusion.vaadin.cotton.event.Subscribe;
import com.mantledillusion.vaadin.cotton.view.AbstractView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("event")
@AnonymousAllowed
public class EventView extends AbstractView {

    @Override
    protected Component buildUI() throws Exception {
        return VerticalLayoutBuilder.create()
                .setSizeFull()
                .setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER)
                .add(new UserView())
                .add(HorizontalLayoutBuilder.create()
                        .setHeightFull()
                        .setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER)
                        .add(ButtonBuilder.create()
                                .setText("Dispatch event")
                                .addClickListener(event -> EventBus.dispatch(new EventPojo()))
                                .build())
                        .build())
                .add(RouterLinkBuilder.create()
                        .setText("Back to Index")
                        .setRoute(IndexView.class)
                        .build())
                .build();
    }

    @Subscribe
    private void handle(EventPojo event) {
        NotificationBuilder.create()
                .setText("Event received!")
                .build()
                .open();
    }
}
