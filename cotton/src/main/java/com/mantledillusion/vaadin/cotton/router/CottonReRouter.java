package com.mantledillusion.vaadin.cotton.router;

import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.UIInitEvent;
import com.vaadin.flow.server.UIInitListener;
import com.vaadin.flow.server.VaadinServiceInitListener;

public class CottonReRouter extends RoleReRouter implements VaadinServiceInitListener, UIInitListener {

    @Override
    public void uiInit(UIInitEvent event) {
        event.getUI().addBeforeEnterListener(this);
    }

    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.getSource().addUIInitListener(this);
    }
}
