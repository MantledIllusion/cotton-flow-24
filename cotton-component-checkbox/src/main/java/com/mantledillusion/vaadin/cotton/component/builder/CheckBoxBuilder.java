package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.checkbox.Checkbox;

/**
 * {@link ConfigurationBuilder} for {@link Checkbox}es.
 */
public class CheckBoxBuilder extends AbstractComponentBuilder<Checkbox, CheckBoxBuilder> implements
		ClickNotifierBuilder<Checkbox, CheckBoxBuilder>,
		FocusableBuilder<Checkbox, CheckBoxBuilder>,
		HasAriaLabelBuilder<Checkbox, CheckBoxBuilder>,
		HasElementBuilder<Checkbox, CheckBoxBuilder>,
		HasEnabledBuilder<Checkbox, CheckBoxBuilder>,
		HasLabelBuilder<Checkbox, CheckBoxBuilder>,
		HasSizeBuilder<Checkbox, CheckBoxBuilder>,
		HasStyleBuilder<Checkbox, CheckBoxBuilder>,
		HasTooltipBuilder<Checkbox, CheckBoxBuilder>,
		HasValueBuilder<Checkbox, Boolean, AbstractField.ComponentValueChangeEvent<Checkbox, Boolean>, CheckBoxBuilder> {

	private CheckBoxBuilder() {}

	/**
	 * Factory method for a new instance.
	 *
	 * @return A new instance, never null.
	 */
	public static CheckBoxBuilder create() {
		return new CheckBoxBuilder();
	}

	@Override
	protected Checkbox instantiate() {
		return new Checkbox();
	}

	/**
	 * Builder method, configures whether the {@link Checkbox} should be input
	 * focussed after the page finishes loading.
	 * 
	 * @see Checkbox#setAutofocus(boolean)
	 * @param autofocus
	 *            True if the {@link Checkbox} should be focussed, false otherwise.
	 * @return this
	 */
	public CheckBoxBuilder setAutofocus(boolean autofocus) {
		return configure(checkBox -> checkBox.setAutofocus(autofocus));
	}

	/**
	 * Builder method, configures the indeterminate state.
	 * 
	 * @see Checkbox#setIndeterminate(boolean)
	 * @param indeterminate
	 *            True if the {@link Checkbox} should be indeterminate, false
	 *            otherwise.
	 * @return this
	 */
	public CheckBoxBuilder setIndeterminate(boolean indeterminate) {
		return configure(checkBox -> checkBox.setIndeterminate(indeterminate));
	}
}
