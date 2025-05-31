package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.timepicker.TimePicker;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Locale;

/**
 * {@link ConfigurationBuilder} for {@link TimePicker}s.
 */
public class TimePickerBuilder extends AbstractComponentBuilder<TimePicker, TimePickerBuilder> implements
		FocusableBuilder<TimePicker, TimePickerBuilder>,
		HasAllowedCharPatternBuilder<TimePicker, TimePickerBuilder>,
		HasAutoOpenBuilder<TimePicker, TimePickerBuilder>,
		HasElementBuilder<TimePicker, TimePickerBuilder>,
		HasEnabledBuilder<TimePicker, TimePickerBuilder>,
		HasLabelBuilder<TimePicker, TimePickerBuilder>,
		HasPlaceholderBuilder<TimePicker, TimePickerBuilder>,
		HasPrefixBuilder<TimePicker, TimePickerBuilder>,
		HasSizeBuilder<TimePicker, TimePickerBuilder>,
		HasStyleBuilder<TimePicker, TimePickerBuilder>,
		HasTooltipBuilder<TimePicker, TimePickerBuilder>,
		HasValidatorBuilder<TimePicker, LocalTime, TimePickerBuilder>,
		HasValueBuilder<TimePicker, LocalTime, AbstractField.ComponentValueChangeEvent<TimePicker, LocalTime>, TimePickerBuilder> {

	private TimePickerBuilder() {}

	/**
	 * Factory method for a new instance.
	 *
	 * @return A new instance, never null.
	 */
	public static TimePickerBuilder create() {
		return new TimePickerBuilder();
	}

	@Override
	protected TimePicker instantiate() {
		return new TimePicker();
	}

	/**
	 * Builder method, configures the {@link Locale} to set.
	 * 
	 * @see TimePicker#setLocale(Locale)
	 * @param locale
	 *            The locale to set; might <b>not</b> be null. be null.
	 * @return this
	 */
	public TimePickerBuilder setLocale(Locale locale) {
		return configure(timePicker -> timePicker.setLocale(locale));
	}

	/**
	 * Builder method, configures the minimal date.
	 * 
	 * @see TimePicker#setMin(LocalTime)
	 * @param time
	 *            The minimal time; might be null.
	 * @return this
	 */
	public TimePickerBuilder setMinDate(LocalTime time) {
		return configure(timePicker -> timePicker.setMin(time));
	}

	/**
	 * Builder method, configures the maximal date.
	 * 
	 * @see TimePicker#setMax(LocalTime)
	 * @param time
	 *            The maximal time; might be null.
	 * @return this
	 */
	public TimePickerBuilder setMaxDate(LocalTime time) {
		return configure(timePicker -> timePicker.setMax(time));
	}

	/**
	 * Builder method, configures the selection to be required.
	 * 
	 * @see TimePicker#setRequired(boolean)
	 * @param required
	 *            True if the week number should be marked required, false otherwise.
	 * @return this
	 */
	public TimePickerBuilder setRequired(boolean required) {
		return configure(timePicker -> timePicker.setRequired(required));
	}

	/**
	 * Builder method, configures the duration in between picker steps.
	 * 
	 * @see TimePicker#setStep(Duration)
	 * @param step
	 *            The duration between steps, evenly dividing an hour a day; might <b>not</b> be null.
	 * @return this
	 */
	public TimePickerBuilder setWeekNumbersVisible(Duration step) {
		return configure(timePicker -> timePicker.setStep(step));
	}

	/**
	 * Builder method, configures a listener for {@link TimePicker.InvalidChangeEvent}s.
	 *
	 * @param listener
	 * 			The listener to add; might <b>not</b> be null.
	 * @return this
	 */
	public TimePickerBuilder addInvalidChangeListener(ComponentEventListener<TimePicker.InvalidChangeEvent> listener) {
		return configure(datePicker -> datePicker.addInvalidChangeListener(listener));
	}
}