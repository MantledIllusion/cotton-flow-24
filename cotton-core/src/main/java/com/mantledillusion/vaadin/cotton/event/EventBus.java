package com.mantledillusion.vaadin.cotton.event;

import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinRequest;

import java.util.*;

/**
 * Utility class providing static functionality on the current {@link VaadinRequest}'s {@link Bus}.
 */
public class EventBus {

    /**
     * Dispatches the given event of potential @{@link Subscribe}rs to the {@link Bus} instance registered to the current {@link VaadinRequest}'s {@link UI}.
     *
     * @param event The event to dispatch; might <b>not</b> be null.
     */
    public static void dispatch(Object event) {
        var ui = Optional.ofNullable(UI.getCurrent())
                .orElseThrow(() -> new IllegalStateException("Unable to access bus on a non-request thread"));

        Optional.ofNullable(ComponentUtil.getData(ui, Bus.class))
                .ifPresent(bus -> bus.dispatch(event));
    }
}
