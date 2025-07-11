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
public class NotificationBuilder extends AbstractNotificationBuilder<Notification, NotificationBuilder> {

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

}
