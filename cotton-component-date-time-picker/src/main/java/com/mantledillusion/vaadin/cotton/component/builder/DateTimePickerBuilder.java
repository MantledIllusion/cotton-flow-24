package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;

import com.vaadin.flow.component.datetimepicker.DateTimePicker;


/**
 * {@link ConfigurationBuilder} for {@link DateTimePicker}s.
 */
public class DateTimePickerBuilder extends AbstractDateTimePickerBuilder<DateTimePicker, DateTimePickerBuilder> {

	private DateTimePickerBuilder() {}

	/**
	 * Factory method for a new instance.
	 *
	 * @return A new instance, never null.
	 */
	public static DateTimePickerBuilder create() {
		return new DateTimePickerBuilder();
	}

	@Override
	protected DateTimePicker instantiate() {
		return new DateTimePicker();
	}

}