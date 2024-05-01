package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.ClickNotifier;
import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.Tag;

/**
 * {@link ConfigurationBuilder} for {@link HtmlLabel}.
 */
public class HtmlLabelBuilder extends AbstractClickableHtmlContainerBuilder<HtmlLabelBuilder.HtmlLabel, HtmlLabelBuilder> {

    /**
     * Simplistic {@link HtmlContainer} extension allowing to set HTML code as text.
     */
    @Tag(Tag.LABEL)
    public static class HtmlLabel extends HtmlContainer implements ClickNotifier<HtmlLabel> {

        private HtmlLabel() {}

        @Override
        public void setText(String text) {
            getElement().setProperty("innerHTML", text);
        }
    }

    private HtmlLabelBuilder() {
        super(HtmlLabel::new);
    }

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static HtmlLabelBuilder create() {
        return new HtmlLabelBuilder();
    }
}
