package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;

import java.time.LocalDateTime;
import java.util.Locale;

public abstract class AbstractDateTimePickerBuilder<C extends DateTimePicker, B extends AbstractDateTimePickerBuilder<C, B>> extends AbstractComponentBuilder<C, B> implements
        FocusableBuilder<C, DateTimePicker, B>,
        HasAutoOpenBuilder<C, B>,
        HasElementBuilder<C, B>,
        HasEnabledBuilder<C, B>,
        HasLabelBuilder<C, B>,
        HasSizeBuilder<C, B>,
        HasStyleBuilder<C, B>,
        HasTooltipBuilder<C, B>,
        HasValidatorBuilder<C, LocalDateTime, B>,
        HasValueBuilder<C, DateTimePicker, LocalDateTime, AbstractField.ComponentValueChangeEvent<DateTimePicker, LocalDateTime>, B> {

    /**
     * Builder method, configures the i18n properties.
     *
     * @see DateTimePicker#setDatePickerI18n(DatePicker.DatePickerI18n)
     * @param i18n The {@link DatePicker.DatePickerI18n}; might <b>not</b> be null.
     * @return this
     */
    public B setI18n(DatePicker.DatePickerI18n i18n) {
        return configure(datePicker -> datePicker.setDatePickerI18n(i18n));
    }

    /**
     * Builder method, configures the {@link Locale} to set.
     *
     * @see DateTimePicker#setLocale(Locale)
     * @param locale The locale to set; might <b>not</b> be null. be null.
     * @return this
     */
    public B setLocale(Locale locale) {
        return configure(datePicker -> datePicker.setLocale(locale));
    }

    /**
     * Builder method, configures the minimal date.
     *
     * @see DateTimePicker#setMin(LocalDateTime)
     * @param date The minimal {@link LocalDateTime}; might be null.
     * @return this
     */
    public B setMinDate(LocalDateTime date) {
        return configure(datePicker -> datePicker.setMin(date));
    }

    /**
     * Builder method, configures the maximal date.
     *
     * @see DateTimePicker#setMax(LocalDateTime)
     * @param date The maximal {@link LocalDateTime}; might be null.
     * @return this
     */
    public B setMaxDate(LocalDateTime date) {
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
    public B setDatePlaceholder(String placeholder) {
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
    public B setTimePlaceholder(String placeholder) {
        return configure(datePicker -> datePicker.setTimePlaceholder(placeholder));
    }

    /**
     * Builder method, configures the selection to be required.
     *
     * @see DateTimePicker#setRequiredIndicatorVisible(boolean)
     * @param required True if the week number should be marked required, false otherwise.
     * @return this
     */
    public B setRequired(boolean required) {
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
     * @param visible True if the week number should be visible, false otherwise.
     * @return this
     */
    public B setWeekNumbersVisible(boolean visible) {
        return configure(datePicker -> datePicker.setWeekNumbersVisible(visible));
    }
}
