package com.mantledillusion.vaadin.cotton.router;

import com.vaadin.flow.server.UIInitEvent;
import com.vaadin.flow.server.UIInitListener;

public class CottonRoleReRouter extends RoleReRouter implements UIInitListener {

    @Override
    public void uiInit(UIInitEvent event) {
        event.getUI().addBeforeEnterListener(this);
    }
}
