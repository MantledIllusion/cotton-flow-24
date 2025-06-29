package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.details.DetailsVariant;

/**
 * {@link ConfigurationBuilder} for {@link Details}s.
 */
@SuppressWarnings("unused")
public class DetailsBuilder extends AbstractComponentBuilder<Details, DetailsBuilder> implements
        HasComponentsBuilder<Details, DetailsBuilder>,
        HasElementBuilder<Details, DetailsBuilder>,
        HasSizeBuilder<Details, DetailsBuilder>,
        HasStyleBuilder<Details, DetailsBuilder>,
        HasThemeVariantBuilder<Details, DetailsVariant, DetailsBuilder>, 
        HasTooltipBuilder<Details, DetailsBuilder> {
    
    private DetailsBuilder() {}

    @Override
    protected Details instantiate() {
        return new Details();
    }

    /**
     * Builder method, sets the summary.
     * 
     * @see Details#setSummary(Component) 
     * @param summary The summary to set; might be null.
     * @return this
     */
    public DetailsBuilder setSummary(Component summary) {
        return configure(details -> details.setSummary(summary));
    }

    /**
     * Builder method, sets the summary.
     *
     * @see Details#setSummaryText(String) 
     * @param summary The summary to set; might be null.
     * @return this
     */
    public DetailsBuilder setSummaryText(String summary) {
        return configure(details -> details.setSummaryText(summary));
    }

    /**
     * Builder methods, sets whether the details are opened.
     * 
     * @see Details#setOpened(boolean) 
     * @param opened True if the content should be shown, false otherwise.
     * @return this
     */
    public DetailsBuilder setOpened(boolean opened) {
        return configure(details -> details.setOpened(opened));
    }

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static DetailsBuilder create() {
        return new DetailsBuilder();
    }
}
