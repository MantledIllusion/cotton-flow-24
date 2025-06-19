package com.mantledillusion.vaadin.cotton.model;

import com.vaadin.flow.component.HasValue;

import java.util.Optional;

/**
 * Interface for {@link Converter}s that might be used to convert between the value types of a field and a property.
 * <p>
 * For example, needed when a {@link HasValue} is bound to a {@link com.mantledillusion.data.epiphy.Property} using
 * a {@link ModelContainer}.
 *
 * @param <FieldValueType> The value type of the field.
 * @param <PropertyValueType> The value type of the property.
 */
@FunctionalInterface
public interface Converter<FieldValueType, PropertyValueType> {

	/**
	 * Converts a value of the properties' value type to a value of the field's
	 * value type.
	 * 
	 * @param value The properties' value to convert; might be null.
	 * @return The converted value for the field, might be null
	 */
	FieldValueType toField(PropertyValueType value);

	/**
	 * Converts a value of the field's value type to a value of the properties'
	 * value type.
	 * 
	 * @param value The field's value to convert; might be null.
	 * @return The converted value for the property, might be null
	 * @throws IllegalArgumentException When called without being overridden
	 */
	default PropertyValueType toProperty(FieldValueType value) {
		throw new IllegalArgumentException("Converting to the property value type is not implemented");
	}

	/**
	 * Wraps the given converter in a way where it is only used for conversion if the field/property value to convert
	 * is not null. If it is, null is returned.
	 *
	 * @param <FieldValueType> The value type of the field.
	 * @param <PropertyValueType> The value type of the property.
	 * @param converter The converter to wrap; might <b>not</b> be null.
	 * @return A wrapped {@link Converter}, never null
	 */
	static <FieldValueType, PropertyValueType> Converter<FieldValueType, PropertyValueType> nullSafe(Converter<FieldValueType, PropertyValueType> converter) {
		if (converter == null) {
			throw new IllegalArgumentException("Cannot wrap a null converter");
		}

		return new Converter<>() {

			@Override
			public FieldValueType toField(PropertyValueType value) {
				return Optional.ofNullable(value).map(converter::toField).orElse(null);
			}

			@Override
			public PropertyValueType toProperty(FieldValueType value) {
				return Optional.ofNullable(value).map(converter::toProperty).orElse(null);
			}
		};
	}
}
