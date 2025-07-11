package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.datepicker.DatePicker;

import java.time.LocalDate;
import java.util.Locale;

public abstract class AbstractDatePickerBuilder<C extends DatePicker, B extends AbstractDatePickerBuilder<C, B>> extends AbstractComponentBuilder<C, B> implements
        FocusableBuilder<C, DatePicker, B>,
        HasAutoOpenBuilder<C, B>,
        HasAllowedCharPatternBuilder<C, B>,
        HasElementBuilder<C, B>,
        HasEnabledBuilder<C, B>,
        HasLabelBuilder<C, B>,
        HasPlaceholderBuilder<C, B>,
        HasPrefixBuilder<C, B>,
        HasSizeBuilder<C, B>,
        HasStyleBuilder<C, B>,
        HasTooltipBuilder<C, B>,
        HasValidatorBuilder<C, LocalDate, B>,
        HasValueBuilder<C, DatePicker, LocalDate, AbstractField.ComponentValueChangeEvent<DatePicker, LocalDate>, B> {

    /**
     * Builder method, configures the i18n properties.
     *
     * @see DatePicker#setI18n(DatePicker.DatePickerI18n)
     * @param i18n The {@link DatePicker.DatePickerI18n}; might <b>not</b> be null.
     * @return this
     */
    public B setI18n(DatePicker.DatePickerI18n i18n) {
        return configure(datePicker -> datePicker.setI18n(i18n));
    }

    /**
     * Builder method, configures the initial date.
     *
     * @see DatePicker#setInitialPosition(LocalDate)
     * @param date The initial {@link LocalDate}; might be null.
     * @return this
     */
    public B setInitialDate(LocalDate date) {
        return configure(datePicker -> datePicker.setInitialPosition(date));
    }

    /**
     * Builder method, configures the {@link Locale} to set.
     *
     * @see DatePicker#setLocale(Locale)
     * @param locale The locale to set; might <b>not</b> be null. be null.
     * @return this
     */
    public B setLocale(Locale locale) {
        return configure(datePicker -> datePicker.setLocale(locale));
    }

    /**
     * Builder method, configures the minimal date.
     *
     * @see DatePicker#setMin(LocalDate)
     * @param date The minimal {@link LocalDate}; might be null.
     * @return this
     */
    public B setMinDate(LocalDate date) {
        return configure(datePicker -> datePicker.setMin(date));
    }

    /**
     * Builder method, configures the maximal date.
     *
     * @see DatePicker#setMax(LocalDate)
     * @param date The maximal {@link LocalDate}; might be null.
     * @return this
     */
    public B setMaxDate(LocalDate date) {
        return configure(datePicker -> datePicker.setMax(date));
    }

    /**
     * Builder method, configures the selection to be required.
     *
     * @see DatePicker#setRequired(boolean)
     * @param required True if the week number should be marked required, false otherwise.
     * @return this
     */
    public B setRequired(boolean required) {
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
     * @param visible True if the week number should be visible, false otherwise.
     * @return this
     */
    public B setWeekNumbersVisible(boolean visible) {
        return configure(datePicker -> datePicker.setWeekNumbersVisible(visible));
    }

    /**
     * Builder method, configures a listener for {@link DatePicker.InvalidChangeEvent}s.
     *
     * @see DatePicker#addInvalidChangeListener(ComponentEventListener)
     * @param listener The listener to add; might <b>not</b> be null.
     * @return this
     */
    public B addInvalidChangeListener(ComponentEventListener<DatePicker.InvalidChangeEvent> listener) {
        return configure(datePicker -> datePicker.addInvalidChangeListener(listener));
    }

    /**
     * Builder method, configures a listener for {@link DatePicker.OpenedChangeEvent}s.
     *
     * @see DatePicker#addOpenedChangeListener(ComponentEventListener)
     * @param listener The listener to add; might <b>not</b> be null.
     * @return this
     */
    public B addOpenedChangeListener(ComponentEventListener<DatePicker.OpenedChangeEvent> listener) {
        return configure(datePicker -> datePicker.addOpenedChangeListener(listener));
    }
}
