package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.Configurer;
import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.dialog.DialogVariant;

@SuppressWarnings("unused")
abstract class AbstractDialogBuilder<B extends AbstractDialogBuilder<B>> extends AbstractComponentBuilder<Dialog, B> implements
        HasComponentsBuilder<Dialog, B>,
        HasElementBuilder<Dialog, B>,
        HasEnabledBuilder<Dialog, B>,
        HasSizeBuilder<Dialog, B>,
        HasStyleBuilder<Dialog, B>,
        HasThemeVariantBuilder<Dialog, DialogVariant, B> {

    AbstractDialogBuilder() {}

    @Override
    protected final Dialog instantiate() {
        return new Dialog();
    }

    /**
     * Builder method, configures whether the {@link Dialog} closes upon the ESC key being pressed.
     *
     * @see Dialog#setCloseOnEsc(boolean)
     * @param close
     *            True if the {@link Dialog} should close, false otherwise.
     * @return this
     */
    public B setCloseOnEsc(boolean close) {
        return configure(dialog -> dialog.setCloseOnEsc(close));
    }

    /**
     * Builder method, configures whether the {@link Dialog} closes upon a click being registered outside the dialog.
     *
     * @see Dialog#setCloseOnOutsideClick(boolean)
     * @param close
     *            True if the {@link Dialog} should close, false otherwise.
     * @return this
     */
    public B setCloseOnOutsideClick(boolean close) {
        return configure(dialog -> dialog.setCloseOnOutsideClick(close));
    }

    /**
     * Builder method, configures the {@link Dialog} to open immediately upon {@link #build()} being called.
     *
     * @see Dialog#open()
     * @return this
     */
    public B open() {
        return configure(Dialog::open);
    }

    /**
     * Builder method, configures a listener for the {@link Dialog} opening/closing.
     *
     * @see Dialog#addOpenedChangeListener(ComponentEventListener)
     * @param listener The listener to add; might <b>not</b> be null.
     * @return this
     */
    public B addOpenListener(ComponentEventListener<Dialog.OpenedChangeEvent> listener) {
        return configure(dialog -> dialog.addOpenedChangeListener(listener));
    }

    /**
     * Builder method, configures a listener for the {@link Dialog} closing because of ESC being pressed or a click
     * outside being registered.
     *
     * @see Dialog#addDialogCloseActionListener(ComponentEventListener)
     * @param listener The listener to add; might <b>not</b> be null.
     * @return this
     */
    public B addCloseListener(ComponentEventListener<Dialog.DialogCloseActionEvent> listener) {
        return configure(dialog -> dialog.addDialogCloseActionListener(listener));
    }

    /**
     * Creates a new {@link Dialog} instance using {@link #instantiate()}, applies all currently contained
     * {@link Configurer}s to it and returns it.
     * <p>
     * Directly opens the {@link Dialog} once its build.
     *
     * @return A new {@link Dialog} instance, fully configured, never null
     */
    public Dialog buildAndOpen() {
        Dialog dialog = build();
        dialog.open();
        return dialog;
    }
}
