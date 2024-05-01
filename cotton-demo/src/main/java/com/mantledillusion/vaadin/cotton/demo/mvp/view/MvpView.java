package com.mantledillusion.vaadin.cotton.demo.mvp.view;

import com.mantledillusion.vaadin.cotton.component.builder.ButtonBuilder;
import com.mantledillusion.vaadin.cotton.component.builder.HorizontalLayoutBuilder;
import com.mantledillusion.vaadin.cotton.component.builder.RouterLinkBuilder;
import com.mantledillusion.vaadin.cotton.component.builder.VerticalLayoutBuilder;
import com.mantledillusion.vaadin.cotton.demo.mvp.presenter.MvpPresenter;
import com.mantledillusion.vaadin.cotton.demo.IndexView;
import com.mantledillusion.vaadin.cotton.viewpresenter.AbstractView;
import com.mantledillusion.vaadin.cotton.viewpresenter.Presented;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("mvp")
@AnonymousAllowed
@Presented(MvpPresenter.class)
public class MvpView extends AbstractView {

    public static final String CID_BTN = "btn";

    @Override
    protected Component buildUI() throws Exception {
        return VerticalLayoutBuilder.create()
                .setSizeFull()
                .setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER)
                .add(HorizontalLayoutBuilder.create()
                        .setHeightFull()
                        .setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER)
                        .add(ButtonBuilder.create()
                                .setId(CID_BTN)
                                .setText("Notify me!")
                                .build())
                        .build())
                .add(RouterLinkBuilder.create()
                        .setText("Back to Index")
                        .setRoute(IndexView.class)
                        .build())
                .build();
    }
}
