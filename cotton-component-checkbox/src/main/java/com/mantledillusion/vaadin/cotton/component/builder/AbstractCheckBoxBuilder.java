package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.checkbox.Checkbox;

public abstract class AbstractCheckBoxBuilder<C extends Checkbox, B extends AbstractCheckBoxBuilder<C, B>> extends AbstractComponentBuilder<C, B> implements
        ClickNotifierBuilder<C, Checkbox, B>,
        FocusableBuilder<C, Checkbox, B>,
        HasAriaLabelBuilder<C, B>,
        HasElementBuilder<C, B>,
        HasEnabledBuilder<C, B>,
        HasLabelBuilder<C, B>,
        HasSizeBuilder<C, B>,
        HasStyleBuilder<C, B>,
        HasTooltipBuilder<C, B>,
        HasValueBuilder<C, Checkbox, Boolean, AbstractField.ComponentValueChangeEvent<Checkbox, Boolean>, B> {

    /**
     * Builder method, configures whether the {@link Checkbox} should be input
     * focussed after the page finishes loading.
     *
     * @param autofocus True if the {@link Checkbox} should be focussed, false otherwise.
     * @return this
     * @see Checkbox#setAutofocus(boolean)
     */
    public B setAutofocus(boolean autofocus) {
        return configure(checkBox -> checkBox.setAutofocus(autofocus));
    }

    /**
     * Builder method, configures the indeterminate state.
     *
     * @param indeterminate True if the {@link Checkbox} should be indeterminate, false
     *                      otherwise.
     * @return this
     * @see Checkbox#setIndeterminate(boolean)
     */
    public B setIndeterminate(boolean indeterminate) {
        return configure(checkBox -> checkBox.setIndeterminate(indeterminate));
    }
}
