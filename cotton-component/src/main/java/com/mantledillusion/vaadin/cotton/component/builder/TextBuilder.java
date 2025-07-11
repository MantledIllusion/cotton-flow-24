package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.Text;

/**
 * {@link ConfigurationBuilder} for {@link Text}s.
 */
public class TextBuilder extends AbstractTextBuilder<Text, TextBuilder> {

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
