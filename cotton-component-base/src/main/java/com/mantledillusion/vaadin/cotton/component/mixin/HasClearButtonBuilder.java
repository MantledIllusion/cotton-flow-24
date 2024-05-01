package com.mantledillusion.vaadin.cotton.component.mixin;

import com.vaadin.flow.component.shared.HasClearButton;

public interface HasClearButtonBuilder<C extends HasClearButton, B extends HasClearButtonBuilder<C, B>> extends
        HasElementBuilder<C, B> {

    /**
     * Builder method, sets whether the clear button is visible.
     *
     * @see HasClearButton#setClearButtonVisible(boolean)
     * @param clearButtonVisible Whether the clear button should be visible.
     * @return this
     */
    default B setClearButtonVisible(boolean clearButtonVisible) {
        return configure(hasClearButton -> hasClearButton.setClearButtonVisible(clearButtonVisible));
    }
}
