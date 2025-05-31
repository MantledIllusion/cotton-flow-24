package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.Configurer;
import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.mantledillusion.vaadin.cotton.i18n.I18N;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;

/**
 * {@link ConfigurationBuilder} for {@link Notification}s.
 */
public class NotificationBuilder extends AbstractComponentBuilder<Notification, NotificationBuilder> implements
		HasComponentsBuilder<Notification, NotificationBuilder>,
		HasElementBuilder<Notification, NotificationBuilder>,
		HasEnabledBuilder<Notification, NotificationBuilder>,
		HasStyleBuilder<Notification, NotificationBuilder>,
		HasThemeVariantBuilder<Notification, NotificationVariant, NotificationBuilder> {

	private NotificationBuilder() {}

	/**
	 * Factory method for a new instance.
	 *
	 * @return A new instance, never null.
	 */
	public static NotificationBuilder create() {
		return new NotificationBuilder();
	}

	@Override
	protected Notification instantiate() {
		return new Notification();
	}

	/**
	 * Builder method, configures the initial text of the {@link Notification} after building.
	 *
	 * @see Notification#setText(String)
	 * @param text The text to set; might be null.
	 * @return this
	 */
	public NotificationBuilder setText(String text) {
		return configure(notification -> notification.setText(text));
	}

	/**
	 * Builder method, sets the position of the {@link Notification}.
	 *
	 * @see Notification#setPosition(Notification.Position)
	 * @param position The position; might <b>not</b> be null.
	 * @return this
	 */
	public NotificationBuilder setPosition(Notification.Position position) {
		return configure(notification -> notification.setPosition(position));
	}

	/**
	 * Builder method, sets the duration of the {@link Notification}.
	 *
	 * @see Notification#setPosition(Notification.Position)
	 * @param duration The duration in milliseconds.
	 * @return this
	 */
	public NotificationBuilder setDuration(int duration) {
		return configure(notification -> notification.setDuration(duration));
	}

	/**
	 * Creates a new {@link Notification} instance using {@link #instantiate()}, applies all currently contained
	 * {@link Configurer}s to it, opens and returns it.
	 *
	 * @return A new {@link Notification} instance, fully configured, never null
	 */
	public Notification open() {
		Notification notification = build();
		notification.open();
		return notification;
	}
}
