package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.login.LoginOverlay;

/**
 * {@link ConfigurationBuilder} for {@link LoginOverlay}s.
 */
public class LoginOverlayBuilder extends AbstractLoginBuilder<LoginOverlay, LoginOverlayBuilder> {

    @Override
    protected LoginOverlay instantiate() {
        return new LoginOverlay();
    }

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static LoginOverlayBuilder create() {
       return new LoginOverlayBuilder();
    }
}
