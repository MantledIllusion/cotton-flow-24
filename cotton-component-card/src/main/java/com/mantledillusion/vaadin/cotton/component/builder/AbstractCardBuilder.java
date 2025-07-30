package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.card.Card;
import com.vaadin.flow.component.card.CardVariant;

public abstract class AbstractCardBuilder<C extends Card, B extends AbstractCardBuilder<C, B>> extends AbstractComponentBuilder<C, B> implements
        HasComponentsBuilder<C, B>,
        HasElementBuilder<C, B>,
        HasSizeBuilder<C, B>,
        HasStyleBuilder<C, B>,
        HasThemeVariantBuilder<C, CardVariant, B> {

    /**
     * Builder method, sets the media component.
     *
     * @see Card#setMedia(Component)
     * @param media The media to set; might be null.
     * @return this
     */
    public B setMedia(Component media) {
        return configure(card -> card.setMedia(media));
    }

    /**
     * Builder method, sets the title.
     *
     * @see Card#setTitle(String)
     * @param title The media to set; might be null.
     * @return this
     */
    public B setTitle(String title) {
        return configure(card -> card.setTitle(title));
    }

    /**
     * Builder method, sets the title and heading level.
     *
     * @see Card#setTitle(String, Integer)
     * @param title The title to set; might be null.
     * @param titleHeadingLevel The title heading level to set; might be null.
     * @return this
     */
    public B setTitle(String title, Integer titleHeadingLevel) {
        return configure(card -> card.setTitle(title, titleHeadingLevel));
    }

    /**
     * Builder method, sets the title heading level.
     *
     * @see Card#setTitleHeadingLevel(Integer)
     * @param titleHeadingLevel The title heading level to set; might be null.
     * @return this
     */
    public B setTitleHeadingLevel(Integer titleHeadingLevel) {
        return configure(card -> card.setTitleHeadingLevel(titleHeadingLevel));
    }

    /**
     * Builder method, sets the title component.
     *
     * @see Card#setTitle(Component)
     * @param title The title to set; might be null.
     * @return this
     */
    public B setTitle(Component title) {
        return configure(card -> card.setTitle(title));
    }

    /**
     * Builder method, sets the subtitle component.
     *
     * @see Card#setSubtitle(Component)
     * @param subtitle The subtitle to set; might be null.
     * @return this
     */
    public B setSubtitle(Component subtitle) {
        return configure(card -> card.setSubtitle(subtitle));
    }

    /**
     * Builder method, sets the header component.
     *
     * @see Card#setHeader(Component)
     * @param header The header to set; might be null.
     * @return this
     */
    public B setHeader(Component header) {
        return configure(card -> card.setHeader(header));
    }

    /**
     * Builder method, sets the header prefix component.
     *
     * @see Card#setHeaderPrefix(Component)
     * @param headerPrefix The header prefix to set; might be null.
     * @return this
     */
    public B setHeaderPrefix(Component headerPrefix) {
        return configure(card -> card.setHeaderPrefix(headerPrefix));
    }

    /**
     * Builder method, sets the header suffix component.
     *
     * @see Card#setHeaderSuffix(Component)
     * @param headerSuffix The header suffix to set; might be null.
     * @return this
     */
    public B setHeaderSuffix(Component headerSuffix) {
        return configure(card -> card.setHeaderSuffix(headerSuffix));
    }

    /**
     * Builder method, adds a footer component.
     *
     * @see Card#addToFooter(Component...)
     * @param footerComponent The footer component to add; might <b>not</b> be null.
     * @return this
     */
    public B addToFooter(Component... footerComponent) {
        return configure(card -> card.addToFooter(footerComponent));
    }
}
