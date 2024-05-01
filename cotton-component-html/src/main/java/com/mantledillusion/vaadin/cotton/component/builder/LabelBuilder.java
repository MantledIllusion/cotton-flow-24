package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.NativeLabel;

/**
 * {@link ConfigurationBuilder} for {@link NativeLabel}s.
 */
public class LabelBuilder extends AbstractHtmlContainerBuilder<NativeLabel, LabelBuilder> {

	private LabelBuilder() {
		super(NativeLabel::new);
	}

	/**
	 * Factory method for a new instance.
	 *
	 * @return A new instance, never null.
	 */
	public static LabelBuilder create() {
		return new LabelBuilder();
	}

	/**
	 * Builder method, configures the component this {@link NativeLabel} is for.
	 * 
	 * @see NativeLabel#setFor(String)
	 * @param id
	 *            The id of the {@link Component} who this {@link NativeLabel} is for;
	 *            might be null.
	 * @return this
	 */
	public LabelBuilder setFor(String id) {
		return configure(label -> label.setFor(id));
	}

	/**
	 * Builder method, configures the component this {@link NativeLabel} is for.
	 * 
	 * @see NativeLabel#setFor(Component)
	 * @param c
	 *            The {@link Component} who this {@link NativeLabel} is for; might
	 *            <b>not</b> be null.
	 * @return this
	 */
	public LabelBuilder setFor(Component c) {
		return configure(label -> label.setFor(c));
	}

	/**
	 * Builder method, configures whether the {@link NativeLabel}'s text can be wrapped on white spaces or not.
	 *
	 * @see com.vaadin.flow.dom.Style#set(String, String)
	 * @param wrap
	 *            True if the text can be wrapped, false otherwise.
	 * @return this
	 */
	public LabelBuilder setWrap(boolean wrap) {
		return configure(label -> label.getElement().getStyle().set("white-space", wrap ? "pre-wrap" : "nowrap"));
	}
}