package com.mantledillusion.vaadin.cotton.demo.responsive;

import com.mantledillusion.vaadin.cotton.spring.annotation.AttachedComponent;
import com.vaadin.flow.component.html.H4;

@AttachedComponent
public class H4HeadlineView extends AbstractHeadlineView<H4> {

    @Override
    protected H4 initContent() {
        return new H4();
    }
}