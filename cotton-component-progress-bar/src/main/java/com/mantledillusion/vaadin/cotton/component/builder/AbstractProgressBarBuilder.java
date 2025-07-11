package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.mixin.HasElementBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.HasSizeBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.HasStyleBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.HasThemeVariantBuilder;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.progressbar.ProgressBarVariant;

public abstract class AbstractProgressBarBuilder<C extends ProgressBar, B extends AbstractProgressBarBuilder<C, B>> extends AbstractComponentBuilder<C, B> implements
        HasElementBuilder<C, B>,
        HasSizeBuilder<C, B>,
        HasStyleBuilder<C, B>,
        HasThemeVariantBuilder<C, ProgressBarVariant, B> {

    /**
     * Builder method, configures the {@link ProgressBar} range's minimum value.
     *
     * @param min The min value of the {@link ProgressBar}'s range.
     * @return this
     * @see ProgressBar#setMin(double)
     */
    public B setMin(double min) {
        return configure(progressBar -> progressBar.setMin(min));
    }

    /**
     * Builder method, configures the {@link ProgressBar} range's maximum value.
     *
     * @param max The max value of the {@link ProgressBar}'s range.
     * @return this
     * @see ProgressBar#setMax(double)
     */
    public B setMax(double max) {
        return configure(progressBar -> progressBar.setMax(max));
    }

    /**
     * Builder method, configures the {@link ProgressBar}'s current value.
     *
     * @param value The value of the {@link ProgressBar}.
     * @return this
     * @see ProgressBar#setValue(double)
     */
    public B setValue(double value) {
        return configure(progressBar -> progressBar.setValue(value));
    }

    /**
     * Builder method, configures the {@link ProgressBar} to be indeterminate.
     *
     * @param indeterminate True if the {@link ProgressBar} should be indeterminate, false otherwise.
     * @return this
     * @see ProgressBar#setIndeterminate(boolean)
     */
    public B setIndeterminate(boolean indeterminate) {
        return configure(progressBar -> progressBar.setIndeterminate(indeterminate));
    }
}
