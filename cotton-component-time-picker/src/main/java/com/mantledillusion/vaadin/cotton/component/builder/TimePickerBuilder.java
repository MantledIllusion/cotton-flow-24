package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.timepicker.TimePicker;


/**
 * {@link ConfigurationBuilder} for {@link TimePicker}s.
 */
public class TimePickerBuilder extends AbstractTimePickerBuilder<TimePicker, TimePickerBuilder> {

	private TimePickerBuilder() {}

	@Override
	protected TimePicker instantiate() {
		return new TimePicker();
	}

	/**
	 * Factory method for a new instance.
	 *
	 * @return A new instance, never null.
	 */
	public static TimePickerBuilder create() {
		return new TimePickerBuilder();
	}

}