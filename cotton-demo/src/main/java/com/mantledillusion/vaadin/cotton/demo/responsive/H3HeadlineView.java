package com.mantledillusion.vaadin.cotton.demo.responsive;

import com.mantledillusion.vaadin.cotton.responsive.*;
import com.mantledillusion.vaadin.cotton.spring.annotation.AttachedComponent;
import com.vaadin.flow.component.html.H3;

@AttachedComponent("Headline")
@Respond(to = ScreenClass.class, with = ScreenClass.PANEL)
@Responsive(H4HeadlineView.class)
public class H3HeadlineView extends AbstractHeadlineView<H3> {

    @Override
    protected H3 initContent() {
        return new H3();
    }
}
