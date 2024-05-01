package com.mantledillusion.vaadin.cotton.viewpresenter;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;

/**
 * A {@link Composite} view that provides its own content.
 */
public abstract class AbstractView extends Composite<Component> {

    @Override
    protected final Component initContent() {
        try {
            return buildUI();
        } catch (Exception e) {
            throw new RuntimeException("Unable to init content", e);
        }
    }

    protected abstract Component buildUI() throws Exception;
}
