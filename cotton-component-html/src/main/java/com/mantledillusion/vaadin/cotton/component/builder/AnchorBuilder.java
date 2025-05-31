package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.AnchorTarget;
import com.vaadin.flow.component.html.AnchorTargetValue;
import com.vaadin.flow.component.html.H1;

/**
 * {@link ConfigurationBuilder} for {@link Anchor}s.
 */
public class AnchorBuilder extends AbstractHtmlContainerBuilder<Anchor, AnchorBuilder> {

    private AnchorBuilder() {
        super(Anchor::new);
    }

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static AnchorBuilder create() {
        return new AnchorBuilder();
    }

    /**
     * Builder method, configures the HREF of the anchor after building.
     *
     * @see Anchor#setHref(String)
     * @param href The href to set; might be null.
     * @return this
     */
    public AnchorBuilder setHref(String href) {
        return configure(anchor -> anchor.setHref(href));
    }

    /**
     * Builder method, configures the target to open the HREF in after building.
     *
     * @see Anchor#setTarget(AnchorTargetValue)
     * @param target The target to set; might <b>not</b> be null.
     * @return this
     */
    public AnchorBuilder setTarget(AnchorTarget target) {
        return configure(anchor -> anchor.setTarget(target));
    }
}
