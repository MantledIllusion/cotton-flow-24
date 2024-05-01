package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.HasElementBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.HasSizeBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.HasStyleBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.HasThemeVariantBuilder;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.progressbar.ProgressBarVariant;

/**
 * {@link ConfigurationBuilder} for {@link ProgressBar}s.
 */
public class ProgressBarBuilder extends AbstractComponentBuilder<ProgressBar, ProgressBarBuilder> implements
        HasElementBuilder<ProgressBar, ProgressBarBuilder>,
        HasSizeBuilder<ProgressBar, ProgressBarBuilder>,
        HasStyleBuilder<ProgressBar, ProgressBarBuilder>,
        HasThemeVariantBuilder<ProgressBar, ProgressBarVariant, ProgressBarBuilder> {

    private ProgressBarBuilder() {}

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static ProgressBarBuilder create() {
        return new ProgressBarBuilder();
    }

    @Override
    protected ProgressBar instantiate() {
        return new ProgressBar();
    }

    /**
     * Builder method, configures the {@link ProgressBar} range's minimum value.
     *
     * @see ProgressBar#setMin(double)
     * @param min
     *            The min value of the {@link ProgressBar}'s range.
     * @return this
     */
    public ProgressBarBuilder setMin(double min) {
        return configure(progressBar -> progressBar.setMin(min));
    }

    /**
     * Builder method, configures the {@link ProgressBar} range's maximum value.
     *
     * @see ProgressBar#setMax(double)
     * @param max
     *            The max value of the {@link ProgressBar}'s range.
     * @return this
     */
    public ProgressBarBuilder setMax(double max) {
        return configure(progressBar -> progressBar.setMax(max));
    }

    /**
     * Builder method, configures the {@link ProgressBar}'s current value.
     *
     * @see ProgressBar#setValue(double)
     * @param value
     *            The value of the {@link ProgressBar}.
     * @return this
     */
    public ProgressBarBuilder setValue(double value) {
        return configure(progressBar -> progressBar.setValue(value));
    }

    /**
     * Builder method, configures the {@link ProgressBar} to be indeterminate.
     *
     * @see ProgressBar#setIndeterminate(boolean)
     * @param indeterminate
     *            True if the {@link ProgressBar} should be indeterminate, false otherwise.
     * @return this
     */
    public ProgressBarBuilder setIndeterminate(boolean indeterminate) {
        return configure(progressBar -> progressBar.setIndeterminate(indeterminate));
    }
}
