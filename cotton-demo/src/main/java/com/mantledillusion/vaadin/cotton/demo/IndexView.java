package com.mantledillusion.vaadin.cotton.demo;

import com.mantledillusion.vaadin.cotton.component.builder.HorizontalLayoutBuilder;
import com.mantledillusion.vaadin.cotton.component.builder.RouterLinkBuilder;
import com.mantledillusion.vaadin.cotton.component.builder.VerticalLayoutBuilder;
import com.mantledillusion.vaadin.cotton.demo.injected.view.InjectedView;
import com.mantledillusion.vaadin.cotton.demo.model.view.ModelView;
import com.mantledillusion.vaadin.cotton.demo.mvp.view.MvpView;
import com.mantledillusion.vaadin.cotton.demo.security.view.UnsecuredView;
import com.mantledillusion.vaadin.cotton.view.AbstractView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("")
@AnonymousAllowed
public class IndexView extends AbstractView {

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
                                .add(RouterLinkBuilder.create()
                                        .setText("MVP View and Presenter")
                                        .setRoute(MvpView.class)
                                        .build())
                                .add(RouterLinkBuilder.create()
                                        .setText("Injected Subviews")
                                        .setRoute(InjectedView.class)
                                        .build())
                                .add(RouterLinkBuilder.create()
                                        .setText("Secured Access")
                                        .setRoute(UnsecuredView.class)
                                        .build())
                                .add(RouterLinkBuilder.create()
                                        .setText("Model Binding")
                                        .setRoute(ModelView.class)
                                        .build())
                                .build())
                        .build())
                .build();
    }
}
