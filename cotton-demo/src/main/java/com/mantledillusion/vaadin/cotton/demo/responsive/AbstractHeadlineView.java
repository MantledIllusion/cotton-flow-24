package com.mantledillusion.vaadin.cotton.demo.responsive;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasText;

public abstract class AbstractHeadlineView<C extends Component & HasText> extends Composite<C> {

    public void setText(String text) {
        getContent().setText(text);
    }
}
