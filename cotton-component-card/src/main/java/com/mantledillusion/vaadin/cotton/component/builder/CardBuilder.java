package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.card.Card;

/**
 * {@link ConfigurationBuilder} for {@link Card}s.
 */
@SuppressWarnings("unused")
public class CardBuilder extends AbstractCardBuilder<Card, CardBuilder> {

    private CardBuilder() {}

    @Override
    protected Card instantiate() {
        return new Card();
    }

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static CardBuilder create() {
        return new CardBuilder();
    }
}
