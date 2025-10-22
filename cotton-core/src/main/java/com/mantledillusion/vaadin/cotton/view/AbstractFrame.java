package com.mantledillusion.vaadin.cotton.view;

import com.mantledillusion.vaadin.cotton.component.builder.DialogBuilder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.dialog.Dialog;


/**
 * A {@link Composite} view that provides its own content for a {@link Dialog}.
 */
public abstract class AbstractFrame extends AbstractView {

    @Override
    protected final Dialog buildUI() throws Exception {
        DialogBuilder builder = DialogBuilder.create();
        Component content = buildUI(builder);
        Dialog dialog = builder.build();
        dialog.add(content);
        return dialog;
    }

    @Override
    public Dialog getContent() {
        return (Dialog) super.getContent();
    }

    protected abstract Component buildUI(DialogBuilder builder) throws Exception;

    /**
     * Shows this frame.
     *
     * @see Dialog#open()
     */
    public void show() {
        getContent().open();
    }

    /**
     * Returns whether this frame is currently showing.
     *
     * @see Dialog#isOpened()
     * @return True if the frame is showing, false otherwise.
     */
    public boolean isShowing() {
        return getContent().isOpened();
    }

    /**
     * Hides this frame.
     *
     * @see Dialog#close()
     */
    public void hide() {
        getContent().close();
    }
}