package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.mantledillusion.vaadin.cotton.i18n.I18N;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.textfield.TextFieldBase;

@SuppressWarnings("unused")
abstract class AbstractTextFieldBaseBuilder<C extends TextFieldBase<C, T>, T, B extends AbstractTextFieldBaseBuilder<C, T, B>> extends AbstractComponentBuilder<C, B> implements
        CompositionNotifierBuilder<C, B>,
        FocusableBuilder<C, B>,
        HasAutocapitalizeBuilder<C, B>,
        HasAutocompleteBuilder<C, B>,
        HasAutocorrectBuilder<C, B>,
        HasClearButtonBuilder<C, B>,
        HasLabelBuilder<C, B>,
        HasPrefixBuilder<C, B>,
        HasSizeBuilder<C, B>,
        HasStyleBuilder<C, B>,
        HasSuffixBuilder<C, B>,
        HasTooltipBuilder<C, B>,
        HasValidatorBuilder<C, T, B>,
        HasValueBuilder<C, T, AbstractField.ComponentValueChangeEvent<C, T>, B>,
        HasValueChangeModeBuilder<C, B>,
        InputNotifierBuilder<C, B>,
        KeyNotifierBuilder<C, B> {

    /**
     * Builder method, configures whether the {@link TextFieldBase} has to be focused automatically when the field's
     * page finished loading.
     *
     * @see TextFieldBase#setAutofocus(boolean)
     * @param autofocus True if the {@link TextFieldBase} has to receive the autofocus, false otherwise.
     * @return this
     */
    public B setAutofocus(boolean autofocus) {
        return configure(textField -> textField.setAutofocus(autofocus));
    }

    /**
     * Builder method, configures the placeholder text to show as long as the {@link TextFieldBase} is empty.
     *
     * @see TextFieldBase#setPlaceholder(String)
     * @param msgId A placeholder text or message ID to translate; might be null.
     * @param indexedMessageParameters Optional parameters to replace at their index in the message; might be null.
     * @return this
     */
    public B setPlaceholder(Object msgId, Object... indexedMessageParameters) {
        return configure(textField -> textField.setPlaceholder(I18N.getTranslation(msgId, indexedMessageParameters)));
    }

    /**
     * Builder method, configures the selection to be required.
     *
     * @see TextFieldBase#setRequired(boolean)
     * @param required True if the week number should be marked required, false otherwise.
     * @return this
     */
    public B setRequired(boolean required) {
        return configure(textField -> textField.setRequired(required));
    }
}
