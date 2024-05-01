package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.dialog.Dialog;

/**
 * {@link ConfigurationBuilder} for {@link Dialog}s.
 */
public class DialogBuilder extends AbstractDialogBuilder<DialogBuilder> {

    private DialogBuilder() {}

    /**
     * Factory method for a dialog.
     *
     * @return A new instance, never null.
     */
    public static DialogBuilder create() {
        return new DialogBuilder();
    }
}
