package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.i18n.I18N;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import java.util.function.Supplier;

/**
 * {@link ConfigurationBuilder} for {@link Dialog}s with content and 0-&gt;n button options for the user to choose from.
 */
public class BasicDialogBuilder extends AbstractDialogBuilder<BasicDialogBuilder> {

    private BasicDialogBuilder(Supplier<Component> contentSupplier) {
        configure(dialog -> {
            HorizontalLayout btnLayout = HorizontalLayoutBuilder.create().
                    setSizeUndefined().
                    setPadding(false).
                    setSpacing(true).
                    build();
            BasicDialogBuilder.this.set(HorizontalLayout.class, btnLayout);

            dialog.add(VerticalLayoutBuilder.create().
                    setWidthFull().
                    setHeightUndefined().
                    setPadding(false).
                    setSpacing(true).
                    setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER).
                    add(contentSupplier.get()).
                    add(btnLayout).
                    build());
        }, true);
    }

    /**
     * Factory method for a very basic dialog that just contains a text and has 0-&gt;n options to close it with.
     *
     * @param msgId The basic {@link Dialog}'s text content, or a message ID to localize; might be null.
     * @param indexedMessageParameters Optional parameters to replace at their index in the message; might be null
     *
     * @return A new instance, never null.
     */
    public static BasicDialogBuilder create(Object msgId, Object... indexedMessageParameters) {
        return create(LabelBuilder.create()
                .setWidthFull()
                .setHeightUndefined()
                .setText(I18N.getTranslation(msgId, indexedMessageParameters))
                .build());
    }

    /**
     * Factory method for a very basic dialog that just contains a text and has 0-&gt;n options to close it with.
     *
     * @param content The basic {@link Dialog}'s content; might <b>not</b> be null.
     *
     * @return A new instance, never null.
     */
    public static BasicDialogBuilder create(Component content) {
        return new BasicDialogBuilder(() -> content);
    }

    /**
     * Builder method, configures the {@link ButtonBuilder} to use when creating the basic {@link Dialog}'s buttons.
     *
     * @param buttonBuilder The {@link ButtonBuilder} to use; might <b>not</b> be null.
     * @return this;
     */
    public BasicDialogBuilder setButtonBuilder(ButtonBuilder buttonBuilder) {
        if (buttonBuilder == null) {
            throw new IllegalArgumentException("Cannot set a null button builder.");
        }
        return configure(dialog -> set(ButtonBuilder.class, buttonBuilder), true);
    }

    /**
     * Builder method, configures a {@link Button} for the basic {@link Dialog} as an option to close it.
     *
     * @param msgId The text of the {@link Button}, or a message ID to localize; might be null.
     * @return this
     */
    public BasicDialogBuilder addOption(String msgId) {
        return addOption(msgId, null, null);
    }

    /**
     * Builder method, configures a {@link Button} for the basic {@link Dialog} as an option to close it.
     *
     * @param msgId    The text of the {@link Button}, or a message ID to localize; might be null.
     * @param listener A listener to call when this option is selected; might be null.
     * @return this
     */
    public BasicDialogBuilder addOption(String msgId, ComponentEventListener<ClickEvent<Button>> listener) {
        return addOption(msgId, null, listener);
    }

    /**
     * Builder method, configures a {@link Button} for the basic {@link Dialog} as an option to close it.
     *
     * @param icon The component to set as icon to the {@link Button}; might be null.
     * @return this
     */
    public BasicDialogBuilder addOption(Component icon) {
        return addOption(null, icon, null);
    }

    /**
     * Builder method, configures a {@link Button} for the basic {@link Dialog} as an option to close it.
     *
     * @param icon The component to set as icon to the {@link Button}; might be null.
     * @param listener A listener to call when this option is selected; might be null.
     * @return this
     */
    public BasicDialogBuilder addOption(Component icon, ComponentEventListener<ClickEvent<Button>> listener) {
        return addOption(null, icon, listener);
    }

    /**
     * Builder method, configures a {@link Button} for the basic {@link Dialog} as an option to close it.
     *
     * @param msgId The text of the {@link Button}, or a message ID to localize; might be null.
     * @param icon  The component to set as icon to the {@link Button}; might be null.
     * @return this
     */
    public BasicDialogBuilder addOption(String msgId, Component icon) {
        return addOption(msgId, icon, null);
    }

    /**
     * Builder method, configures a {@link Button} for the basic {@link Dialog} as an option to close it.
     *
     * @param msgId    The text of the {@link Button}, or a message ID to localize; might be null.
     * @param icon     The component to set as icon to the {@link Button}; might be null.
     * @param listener A listener to call when this option is selected; might be null.
     * @return this
     */
    public BasicDialogBuilder addOption(String msgId, Component icon, ComponentEventListener<ClickEvent<Button>> listener) {
        return configure(dialog -> {
            Button btn = BasicDialogBuilder.this.get(ButtonBuilder.class).
                    setText(msgId).
                    setIcon(icon).
                    build();
            btn.addClickListener(event -> {
                dialog.close();
                if (listener != null) {
                    listener.onComponentEvent(event);
                }
            });
            BasicDialogBuilder.this.get(HorizontalLayout.class).add(btn);
        });
    }
}
