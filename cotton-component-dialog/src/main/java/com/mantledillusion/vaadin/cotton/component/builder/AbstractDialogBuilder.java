package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.ConfigurationCustomizer;
import com.mantledillusion.vaadin.cotton.component.Configurer;
import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.dialog.DialogVariant;

/**
 * {@link ConfigurationBuilder} for subtypes of {@link Dialog}.
 */
public abstract class AbstractDialogBuilder<C extends Dialog, B extends AbstractDialogBuilder<C, B>> extends AbstractComponentBuilder<C, B> implements
        HasComponentsBuilder<C, B>,
        HasElementBuilder<C, B>,
        HasEnabledBuilder<C, B>,
        HasSizeBuilder<C, B>,
        HasStyleBuilder<C, B>,
        HasThemeVariantBuilder<C, DialogVariant, B> {

    public class DialogHeaderBuilder extends AbstractConfigurationBuilder<Dialog.DialogHeader, DialogHeaderBuilder> implements
            HasComponentsBuilder<Dialog.DialogHeader, DialogHeaderBuilder> {

        private DialogHeaderBuilder() {
            super(AbstractDialogBuilder.this);
        }

        /**
         * Builder method, sets the {@link Dialog}'s title.
         *
         * @see Dialog#setHeaderTitle(String)
         * @param title The title to set; might be null.
         * @return this
         */
        public DialogHeaderBuilder setTitle(String title) {
            AbstractDialogBuilder.this.configure(dialog -> dialog.setHeaderTitle(title));
            return this;
        }
    }

    public class DialogFooterBuilder extends AbstractConfigurationBuilder<Dialog.DialogFooter, DialogFooterBuilder> implements
            HasComponentsBuilder<Dialog.DialogFooter, DialogFooterBuilder> {

        private DialogFooterBuilder() {
            super(AbstractDialogBuilder.this);
        }
    }

    public AbstractDialogBuilder() {}

    /**
     * Builder method, configures the {@link Dialog.DialogHeader}.
     *
     * @param customizer A {@link ConfigurationCustomizer} for the {@link DialogHeaderBuilder}; might <b>not</b> be null.
     * @return this
     */
    public B setHeader(ConfigurationCustomizer<DialogHeaderBuilder> customizer) {
        var builder  = new DialogHeaderBuilder();
        customizer.customize(builder);
        return configure(dialog -> builder.apply(dialog.getHeader()));
    }

    /**
     * Builder method, configures the {@link Dialog.DialogFooter}.
     *
     * @param customizer A {@link ConfigurationCustomizer} for the {@link DialogFooterBuilder}; might <b>not</b> be null.
     * @return this
     */
    public B setFooter(ConfigurationCustomizer<DialogFooterBuilder> customizer) {
        var builder  = new DialogFooterBuilder();
        customizer.customize(builder);
        return configure(dialog -> builder.apply(dialog.getFooter()));
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
    public C buildAndOpen() {
        C dialog = build();
        dialog.open();
        return dialog;
    }
}
