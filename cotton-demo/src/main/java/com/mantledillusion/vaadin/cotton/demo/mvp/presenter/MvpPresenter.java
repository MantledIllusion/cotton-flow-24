package com.mantledillusion.vaadin.cotton.demo.mvp.presenter;

import com.mantledillusion.vaadin.cotton.component.builder.NotificationBuilder;
import com.mantledillusion.vaadin.cotton.demo.mvp.view.MvpView;
import com.mantledillusion.vaadin.cotton.spring.annotation.AttachScope;
import com.mantledillusion.vaadin.cotton.presenter.AbstractPresenter;
import com.mantledillusion.vaadin.cotton.presenter.Listen;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;

@AttachScope
public class MvpPresenter extends AbstractPresenter<MvpView> {

    @Listen(MvpView.CID_BTN)
    private void handle(ClickEvent<Button> event) {
        NotificationBuilder.create()
                .setText("The presenter has listened!")
                .setDuration(2000)
                .open();
    }
}
