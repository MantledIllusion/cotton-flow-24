package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.details.DetailsVariant;

public abstract class AbstractDetailsBuilder<C extends Details, B extends AbstractDetailsBuilder<C, B>> extends AbstractComponentBuilder<C, B> implements
        HasComponentsBuilder<C, B>,
        HasElementBuilder<C, B>,
        HasSizeBuilder<C, B>,
        HasStyleBuilder<C, B>,
        HasThemeVariantBuilder<C, DetailsVariant, B>,
        HasTooltipBuilder<C, B> {

    /**
     * Builder method, sets the summary.
     *
     * @param summary The summary to set; might be null.
     * @return this
     * @see Details#setSummary(Component)
     */
    public B setSummary(Component summary) {
        return configure(details -> details.setSummary(summary));
    }

    /**
     * Builder method, sets the summary.
     *
     * @param summary The summary to set; might be null.
     * @return this
     * @see Details#setSummaryText(String)
     */
    public B setSummaryText(String summary) {
        return configure(details -> details.setSummaryText(summary));
    }

    /**
     * Builder methods, sets whether the details are opened.
     *
     * @param opened True if the content should be shown, false otherwise.
     * @return this
     * @see Details#setOpened(boolean)
     */
    public B setOpened(boolean opened) {
        return configure(details -> details.setOpened(opened));
    }
}
