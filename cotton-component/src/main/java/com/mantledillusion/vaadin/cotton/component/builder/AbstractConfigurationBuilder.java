package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.Configurer;

import java.util.*;

/**
 * Base implementation of {@link ConfigurationBuilder} that provides all base functionality.
 *
 * @param <C> The type this builder builds configurations on.
 * @param <B> The final implementation type of this {@link AbstractConfigurationBuilder}. Necessary to allow builder
 *           methods of non-final implementations to return the builder instance in the correct type.
 */
abstract class AbstractConfigurationBuilder<C, B extends AbstractConfigurationBuilder<C, B>> implements ConfigurationBuilder<C, B> {

	private final AbstractConfigurationBuilder<?, ?> parent;
	private final Map<Class<?>, Object> context = new HashMap<>();
	private final List<Configurer<C>> configurators = new ArrayList<>();

	AbstractConfigurationBuilder() {
		this.parent = null;
	}

	AbstractConfigurationBuilder(AbstractConfigurationBuilder<?, ?> parent) {
		this.parent = parent;
	}

	@Override
	public <V> boolean contains(Class<V> valueType) {
		return (this.parent != null && this.parent.contains(valueType)) || this.context.containsKey(valueType);
	}

	@Override
	public <V> V get(Class<V> valueType) {
		return this.parent != null && this.parent.contains(valueType)
				? this.parent.get(valueType)
				: (V) this.context.get(valueType);
	}

	@Override
	public <V, V2 extends V> void set(Class<V> valueType, V2 value) {
		if (value == null) {
			return;
		}
		if (this.context.containsKey(valueType)) {
			throw new IllegalStateException("Configuration tried to configure a second instance of the type " +
					value.getClass().getSimpleName() + " for the type " + valueType.getSimpleName() +
					", which would cause the configuration context to become inconsistent.");
		}
		this.context.put(valueType, value);
	}

	@Override
	public B configure(Configurer<C> configurer, boolean prepend) {
		this.configurators.add(prepend ? 0 : this.configurators.size(), configurer);
		return getThis();
	}
	
	@Override
	public List<Configurer<C>> getConfigurers() {
		return Collections.unmodifiableList(this.configurators);
	}

	@Override
	@SuppressWarnings("unchecked")
	public B getThis() {
		return (B) this;
	}

	protected void apply(C entity) {
		this.context.clear();
		this.context.put(entity.getClass(), entity);
		this.configurators.forEach(configuration -> configuration.configure(entity));
	}
}