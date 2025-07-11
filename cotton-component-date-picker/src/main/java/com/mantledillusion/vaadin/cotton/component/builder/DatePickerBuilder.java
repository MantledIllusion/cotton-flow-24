package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.datepicker.DatePicker;


/**
 * {@link ConfigurationBuilder} for {@link DatePicker}s.
 */
public class DatePickerBuilder extends AbstractDatePickerBuilder<DatePicker, DatePickerBuilder> {

	private DatePickerBuilder() {}

	/**
	 * Factory method for a new instance.
	 *
	 * @return A new instance, never null.
	 */
	public static DatePickerBuilder create() {
		return new DatePickerBuilder();
	}

	@Override
	protected DatePicker instantiate() {
		return new DatePicker();
	}

}