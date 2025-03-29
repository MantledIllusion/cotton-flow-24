package com.mantledillusion.vaadin.cotton.component;

/**
 * A customizer to a {@link ConfigurationBuilder}'s sub-builder.
 *
 * @param <B> The type of the sub-builder to customize.
 */
public interface ConfigurationCustomizer<B> {

    /**
     * Customizes the configuration of the given sub-builder.
     *
     * @param builder The builder whose configuration to customize, might <b>not</b> be null.
     */
    void customize(B builder);
}
