package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.timepicker.TimePicker;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Locale;

public abstract class AbstractTimePickerBuilder<C extends TimePicker, B extends AbstractTimePickerBuilder<C, B>> extends AbstractComponentBuilder<C, B> implements
        FocusableBuilder<C, TimePicker, B>,
        HasAllowedCharPatternBuilder<C, B>,
        HasAutoOpenBuilder<C, B>,
        HasElementBuilder<C, B>,
        HasEnabledBuilder<C, B>,
        HasLabelBuilder<C, B>,
        HasPlaceholderBuilder<C, B>,
        HasPrefixBuilder<C, B>,
        HasSizeBuilder<C, B>,
        HasStyleBuilder<C, B>,
        HasTooltipBuilder<C, B>,
        HasValidatorBuilder<C, LocalTime, B>,
        HasValueBuilder<C, TimePicker, LocalTime, AbstractField.ComponentValueChangeEvent<TimePicker, LocalTime>, B> {

    /**
     * Builder method, configures the {@link Locale} to set.
     *
     * @param locale The locale to set; might <b>not</b> be null. be null.
     * @return this
     * @see TimePicker#setLocale(Locale)
     */
    public B setLocale(Locale locale) {
        return configure(timePicker -> timePicker.setLocale(locale));
    }

    /**
     * Builder method, configures the minimal date.
     *
     * @param time The minimal time; might be null.
     * @return this
     * @see TimePicker#setMin(LocalTime)
     */
    public B setMinDate(LocalTime time) {
        return configure(timePicker -> timePicker.setMin(time));
    }

    /**
     * Builder method, configures the maximal date.
     *
     * @param time The maximal time; might be null.
     * @return this
     * @see TimePicker#setMax(LocalTime)
     */
    public B setMaxDate(LocalTime time) {
        return configure(timePicker -> timePicker.setMax(time));
    }

    /**
     * Builder method, configures the selection to be required.
     *
     * @param required True if the week number should be marked required, false otherwise.
     * @return this
     * @see TimePicker#setRequired(boolean)
     */
    public B setRequired(boolean required) {
        return configure(timePicker -> timePicker.setRequired(required));
    }

    /**
     * Builder method, configures the duration in between picker steps.
     *
     * @param step The duration between steps, evenly dividing an hour a day; might <b>not</b> be null.
     * @return this
     * @see TimePicker#setStep(Duration)
     */
    public B setWeekNumbersVisible(Duration step) {
        return configure(timePicker -> timePicker.setStep(step));
    }

    /**
     * Builder method, configures a listener for {@link TimePicker.InvalidChangeEvent}s.
     *
     * @param listener The listener to add; might <b>not</b> be null.
     * @return this
     */
    public B addInvalidChangeListener(ComponentEventListener<TimePicker.InvalidChangeEvent> listener) {
        return configure(datePicker -> datePicker.addInvalidChangeListener(listener));
    }
}
