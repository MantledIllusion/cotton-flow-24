package com.mantledillusion.vaadin.cotton.component.mixin;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasSize;

/**
 * {@link ConfigurationBuilder} for {@link HasSize} implementing {@link Component}s.
 *
 * @param <C>
 *            The {@link Component} type implementing {@link HasSize}.
 * @param <B>
 *            The final implementation type of {@link HasSizeBuilder}.
 */
public interface HasSizeBuilder<C extends HasSize, B extends HasSizeBuilder<C, B>> extends
		HasElementBuilder<C, B> {

	/**
	 * Builder method, configures the {@link Component}'s width to be set undefined.
	 * 
	 * @see HasSize#setWidth(String)
	 * @return this
	 */
	default B setWidthUndefined() {
		return setWidth(null);
	}

	/**
	 * Builder method, configures the {@link Component}'s height to be set undefined.
	 * 
	 * @see HasSize#setHeight(String)
	 * @return this
	 */
	default B setHeightUndefined() {
		return setHeight(null);
	}

	/**
	 * Builder method, configures the {@link Component} to have an undefined width and height.
	 *
	 * @see HasSize#setSizeUndefined()
	 * @return this
	 */
	default B setSizeUndefined() {
		return configure(HasSize::setSizeUndefined);
	}

	/**
	 * Builder method, configures the {@link Component}'s width to be set.
	 * 
	 * @see HasSize#setWidth(String)
	 * @param width The width to set; might be null
	 * @return this
	 */
	default B setWidth(String width) {
		return configure(hasSize -> hasSize.setWidth(width));
	}

	/**
	 * Builder method, configures the {@link Component}'s height to be set.
	 * 
	 * @see HasSize#setHeight(String)
	 * @param height The width to set; might be null
	 * @return this
	 */
	default B setHeight(String height) {
		return configure(hasSize -> hasSize.setHeight(height));
	}

	/**
	 * Builder method, configures the {@link Component}'s width to be set to 100% percental share of its parent's width.
	 *
	 * @see HasSize#setWidth(String)
	 * @return this
	 */
	default B setWidthFull() {
		return configure(HasSize::setWidthFull);
	}

	/**
	 * Builder method, configures the {@link Component}'s height to be set to 100% percental share of its parent's height.
	 *
	 * @see HasSize#setHeight(String)
	 * @return this
	 */
	default B setHeightFull() {
		return configure(HasSize::setHeightFull);
	}

	/**
	 * Builder method, configures the {@link Component} to have a 100% percental width and height.
	 * 
	 * @see HasSize#setSizeFull()
	 * @return this
	 */
	default B setSizeFull() {
		return configure(HasSize::setSizeFull);
	}
}