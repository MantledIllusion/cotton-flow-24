package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.mantledillusion.vaadin.cotton.i18n.I18N;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.datepicker.DatePicker.DatePickerI18n;

import java.time.LocalDate;
import java.util.Locale;

/**
 * {@link ConfigurationBuilder} for {@link DatePicker}s.
 */
public class DatePickerBuilder extends AbstractComponentBuilder<DatePicker, DatePickerBuilder> implements
		FocusableBuilder<DatePicker, DatePickerBuilder>,
		HasElementBuilder<DatePicker, DatePickerBuilder>,
		HasEnabledBuilder<DatePicker, DatePickerBuilder>,
		HasLabelBuilder<DatePicker, DatePickerBuilder>,
		HasSizeBuilder<DatePicker, DatePickerBuilder>,
		HasStyleBuilder<DatePicker, DatePickerBuilder>,
		HasValidatorBuilder<DatePicker, LocalDate, DatePickerBuilder>,
		HasValueBuilder<DatePicker, LocalDate, AbstractField.ComponentValueChangeEvent<DatePicker, LocalDate>, DatePickerBuilder> {

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

	/**
	 * Builder method, configures the i18n properties.
	 * 
	 * @see DatePicker#setI18n(DatePickerI18n)
	 * @param i18n
	 *            The {@link DatePickerI18n}; might <b>not</b> be null.
	 * @return this
	 */
	public DatePickerBuilder setI18n(DatePickerI18n i18n) {
		return configure(datePicker -> datePicker.setI18n(i18n));
	}

	/**
	 * Builder method, configures the initial date.
	 * 
	 * @see DatePicker#setInitialPosition(LocalDate)
	 * @param date
	 *            The initial {@link LocalDate}; might be null.
	 * @return this
	 */
	public DatePickerBuilder setInitialDate(LocalDate date) {
		return configure(datePicker -> datePicker.setInitialPosition(date));
	}

	/**
	 * Builder method, configures the {@link Locale} to set.
	 * 
	 * @see DatePicker#setLocale(Locale)
	 * @param locale
	 *            The locale to set; might <b>not</b> be null. be null.
	 * @return this
	 */
	public DatePickerBuilder setLocale(Locale locale) {
		return configure(datePicker -> datePicker.setLocale(locale));
	}

	/**
	 * Builder method, configures the minimal date.
	 * 
	 * @see DatePicker#setMin(LocalDate)
	 * @param date
	 *            The minimal {@link LocalDate}; might be null.
	 * @return this
	 */
	public DatePickerBuilder setMinDate(LocalDate date) {
		return configure(datePicker -> datePicker.setMin(date));
	}

	/**
	 * Builder method, configures the maximal date.
	 * 
	 * @see DatePicker#setMax(LocalDate)
	 * @param date
	 *            The maximal {@link LocalDate}; might be null.
	 * @return this
	 */
	public DatePickerBuilder setMaxDate(LocalDate date) {
		return configure(datePicker -> datePicker.setMax(date));
	}

	/**
	 * Builder method, configures the placeholder text that might be displayed when
	 * nothing is selected.
	 * 
	 * @see DatePicker#setPlaceholder(String)
	 * @param msgId A placeholder text or message ID to translate; might be null.
	 * @param indexedMessageParameters Optional parameters to replace at their index in the message; might be null
	 * @return this
	 */
	public DatePickerBuilder setPlaceholder(Object msgId, Object... indexedMessageParameters) {
		return configure(datePicker -> datePicker.setPlaceholder(I18N.getTranslation(msgId, indexedMessageParameters)));
	}

	/**
	 * Builder method, configures the selection to be required.
	 * 
	 * @see DatePicker#setRequired(boolean)
	 * @param required
	 *            True if the week number should be marked required, false
	 *            otherwise.
	 * @return this
	 */
	public DatePickerBuilder setRequired(boolean required) {
		return configure(datePicker -> datePicker.setRequired(required));
	}

	/**
	 * Builder method, configures the week number to be visible.
	 * <p>
	 * Set true to display ISO-8601 week numbers in the calendar.
	 * <p>
	 * Notice that displaying week numbers is only supported when
	 * i18n.firstDayOfWeek is 1 (Monday).
	 * 
	 * @see DatePicker#setWeekNumbersVisible(boolean)
	 * @param visible
	 *            True if the week number should be visible, false otherwise.
	 * @return this
	 */
	public DatePickerBuilder setWeekNumbersVisible(boolean visible) {
		return configure(datePicker -> datePicker.setWeekNumbersVisible(visible));
	}

	/**
	 * Builder method, configures a listener for {@link DatePicker.InvalidChangeEvent}s.
	 *
	 * @param listener
	 * 			The listener to add; might <b>not</b> be null.
	 * @return this
	 */
	public DatePickerBuilder addInvalidChangeListener(ComponentEventListener<DatePicker.InvalidChangeEvent> listener) {
		return configure(datePicker -> datePicker.addInvalidChangeListener(listener));
	}

	/**
	 * Builder method, configures a listener for {@link DatePicker.OpenedChangeEvent}s.
	 *
	 * @param listener
	 * 			The listener to add; might <b>not</b> be null.
	 * @return this
	 */
	public DatePickerBuilder addOpenedChangeListener(ComponentEventListener<DatePicker.OpenedChangeEvent> listener) {
		return configure(datePicker -> datePicker.addOpenedChangeListener(listener));
	}
}