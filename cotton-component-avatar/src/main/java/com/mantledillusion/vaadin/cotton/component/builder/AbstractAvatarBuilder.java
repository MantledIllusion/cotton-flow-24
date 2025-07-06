package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationCustomizer;
import com.mantledillusion.vaadin.cotton.component.Configurer;
import com.mantledillusion.vaadin.cotton.component.mixin.HasElementBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.HasSizeBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.HasStyleBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.HasThemeVariantBuilder;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.avatar.AvatarVariant;
import com.vaadin.flow.server.AbstractStreamResource;

public abstract class AbstractAvatarBuilder<C extends Avatar, B extends AbstractAvatarBuilder<C, B>> extends AbstractComponentBuilder<C, B> implements
        HasElementBuilder<C, B>,
        HasSizeBuilder<C, B>,
        HasStyleBuilder<C, B>,
        HasThemeVariantBuilder<C, AvatarVariant, B> {

    public class AvatarI18NBuilder extends AbstractConfigurationBuilder<Avatar.AvatarI18n, AvatarI18NBuilder> implements
            Configurer<C> {

        @Override
        public void configure(C component) {
            Avatar.AvatarI18n i18n = new Avatar.AvatarI18n();
            apply(i18n);
            component.setI18n(i18n);
        }

        /**
         * Builder method, sets the name of an anonymous user.
         *
         * @see com.vaadin.flow.component.avatar.Avatar.AvatarI18n#setAnonymous(String)
         * @param anonymous The name to set; might <b>not</b> be null.
         * @return this
         */
        public AvatarI18NBuilder setAnonymous(String anonymous) {
            return configure(i18n -> i18n.setAnonymous(anonymous));
        }
    }

    /**
     * Builder method, sets the {@link com.vaadin.flow.component.avatar.Avatar.AvatarI18n}.
     *
     * @see Avatar#setI18n(Avatar.AvatarI18n)
     * @param i18n The internationalization to set; might <b>not</b> be null.
     * @return this
     */
    public B setI18N(Avatar.AvatarI18n i18n) {
        return configure(avatar -> avatar.setI18n(i18n));
    }

    /**
     * Builder method, sets the {@link com.vaadin.flow.component.avatar.Avatar.AvatarI18n}.
     *
     * @see Avatar#setI18n(Avatar.AvatarI18n)
     * @param customizer A {@link ConfigurationCustomizer} for the {@link AvatarI18NBuilder}; might <b>not</b> be null.
     * @return this
     */
    public B setI18N(ConfigurationCustomizer<AvatarI18NBuilder> customizer) {
        AvatarI18NBuilder avatarI18NBuilder = new AvatarI18NBuilder();
        customizer.customize(avatarI18NBuilder);
        return configure(avatarI18NBuilder);
    }

    /**
     * Builder method, sets the name of the avatar to display in the tooltip on hover.
     *
     * @see Avatar#setName(String)
     * @param name The name to set; might be null.
     * @return this
     */
    public B setName(String name) {
        return configure(avatar -> avatar.setName(name));
    }

    /**
     * Builder method, sets the abbreviation to display on the avatar.
     *
     * @see Avatar#setAbbreviation(String)
     * @param name The abbreviation to set; might be null.
     * @return this
     */
    public B setAbbreviation(String name) {
        return configure(avatar -> avatar.setAbbreviation(name));
    }

    /**
     * Builder method, sets the image url of the avatar.
     *
     * @see Avatar#setImage(String)
     * @param url The URL to set; might be null.
     * @return this
     */
    public B setImage(String url) {
        return configure(avatar -> avatar.setImage(url));
    }

    /**
     * Builder method, sets the image resource of the avatar.
     *
     * @see Avatar#setImageResource(AbstractStreamResource)
     * @param resource The resource to set; might be null.
     * @return this
     */
    public B setImageResource(AbstractStreamResource resource) {
        return configure(avatar -> avatar.setImageResource(resource));
    }

    /**
     * Builder method, sets the index of the avatar's color.
     *
     * @see Avatar#setColorIndex(Integer)
     * @param index The index to set; might be null.
     * @return this
     */
    public B setColorIndex(Integer index) {
        return configure(avatar -> avatar.setColorIndex(index));
    }

    /**
     * Builder method, sets whether to show the avatar's tooltip.
     *
     * @see Avatar#setTooltipEnabled(boolean)
     * @param enabled True if the tooltip is enabled, false otherwise.
     * @return this
     */
    public B setTooltipEnabled(boolean enabled) {
        return configure(avatar -> avatar.setTooltipEnabled(enabled));
    }
}
