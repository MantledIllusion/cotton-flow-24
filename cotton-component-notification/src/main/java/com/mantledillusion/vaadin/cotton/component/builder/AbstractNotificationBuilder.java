package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.Configurer;
import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;

public abstract class AbstractNotificationBuilder<C extends Notification, B extends AbstractNotificationBuilder<C, B>> extends AbstractComponentBuilder<C, B> implements
        HasComponentsBuilder<C, B>,
        HasElementBuilder<C, B>,
        HasEnabledBuilder<C, B>,
        HasStyleBuilder<C, B>,
        HasThemeVariantBuilder<C, NotificationVariant, B> {

    /**
     * Builder method, configures the initial text of the {@link Notification} after building.
     *
     * @param text The text to set; might be null.
     * @return this
     * @see Notification#setText(String)
     */
    public B setText(String text) {
        return configure(notification -> notification.setText(text));
    }

    /**
     * Builder method, sets the position of the {@link Notification}.
     *
     * @param position The position; might <b>not</b> be null.
     * @return this
     * @see Notification#setPosition(Notification.Position)
     */
    public B setPosition(Notification.Position position) {
        return configure(notification -> notification.setPosition(position));
    }

    /**
     * Builder method, sets the duration of the {@link Notification}.
     *
     * @param duration The duration in milliseconds.
     * @return this
     * @see Notification#setPosition(Notification.Position)
     */
    public B setDuration(int duration) {
        return configure(notification -> notification.setDuration(duration));
    }

    /**
     * Creates a new {@link Notification} instance using {@link #instantiate()}, applies all currently contained
     * {@link Configurer}s to it, opens and returns it.
     *
     * @return A new {@link Notification} instance, fully configured, never null
     */
    public C open() {
        C notification = build();
        notification.open();
        return notification;
    }
}
