package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;

public abstract class AbstractButtonBuilder<C extends Button, B extends AbstractButtonBuilder<C, B>> extends AbstractComponentBuilder<C, B> implements
        ClickNotifierBuilder<C, Button, B>,
        FocusableBuilder<C, Button, B>,
        HasAriaLabelBuilder<C, B>,
        HasElementBuilder<C, B>,
        HasEnabledBuilder<C, B>,
        HasPrefixBuilder<C, B>,
        HasSizeBuilder<C, B>,
        HasStyleBuilder<C, B>,
        HasSuffixBuilder<C, B>,
        HasTextBuilder<C, B>,
        HasThemeVariantBuilder<C, ButtonVariant, B>,
        HasTooltipBuilder<C, B> {

    /**
     * Builder method, configures whether the {@link Button} has to be focused
     * automatically when the field's page finished loading.
     *
     * @param autofocus True if the {@link Button} has to receive the autofocus, false otherwise.
     * @return this
     * @see Button#setAutofocus(boolean)
     */
    public B setAutofocus(boolean autofocus) {
        return configure(button -> button.setAutofocus(autofocus));
    }

    /**
     * Builder method, configures the {@link Component} that should be displayed as
     * icon.
     *
     * @param icon The {@link Component} to display as icon; might be null.
     * @return this
     * @see Button#setIcon(Component)
     */
    public B setIcon(Component icon) {
        return configure(button -> button.setIcon(icon));
    }

    /**
     * Builder method, configures whether the {@link Button}'s icon should be
     * displayed after its text.
     *
     * @param iconAfterText True if the should be displayed after the text, false otherwise.
     * @return this
     * @see Button#setIconAfterText(boolean)
     */
    public B setIconAfterText(boolean iconAfterText) {
        return configure(button -> button.setIconAfterText(iconAfterText));
    }
}
