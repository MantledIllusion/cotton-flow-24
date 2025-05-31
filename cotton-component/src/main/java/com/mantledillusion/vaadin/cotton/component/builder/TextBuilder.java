package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.HasElementBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.HasTextBuilder;
import com.vaadin.flow.component.Text;

/**
 * {@link ConfigurationBuilder} for {@link Text}s.
 */
public class TextBuilder extends AbstractComponentBuilder<Text, TextBuilder> implements
        HasElementBuilder<Text, TextBuilder>,
        HasTextBuilder<Text, TextBuilder> {

    private TextBuilder() {}

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static TextBuilder create() {
        return new TextBuilder();
    }

    @Override
    protected Text instantiate() {
        return new Text(null);
    }
}
