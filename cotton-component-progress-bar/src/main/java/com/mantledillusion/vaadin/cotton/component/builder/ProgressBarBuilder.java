package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.progressbar.ProgressBar;

/**
 * {@link ConfigurationBuilder} for {@link ProgressBar}s.
 */
public class ProgressBarBuilder extends AbstractProgressBarBuilder<ProgressBar, ProgressBarBuilder> {

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

}
