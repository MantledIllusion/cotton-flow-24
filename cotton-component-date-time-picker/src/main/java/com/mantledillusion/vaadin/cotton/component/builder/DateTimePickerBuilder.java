package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.datepicker.DatePicker.DatePickerI18n;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;

import java.time.LocalDateTime;
import java.util.Locale;

/**
 * {@link ConfigurationBuilder} for {@link DateTimePicker}s.
 */
public class DateTimePickerBuilder extends AbstractComponentBuilder<DateTimePicker, DateTimePickerBuilder> implements
		FocusableBuilder<DateTimePicker, DateTimePickerBuilder>,
		HasAutoOpenBuilder<DateTimePicker, DateTimePickerBuilder>,
		HasElementBuilder<DateTimePicker, DateTimePickerBuilder>,
		HasEnabledBuilder<DateTimePicker, DateTimePickerBuilder>,
		HasLabelBuilder<DateTimePicker, DateTimePickerBuilder>,
		HasSizeBuilder<DateTimePicker, DateTimePickerBuilder>,
		HasStyleBuilder<DateTimePicker, DateTimePickerBuilder>,
		HasTooltipBuilder<DateTimePicker, DateTimePickerBuilder>,
		HasValidatorBuilder<DateTimePicker, LocalDateTime, DateTimePickerBuilder>,
		HasValueBuilder<DateTimePicker, LocalDateTime, AbstractField.ComponentValueChangeEvent<DateTimePicker, LocalDateTime>, DateTimePickerBuilder> {

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

	/**
	 * Builder method, configures the i18n properties.
	 * 
	 * @see DateTimePicker#setDatePickerI18n(DatePickerI18n)
	 * @param i18n
	 *            The {@link DatePickerI18n}; might <b>not</b> be null.
	 * @return this
	 */
	public DateTimePickerBuilder setI18n(DatePickerI18n i18n) {
		return configure(datePicker -> datePicker.setDatePickerI18n(i18n));
	}

	/**
	 * Builder method, configures the {@link Locale} to set.
	 * 
	 * @see DateTimePicker#setLocale(Locale)
	 * @param locale
	 *            The locale to set; might <b>not</b> be null. be null.
	 * @return this
	 */
	public DateTimePickerBuilder setLocale(Locale locale) {
		return configure(datePicker -> datePicker.setLocale(locale));
	}

	/**
	 * Builder method, configures the minimal date.
	 * 
	 * @see DateTimePicker#setMin(LocalDateTime)
	 * @param date
	 *            The minimal {@link LocalDateTime}; might be null.
	 * @return this
	 */
	public DateTimePickerBuilder setMinDate(LocalDateTime date) {
		return configure(datePicker -> datePicker.setMin(date));
	}

	/**
	 * Builder method, configures the maximal date.
	 * 
	 * @see DateTimePicker#setMax(LocalDateTime)
	 * @param date
	 *            The maximal {@link LocalDateTime}; might be null.
	 * @return this
	 */
	public DateTimePickerBuilder setMaxDate(LocalDateTime date) {
		return configure(datePicker -> datePicker.setMax(date));
	}

	/**
	 * Builder method, configures the placeholder text that might be displayed when
	 * nothing is selected.
	 * 
	 * @see DateTimePicker#setDatePlaceholder(String)
	 * @param placeholder The placeholder to set; might be null.
	 * @return this
	 */
	public DateTimePickerBuilder setDatePlaceholder(String placeholder) {
		return configure(datePicker -> datePicker.setDatePlaceholder(placeholder));
	}

	/**
	 * Builder method, configures the placeholder text that might be displayed when
	 * nothing is selected.
	 *
	 * @see DateTimePicker#setTimePlaceholder(String)
	 * @param placeholder The placeholder to set; might be null.
	 * @return this
	 */
	public DateTimePickerBuilder setTimePlaceholder(String placeholder) {
		return configure(datePicker -> datePicker.setTimePlaceholder(placeholder));
	}

	/**
	 * Builder method, configures the selection to be required.
	 * 
	 * @see DateTimePicker#setRequiredIndicatorVisible(boolean)
	 * @param required
	 *            True if the week number should be marked required, false otherwise.
	 * @return this
	 */
	public DateTimePickerBuilder setRequired(boolean required) {
		return configure(datePicker -> datePicker.setRequiredIndicatorVisible(required));
	}

	/**
	 * Builder method, configures the week number to be visible.
	 * <p>
	 * Set true to display ISO-8601 week numbers in the calendar.
	 * <p>
	 * Notice that displaying week numbers is only supported when
	 * i18n.firstDayOfWeek is 1 (Monday).
	 * 
	 * @see DateTimePicker#setWeekNumbersVisible(boolean)
	 * @param visible
	 *            True if the week number should be visible, false otherwise.
	 * @return this
	 */
	public DateTimePickerBuilder setWeekNumbersVisible(boolean visible) {
		return configure(datePicker -> datePicker.setWeekNumbersVisible(visible));
	}
}