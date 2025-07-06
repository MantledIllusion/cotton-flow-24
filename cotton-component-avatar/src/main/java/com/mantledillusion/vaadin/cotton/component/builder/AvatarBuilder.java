package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.avatar.Avatar;

/**
 * {@link ConfigurationBuilder} for {@link Avatar}s.
 */
@SuppressWarnings("unused")
public class AvatarBuilder extends AbstractAvatarBuilder<Avatar, AvatarBuilder> {

    private AvatarBuilder() {}

    @Override
    protected Avatar instantiate() {
        return new Avatar();
    }

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static AvatarBuilder create() {
        return new AvatarBuilder();
    }
}
