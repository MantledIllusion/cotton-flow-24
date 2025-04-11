package com.mantledillusion.vaadin.cotton.model;

import com.mantledillusion.data.epiphy.Property;
import com.mantledillusion.data.epiphy.context.Context;
import com.mantledillusion.data.epiphy.context.TraversingMode;
import com.mantledillusion.data.epiphy.context.function.*;
import com.mantledillusion.data.epiphy.context.reference.ReferencedValue;
import com.mantledillusion.essentials.expression.Expression;
import com.mantledillusion.vaadin.cotton.component.Configurer;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasEnabled;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.data.binder.HasDataProvider;
import com.vaadin.flow.data.provider.HasListDataView;
import com.vaadin.flow.data.provider.InMemoryDataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.provider.hierarchy.HasHierarchicalDataProvider;
import com.vaadin.flow.data.provider.hierarchy.TreeData;
import com.vaadin.flow.data.provider.hierarchy.TreeDataProvider;
import com.vaadin.flow.function.SerializablePredicate;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.VaadinSessionState;
import com.vaadin.flow.shared.Registration;
import org.apache.commons.lang3.ObjectUtils;

import java.lang.reflect.Method;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A container of a data model instance.
 *
 * @param <ModelType> The type of the data model the {@link ModelContainer} is able to grant access to.
 */
public class ModelContainer<ModelType> implements AuditingConfigurer<ModelContainer<ModelType>> {

	private static final Procedure NOOP = () -> {};

	private interface Procedure {

		void trigger();
	}

	private final Context context;
	private final Auditor baseBindingAuditor = new Auditor(null);
	private final Map<Property<ModelType, ?>, List<Binding<?>>> bindings = new IdentityHashMap<>();
	private final List<ModelContainer<ModelType>> children = new ArrayList<>();

	/**
	 * Default constructor.
	 */
	public ModelContainer() {
		this(null);
	}

	/**
	 * Advanced constructor allowing to supply a {@link Context} when accessing the model.
	 *
	 * @param context The {@link Context} to use, might be null.
	 */
	public ModelContainer(Context context) {
        this.context = ObjectUtils.defaultIfNull(context, Context.EMPTY);
    }

	// ######################################################################################################################################
	// ############################################################ MODEL ACCESS ############################################################
	// ######################################################################################################################################

	private ModelContainer<ModelType> parent;
	private ModelType model;

	/**
	 * Returns the {@link Context} through which the model is accessed.
	 *
	 * @return The context, never null
	 */
	public final Context getContext() {
		return this.context;
	}

	/**
	 * Returns the model instance currently contained by this {@link ModelContainer}.
	 *
	 * @return This {@link ModelContainer}'s own or parent model instance, might be null
	 */
	public ModelType getModel() {
		return Optional.ofNullable(this.parent)
				.map(ModelContainer::getModel)
				.orElse(this.model);
	}

	/**
	 * Sets a model instance for this {@link ModelContainer}.
	 * <p>
	 * Unbinds the {@link ModelContainer} from its parent if there is any.
	 *
	 * @param model The instance to set; might be null.
	 */
	public void setModel(ModelType model) {
		if (this.parent != null) {
			this.parent.unregister(this);
		}
		this.parent = null;
		this.model = model;
		updateAll(Binding.UpdateType.EXCHANGE);
	}

	/**
	 * Sets a model parent for this {@link ModelContainer}.
	 * <p>
	 * Unbinds the {@link ModelContainer} from its model if there is any.
	 *
	 * @param parent The parent to set; might be null.
	 */
	public void setModel(ModelContainer<ModelType> parent) {
		if (this.parent != null) {
			this.parent.unregister(this);
		}
		this.parent = parent;
		this.parent.register(this);
		this.model = null;
		updateAll(Binding.UpdateType.EXCHANGE);
	}

	/**
	 * Resets the model for this {@link ModelContainer}.
	 * <p>
	 * Unbinds the {@link ModelContainer} from its parent or model if there is any.
	 *
	 * @return This {@link ModelContainer}'s own or parent model instance, might be null
	 */
	public ModelType resetModel() {
		ModelType model;
		if (this.parent != null) {
			model = this.parent.getModel();
			this.parent.unregister(this);
		} else {
			model = this.model;
		}
		this.parent = null;
		this.model = null;
		updateAll(Binding.UpdateType.EXCHANGE);
		return model;
	}

	// ######################################################################################################################################
	// ############################################################# AUDITING ###############################################################
	// ######################################################################################################################################

	/**
	 * Sets the {@link Binding.AuditMode} for all {@link Binding}s.
	 * <p>
	 * The mode determines the basis right auditings added using {@link #setAudit(Binding.AccessMode, Expression)} are
	 * executed on:<br>
	 * - {@link Binding.AuditMode#GENEROUS}: {@link Binding.AccessMode#READ_WRITE} is the default, added
	 * {@link Expression}s limit the access.<br>
	 * - {@link Binding.AuditMode#RESTRICTIVE}: {@link Binding.AccessMode#HIDDEN} is the default, added
	 * {@link Expression}s expand the access.<br>
	 * <p>
	 * The default mode is {@link Binding.AuditMode#GENEROUS}.
	 *
	 * @param mode The mode to set; might <b>not</b> be null.
	 * @return this.
	 */
	public final ModelContainer<ModelType> setAuditMode(Binding.AuditMode mode) {
		this.baseBindingAuditor.setAuditMode(mode);
		refreshBindingAuditing();
		return this;
	}

	@Override
	public final ModelContainer<ModelType> setAudit(Binding.AccessMode mode, boolean requiresLogin, Expression<String> rightExpression) {
		this.baseBindingAuditor.set(mode, requiresLogin, rightExpression);
		refreshBindingAuditing();
		return this;
	}

	private void refreshBindingAuditing() {
		this.bindings.values().stream().flatMap(List::stream).forEach(Binding::refreshAccessMode);
	}

	// ######################################################################################################################################
	// ########################################################## CHILD HANDLING ############################################################
	// ######################################################################################################################################

	private void register(ModelContainer<ModelType> childAccessor) {
		this.children.add(childAccessor);
	}

	private void unregister(ModelContainer<ModelType> childAccessor) {
		this.children.remove(childAccessor);
	}

	// ######################################################################################################################################
	// ########################################################## BINDING HANDLING ##########################################################
	// ######################################################################################################################################

	private <FieldValueType, BindingType extends Binding<FieldValueType>> BindingType addBinding(Property<ModelType, ?> property, BindingType binding) {
		this.bindings.computeIfAbsent(property, key -> new ArrayList<>()).add(binding);
		return binding;
	}

	private <FieldValueType> Consumer<Binding<FieldValueType>> removeBinding(Property<ModelType, ?> property) {
		return new Consumer<>() {

			private boolean bound = true;

			@Override
			public void accept(Binding<FieldValueType> binding) {
				if (this.bound) {
					if (Optional.ofNullable(VaadinSession.getCurrent()).map(VaadinSession::getState).map(VaadinSessionState.OPEN::equals).orElse(false)) {
						binding.accessModeChanged(false);
					}
					ModelContainer.this.bindings.computeIfAbsent(property, key -> new ArrayList<>()).remove(binding);
					this.bound = false;
				}
			}
		};
	}

	/**
	 * Unbinds all {@link Binding}s from this {@link ModelContainer}.
	 */
	public synchronized void unbindAll() {
		Iterator<Map.Entry<Property<ModelType, ?>, List<Binding<?>>>> entryIterator = this.bindings.entrySet().iterator();
		while (entryIterator.hasNext()) {
			Iterator<Binding<?>> bindingIterator = entryIterator.next().getValue().iterator();
			while (bindingIterator.hasNext()) {
				bindingIterator.next().unbind();
				bindingIterator.remove();
			}
			entryIterator.remove();
		}
	}

	private synchronized void updateAll(Binding.UpdateType type) {
		this.bindings.forEach((property, bindings) -> bindings.forEach(binding -> binding.valueChanged(this.context, type, Binding.UpdateDirection.PARENT)));
		this.children.forEach(child -> child.updateAll(type));
	}

	private synchronized void update(Property<ModelType, ?> property, Context context, Binding.UpdateType type) {
		// RUN OVER ALL BINDINGS
		boundPropertyLoop: for (Property<ModelType, ?> boundProperty : this.bindings.keySet()) {
			// IF THE UPDATE'S PROPERTY IS A PARENT OF THE BINDING'S PROPERTY...
			Set<Property<?, ?>> boundHierarchy = boundProperty.getHierarchy();
			Set<Property<?, ?>> hierarchy = property.getHierarchy();
			boolean upStream = boundHierarchy.containsAll(hierarchy);
			boolean downStream = hierarchy.containsAll(boundHierarchy);
			if (upStream || downStream) {
				// GO OVER ALL OF THE UPDATE'S CONTEXTABLE PROPERTIES
				for (Property<?, ?> contextedProperty : property.getHierarchy()) {
					// CHECK IF THERE IS A KEY FOR THE CONTEXTED PROPERTY THAT IS UNEQUAL TO THE ONE IN THIS BINDER'S CONTEXT
					if (context.containsReference(contextedProperty) && this.context.containsReference(contextedProperty)
							&& !context.getReference(contextedProperty).equals(this.context.getReference(contextedProperty))) {
						// IF THERE IS ONE, THE BINDING IS NOT AFFECTED BY THE UPDATE
						continue boundPropertyLoop;
					}
				}
				this.bindings.get(boundProperty).forEach(binding -> binding.valueChanged(context, type, upStream && downStream ?
						Binding.UpdateDirection.SELF : (upStream ? Binding.UpdateDirection.PARENT : Binding.UpdateDirection.CHILD)));
			}
		}
		this.children.forEach(child -> child.update(property, context, type));
	}

	// ######################################################################################################################################
	// ########################################################## CONSUMER BINDING ##########################################################
	// ######################################################################################################################################

	private static class ConsumerBinding<V> extends Binding<V> {
		private final Procedure valueReader;
		private final Consumer<V> valueResetter;

		private ConsumerBinding(Auditor baseAuditor, Consumer<Binding<V>> registration, Procedure valueReader, Consumer<V> valueResetter) {
			super(baseAuditor, registration);
			this.valueReader = valueReader;
			this.valueResetter = valueResetter;

			refreshAccessMode();
		}

		@Override
		public void accessModeChanged(boolean couple) {
			if (couple) {
				this.valueReader.trigger();
			} else {
				this.valueResetter.accept(getMaskedValue());
			}
		}

		@Override
		public void valueChanged(Context context, UpdateType type, UpdateDirection direction) {
			this.valueReader.trigger();
		}
	}

	/**
	 * Binds the given {@link BiConsumer} to the given property of this {@link ModelContainer}.
	 *
	 * @param <FieldValueType> The value type of the {@link Consumer} to bind.
	 * @param <ComponentType> The component type of the {@link Configurer}.
	 * @param setter The {@link BiConsumer} to bind; might <b>not</b> be null.
	 * @param property The {@link Property} to bind to; might <b>not</b> be null.
	 * @return A {@link Configurer} to use in any component builder, never null
	 */
	public <FieldValueType, ComponentType> Configurer<ComponentType> bindConfigurer(BiConsumer<ComponentType, FieldValueType> setter,
																					Property<ModelType, FieldValueType> property) {
		return bindConfigurer(setter, value -> value, property);
	}

	/**
	 * Binds the given {@link BiConsumer} to the given property of this {@link ModelContainer}.
	 *
	 * @param <FieldValueType> The value type of the {@link Consumer} to bind.
	 * @param <PropertyValueType> The value type of the property to bind with.
	 * @param <ComponentType> The component type of the {@link Configurer}.
	 * @param setter The {@link BiConsumer} to bind; might <b>not</b> be null.
	 * @param converter The {@link Converter} to use for conversion between the field's and the properties' value
	 *                  types; might <b>not</b> be null.
	 * @param property The {@link Property} to bind to; might <b>not</b> be null.
	 * @return A {@link Configurer} to use in any component builder, never null
	 */
	public <FieldValueType, PropertyValueType, ComponentType> Configurer<ComponentType> bindConfigurer(BiConsumer<ComponentType, FieldValueType> setter,
																									   Converter<FieldValueType, PropertyValueType> converter,
																									   Property<ModelType, PropertyValueType> property) {
		if (setter == null) {
			throw new IllegalArgumentException("Cannot bind a null " + BiConsumer.class.getSimpleName());
		} else if (converter == null) {
			throw new IllegalArgumentException("Cannot bind using a null converter");
		} else if (property == null) {
			throw new IllegalArgumentException("Cannot bind using a null property");
		}

		return component -> bindConsumer(value -> setter.accept(component, converter.toField(value)), property);
	}

	/**
	 * Binds the given {@link Consumer} to the given property of this {@link ModelContainer}.
	 *
	 * @param <FieldValueType> The value type of the {@link Consumer} to bind.
	 * @param consumer The {@link Consumer} to bind; might <b>not</b> be null.
	 * @param property The {@link Property} to bind to; might <b>not</b> be null.
	 * @return The {@link Binding} to further configure the binding with, never null
	 */
	public <FieldValueType> Binding<FieldValueType> bindConsumer(Consumer<FieldValueType> consumer,
																 Property<ModelType, FieldValueType> property) {
		return bindConsumer(consumer, value -> value, property);
	}

	/**
	 * Binds the given {@link Consumer} to the given property of this {@link ModelContainer}.
	 *
	 * @param <FieldValueType> The value type of the {@link Consumer} to bind.
	 * @param <PropertyValueType> The value type of the property to bind with.
	 * @param consumer The {@link Consumer} to bind; might <b>not</b> be null.
	 * @param converter The {@link Converter} to use for conversion between the field's and the properties' value
	 *                  types; might <b>not</b> be null.
	 * @param property The {@link Property} to bind to; might <b>not</b> be null.
	 * @return The {@link Binding} to further configure the binding with, never null
	 */
	public <FieldValueType, PropertyValueType> Binding<FieldValueType> bindConsumer(Consumer<FieldValueType> consumer,
																					Converter<FieldValueType, PropertyValueType> converter,
																					Property<ModelType, PropertyValueType> property) {
		if (consumer == null) {
			throw new IllegalArgumentException("Cannot bind a null " + Consumer.class.getSimpleName());
		} else if (converter == null) {
			throw new IllegalArgumentException("Cannot bind using a null converter");
		} else if (property == null) {
			throw new IllegalArgumentException("Cannot bind using a null property");
		}

		Consumer<Binding<FieldValueType>> registration = removeBinding(property);
		Procedure valueReader = () -> consumer.accept(converter.toField(this.get(property)));
		Consumer<FieldValueType> valueResetter = consumer::accept;

		return addBinding(property, new ConsumerBinding<>(this.baseBindingAuditor, registration, valueReader, valueResetter));
	}

	// ######################################################################################################################################
	// ########################################################## HASVALUE BINDING ##########################################################
	// ######################################################################################################################################

	private class HasValueBinding<FieldValueType> extends Binding<FieldValueType> implements HasValue.ValueChangeListener<HasValue.ValueChangeEvent<?>> {

		private final HasValue<?, ?> hasValue;
		private final Property<ModelType, ?> property;
		private final Procedure valueReader;
		private final Procedure valueWriter;
		private final Consumer<FieldValueType> valueResetter;

		private Registration registration;
		private boolean synchronizing = false;

		public HasValueBinding(Auditor baseAuditor, Consumer<Binding<FieldValueType>>  registration,
							   HasValue<?, ?> hasValue, Property<ModelType, ?> property,
							   Procedure valueReader, Procedure valueWriter, Consumer<FieldValueType> valueResetter) {
			super(baseAuditor, registration);
			this.hasValue = hasValue;
			this.property = property;
			this.valueReader = valueReader;
			this.valueWriter = valueWriter;
			this.valueResetter = valueResetter;

			refreshAccessMode();
		}

		@Override
		public synchronized void accessModeChanged(boolean couple) {
			refreshEnablement();
			if (this.hasValue instanceof Component) {
				((Component) this.hasValue).setVisible(getAccessMode() != AccessMode.HIDDEN);
			}

			if (couple) {
				if (this.registration == null) {
					this.registration = this.hasValue.addValueChangeListener(this);
				}
				this.synchronizing = true;
				this.valueReader.trigger();
				this.synchronizing = false;
			} else {
				if (this.registration != null) {
					this.registration.remove();
					this.registration = null;
				}
				this.valueResetter.accept(getMaskedValue());
			}
		}

		@Override
		public synchronized void valueChanged(HasValue.ValueChangeEvent<?> event) {
			if (!this.synchronizing) {
				this.synchronizing = true;
				this.valueWriter.trigger();
				this.synchronizing = false;
			}
		}

		@Override
		public synchronized void valueChanged(Context context, UpdateType type, UpdateDirection direction) {
			if (!this.synchronizing) {
				this.synchronizing = true;
				refreshEnablement();
				if (this.registration != null) {
					this.valueReader.trigger();
				}
				this.synchronizing = false;
			}
		}

		private void refreshEnablement() {
			boolean exists = ModelContainer.this.exists(this.property);
			this.hasValue.setReadOnly(!this.property.isWritable() || !exists ||
					getAccessMode() != AccessMode.READ_WRITE);
			if (this.hasValue instanceof HasEnabled) {
				((HasEnabled) this.hasValue).setEnabled(exists);
			}
		}
	}

	/**
	 * Binds the given {@link HasValue} to the given property of this {@link ModelContainer}.
	 *
	 * @param <FieldValueType> The value type of the {@link HasValue} to bind.
	 * @param hasValue The {@link HasValue} to bind; might <b>not</b> be null.
	 * @param property The {@link Property} to bind to; might <b>not</b> be null.
	 * @return The {@link Binding} to further configure the binding with, never null
	 */
	public <FieldValueType> Binding<FieldValueType> bindHasValue(HasValue<?, FieldValueType> hasValue,
																 Property<ModelType, FieldValueType> property) {
		if (hasValue == null) {
			throw new IllegalArgumentException("Cannot bind a null " + HasValue.class.getSimpleName());
		} else if (property == null) {
			throw new IllegalArgumentException("Cannot bind using a null property");
		}

		Consumer<Binding<FieldValueType>> registration = removeBinding(property);
		Procedure valueReader = () -> hasValue
				.setValue(ObjectUtils.defaultIfNull(this.get(property), hasValue.getEmptyValue()));
		Procedure valueWriter;
		if (property.isWritable()) {
			valueWriter = () -> this.set(property, hasValue.getValue());
		} else {
			valueWriter = NOOP;
		}
		Consumer<FieldValueType> valueResetter = maskedValue ->
				hasValue.setValue(maskedValue == null ? hasValue.getEmptyValue() : maskedValue);

		return addBinding(property, new HasValueBinding<>(this.baseBindingAuditor, registration, hasValue, property,
				valueReader, valueWriter, valueResetter));
	}

	/**
	 * Binds the given {@link HasValue} to the given property of this {@link ModelContainer}.
	 *
	 * @param <FieldValueType> The value type of the {@link HasValue} to bind.
	 * @param <PropertyValueType> The value type of the property to bind with.
	 * @param hasValue The {@link HasValue} to bind; might <b>not</b> be null.
	 * @param converter The {@link Converter} to use for conversion between the field's and the properties' value
	 *                  types; might <b>not</b> be null.
	 * @param property The {@link Property} to bind to; might <b>not</b> be null.
	 * @return The {@link Binding} to further configure the binding with, never null
	 */
	public <FieldValueType, PropertyValueType> Binding<FieldValueType> bindHasValue(HasValue<?, FieldValueType> hasValue,
																					Converter<FieldValueType, PropertyValueType> converter,
																					Property<ModelType, PropertyValueType> property) {
		if (hasValue == null) {
			throw new IllegalArgumentException("Cannot bind a null " + HasValue.class.getSimpleName());
		} else if (converter == null) {
			throw new IllegalArgumentException("Cannot bind using a null converter");
		} else if (property == null) {
			throw new IllegalArgumentException("Cannot bind using a null property");
		}

		Consumer<Binding<FieldValueType>> registration = removeBinding(property);
		Procedure valueReader = () -> hasValue.setValue(
				ObjectUtils.defaultIfNull(converter.toField(this.get(property)), hasValue.getEmptyValue()));
		Procedure valueWriter;
		if (property.isWritable()) {
			valueWriter = () -> this.set(property, converter.toProperty(hasValue.getValue()));
		} else {
			valueWriter = NOOP;
		}
		Consumer<FieldValueType> valueResetter = maskedValue ->
				hasValue.setValue(maskedValue == null ? hasValue.getEmptyValue() : maskedValue);

		return addBinding(property, new HasValueBinding<>(this.baseBindingAuditor, registration, hasValue, property,
				valueReader, valueWriter, valueResetter));
	}

	// ######################################################################################################################################
	// ###################################################### HASDATAPROVIDER BINDING #######################################################
	// ######################################################################################################################################

	private interface ElementHandle<ElementType> {

		boolean contains(ElementType element);

		Iterable<ElementType> get();

		void add(ElementType parent, ElementType element, ElementType sibling);

		void remove(ElementType element);
	}

	private class DataProviderBinding<ElementType> extends InMemoryDataProviderBinding<ElementType> {

		private final Property<ModelType, ElementType> property;
		private final ElementHandle<ElementType> elementHandle;

		private DataProviderBinding(Auditor baseAuditor, Consumer<Binding<ElementType>> registration,
									Property<ModelType, ElementType> property,
									InMemoryDataProvider<ElementType> dataProvider,
									ElementHandle<ElementType> elementHandle) {
			super(baseAuditor, registration, dataProvider);
			this.property = property;
			this.elementHandle = elementHandle;
		}

		@Override
		void valueChanged(Context context, UpdateType type, UpdateDirection direction) {
			boolean changed = false;

			if (type != UpdateType.ADD) {
				for (ElementType element: this.elementHandle.get()) {
					if (this.property.contextualize(ModelContainer.this.getModel(), element).isEmpty()) {
						if (direction.isUpStream) {
							this.elementHandle.remove(element);
						}
						changed = true;
					}
				}
			}

			if (type != UpdateType.REMOVE) {
				ElementType subSibling = null;
				for (Context subContext: this.property.contextualize(ModelContainer.this.getModel(), context, TraversingMode.PARENT)) {
					if (direction.isUpStream) {
						subSibling = addElement(null, subSibling, subContext);
					}
					changed = true;
				}
			}

			if (changed) {
				getDataProvider().refreshAll();
			}
		}

		private ElementType addElement(ElementType parent, ElementType childSibling, Context childContext) {
			ElementType child = this.property.get(ModelContainer.this.getModel(), childContext);
			if (child != parent) {
				if (!this.elementHandle.contains(child)) {
					this.elementHandle.add(parent, child, childSibling);
				}

				ElementType subSibling = null;
				for (Context subContext: this.property.contextualize(ModelContainer.this.getModel(), childContext, TraversingMode.CHILD)) {
					subSibling = addElement(child, subSibling, subContext);
				}
			}
			return child;
		}
	}

	/**
	 * Binds the given {@link HasListDataView} to the given property of this {@link ModelContainer}.
	 *
	 * @param <ElementType> The element type of the {@link HasListDataView} to bind.
	 * @param hasListDataView The {@link HasListDataView} to bind; might <b>not</b> be null.
	 * @param property The {@link Property} to bind to; might <b>not</b> be null.
	 * @return The {@link Binding} to further configure the binding with, never null
	 */
	public <ElementType> InMemoryDataProviderBinding<ElementType> bindHasListDataView(HasListDataView<ElementType, ?> hasListDataView,
																					  Property<ModelType, ElementType> property) {
		return bindHasListDataView(hasListDataView, property, null);
	}

	/**
	 * Binds the given {@link HasListDataView} to the given property of this {@link ModelContainer}.
	 *
	 * @param <ElementType> The element type of the {@link HasListDataView} to bind.
	 * @param hasListDataView The {@link HasListDataView} to bind; might <b>not</b> be null.
	 * @param property The {@link Property} to bind to; might <b>not</b> be null.
	 * @param filter The filter to apply to the elements; might be null.
	 * @return The {@link Binding} to further configure the binding with, never null
	 */
	public <ElementType> InMemoryDataProviderBinding<ElementType> bindHasListDataView(HasListDataView<ElementType, ?> hasListDataView,
																					  Property<ModelType, ElementType> property,
																					  SerializablePredicate<ElementType> filter) {
		Consumer<Binding<ElementType>> registration = removeBinding(property);
		List<ElementType> elements = new ArrayList<>();
		ListDataProvider<ElementType> dataProvider = new ListDataProvider<>(Collections.unmodifiableList(elements));
		dataProvider.setFilter(filter);
		hasListDataView.setItems(dataProvider);

		return addBinding(property, new DataProviderBinding<>(this.baseBindingAuditor, registration,
				property, dataProvider, new ElementHandle<ElementType>() {

			@Override
			public boolean contains(ElementType element) {
				return elements.contains(element);
			}

			@Override
			public Iterable<ElementType> get() {
				return new ArrayList<>(elements);
			}

			@Override
			public void add(ElementType parent, ElementType element, ElementType sibling) {
				elements.add(elements.indexOf(sibling)+1, element);
			}

			@Override
			public void remove(ElementType element) {
				elements.remove(element);
			}
		}));
	}

	/**
	 * Binds the given {@link HasDataProvider} to the given property of this {@link ModelContainer}.
	 *
	 * @param <ElementType> The element type of the {@link HasDataProvider} to bind.
	 * @param hasDataProvider The {@link HasDataProvider} to bind; might <b>not</b> be null.
	 * @param property The {@link Property} to bind to; might <b>not</b> be null.
	 * @return The {@link Binding} to further configure the binding with, never null
	 */
	public <ElementType> InMemoryDataProviderBinding<ElementType> bindHasDataProvider(HasDataProvider<ElementType> hasDataProvider,
																					  Property<ModelType, ElementType> property) {
		return bindHasDataProvider(hasDataProvider, property, null);
	}

	/**
	 * Binds the given {@link HasDataProvider} to the given property of this {@link ModelContainer}.
	 *
	 * @param <ElementType> The element type of the {@link HasDataProvider} to bind.
	 * @param hasDataProvider The {@link HasDataProvider} to bind; might <b>not</b> be null.
	 * @param property The {@link Property} to bind to; might <b>not</b> be null.
	 * @param filter The filter to apply to the elements; might be null.
	 * @return The {@link Binding} to further configure the binding with, never null
	 */
	public <ElementType> InMemoryDataProviderBinding<ElementType> bindHasDataProvider(HasDataProvider<ElementType> hasDataProvider,
																					  Property<ModelType, ElementType> property,
																					  SerializablePredicate<ElementType> filter) {
		Consumer<Binding<ElementType>> registration = removeBinding(property);
		List<ElementType> elements = new ArrayList<>();
		ListDataProvider<ElementType> dataProvider = new ListDataProvider<>(Collections.unmodifiableList(elements));
		dataProvider.setFilter(filter);
		hasDataProvider.setDataProvider(dataProvider);

		return addBinding(property, new DataProviderBinding<>(this.baseBindingAuditor, registration,
				property, dataProvider, new ElementHandle<ElementType>() {

			@Override
			public boolean contains(ElementType element) {
				return elements.contains(element);
			}

			@Override
			public Iterable<ElementType> get() {
				return new ArrayList<>(elements);
			}

			@Override
			public void add(ElementType parent, ElementType element, ElementType sibling) {
				elements.add(elements.indexOf(sibling)+1, element);
			}

			@Override
			public void remove(ElementType element) {
				elements.remove(element);
			}
		}));
	}

	/**
	 * Binds the given {@link HasHierarchicalDataProvider} to the given property of this {@link ModelContainer}.
	 *
	 * @param <ElementType> The element type of the {@link HasHierarchicalDataProvider} to bind.
	 * @param hasDataProvider The {@link HasHierarchicalDataProvider} to bind; might <b>not</b> be null.
	 * @param property The {@link Property} to bind to; might <b>not</b> be null.
	 * @return The {@link Binding} to further configure the binding with, never null
	 */
	public <ElementType> InMemoryDataProviderBinding<ElementType> bindHasHierarchicalDataProvider(HasHierarchicalDataProvider<ElementType> hasDataProvider,
																								  Property<ModelType, ElementType> property) {
		return bindHasHierarchicalDataProvider(hasDataProvider, property, null);
	}

	/**
	 * Binds the given {@link HasHierarchicalDataProvider} to the given property of this {@link ModelContainer}.
	 *
	 * @param <ElementType> The element type of the {@link HasHierarchicalDataProvider} to bind.
	 * @param hasDataProvider The {@link HasHierarchicalDataProvider} to bind; might <b>not</b> be null.
	 * @param property The {@link Property} to bind to; might <b>not</b> be null.
	 * @param filter The filter to apply to the elements; might be null.
	 * @return The {@link Binding} to further configure the binding with, never null
	 */
	public <ElementType> InMemoryDataProviderBinding<ElementType> bindHasHierarchicalDataProvider(HasHierarchicalDataProvider<ElementType> hasDataProvider,
																								  Property<ModelType, ElementType> property,
																								  SerializablePredicate<ElementType> filter) {
		Consumer<Binding<ElementType>> registration = removeBinding(property);
		TreeData<ElementType> elements = new TreeData<>();
		TreeDataProvider<ElementType> dataProvider = new TreeDataProvider<>(elements);
		dataProvider.setFilter(filter);
		hasDataProvider.setDataProvider(dataProvider);

		return addBinding(property, new DataProviderBinding<>(this.baseBindingAuditor, registration,
				property, dataProvider, new ElementHandle<ElementType>() {

			@Override
			public boolean contains(ElementType element) {
				return elements.contains(element);
			}

			@Override
			public Iterable<ElementType> get() {
				return toStream(elements.getRootItems()).collect(Collectors.toList());
			}

			private Stream<ElementType> toStream(List<ElementType> elements) {
				return elements.stream().flatMap(this::toStream);
			}

			private Stream<ElementType> toStream(ElementType element) {
				return Stream.concat(Stream.of(element), toStream(elements.getChildren(element)));
			}

			@Override
			public void add(ElementType parent, ElementType element, ElementType sibling) {
				elements.addItem(parent, element);
				elements.moveAfterSibling(element, sibling);
			}

			@Override
			public void remove(ElementType element) {
				elements.removeItem(element);
			}
		}));
	}

	// ######################################################################################################################################
	// ########################################################## PROPERTY ACCESS ###########################################################
	// ######################################################################################################################################

	/**
	 * Determines whether the given property exists in the model; or to put it differently, whether the parent
	 * properties of the property are all non-null.
	 * <p>
	 * The {@link Method} checks on the property parent's values, not on the properties' own value. If all parents are
	 * non-null but the property itself is null, the {@link Method} will still return true.
	 * <p>
	 * The result indicates whether it is safe to execute writing operations on the property.
	 * <p>
	 * Note that if the path from the property model's root to the given property is indexed, the used index context
	 * has an impact on the returned result.
	 * <p>
	 * For determination of existence, this handler's own index context is used.
	 *
	 * @param <PropertyValueType> The type of the property to check.
	 * @param property The property to check for existence; <b>not</b> allowed to be null.
	 * @return True if all the given properties' parents are non-null, false otherwise
	 */
	public <PropertyValueType> boolean exists(Property<ModelType, PropertyValueType> property) {
		if (this.parent != null) {
			return this.parent.exists(property, getContext());
		} else {
			return exists(property, null);
		}
	}

	/**
	 * Determines whether the given property exists in the model; or to put it differently, whether the parent
	 * properties of the property are all non-null.
	 * <p>
	 * The {@link Method} checks on the property parent's values, not on the properties' own value. If all parents are
	 * non-null but the property itself is null, the {@link Method} will still return true.
	 * <p>
	 * The result indicates whether it is safe to execute writing operations on the property.
	 * <p>
	 * Note that if the path from the property model's root to the given property is indexed, the used index context
	 * has an impact on the returned result.
	 * <p>
	 * For determination of existence, the given index context is used as an extension to the handler's own index
	 * context.
	 * 
	 * @param <PropertyValueType> The type of the property to check.
	 * @param property The property to check for existence; <b>not</b> allowed to be null.
	 * @param context The context which is used for determining the correct property; might be null.
	 * @return True if all of the given properties' parents are non-null, false otherwise
	 */
	public <PropertyValueType> boolean exists(Property<ModelType, PropertyValueType> property, Context context) {
		if (this.parent != null) {
			return this.parent.exists(property, getContext().union(context));
		} else {
			return property.exists(this.model, getContext().union(context));
		}
	}

	/**
	 * Fetches the value from inside the model data the given property points to.
	 * <p>
	 * Note that if the path from the property model's root to the given property is indexed, the used index context
	 * has an impact on the returned result.
	 * <p>
	 * For determining the correct property, this handler's own index context is used.
	 * 
	 * @param <PropertyValueType> The type of the property to get.
	 * @param property The property to fetch model data for; <b>not</b> allowed to be null.
	 * @return The target data in the model the given property points to; might be null if the property is null
	 */
	public <PropertyValueType> PropertyValueType get(Property<ModelType, PropertyValueType> property) {
		if (this.parent != null) {
			return this.parent.get(property, getContext());
		} else {
			return get(property, null);
		}
	}

	/**
	 * Fetches the value from inside the model data the given property points to.
	 * <p>
	 * Note that if the path from the property model's root to the given property is indexed, the used index context
	 * has an impact on the returned result.
	 * <p>
	 * For determining the correct property, the given index context is used as an extension to the handler's own
	 * index context.
	 * 
	 * @param <PropertyValueType> The type of the property to get.
	 * @param property The property to fetch model data for; <b>not</b> allowed to be null.
	 * @param context The context which is used for determining the correct property; might be null.
	 * @return The target data in the model the given property points to; might be null if the property is null
	 */
	public  <PropertyValueType> PropertyValueType get(Property<ModelType, PropertyValueType> property, Context context) {
		if (this.parent != null) {
			return this.parent.get(property, getContext().union(context));
		} else {
			return property.get(this.model, getContext().union(context), true);
		}
	}

	/**
	 * Sets the value inside the model data the given property points to.
	 * <p>
	 * Note that if the path from the property model's root to the given property is indexed, the used index context
	 * has an impact on the execution's result.
	 * <p>
	 * For determining the correct property, this handler's own index context is used.
	 * 
	 * @param <PropertyValueType> The type of the property to set.
	 * @param property The property to set inside the model; <b>not</b> allowed to be null.
	 * @param value The value to inject into the model.
	 */
	public <PropertyValueType> void set(Property<ModelType, PropertyValueType> property, PropertyValueType value) {
		if (this.parent != null) {
			this.parent.set(property, value, getContext());
		} else {
			set(property, value, null);
		}
	}

	/**
	 * Sets the value inside the model data the given property points to.
	 * <p>
	 * Note that if the path from the property model's root to the given property is indexed, the used index context
	 * has an impact on the execution's result.
	 * <p>
	 * For determining the correct property, the given index context is used as an extension to the handler's own
	 * index context.
	 * 
	 * @param <PropertyValueType> The type of the property to set.
	 * @param property The property to set inside the model; <b>not</b> allowed to be null.
	 * @param value The value to inject into the model; might be null.
	 * @param context The context which is used for determining the correct property; might be null.
	 */
	public <PropertyValueType> void set(Property<ModelType, PropertyValueType> property, PropertyValueType value, Context context) {
		if (this.parent != null) {
			this.parent.set(property, value, getContext().union(context));
		} else {
			context = getContext().union(context);
			property.set(this.model, value, context);
			update(property, context, Binding.UpdateType.EXCHANGE);
		}
	}

	/**
	 * Includes the element inside the model data the given property points to.
	 * <p>
	 * Note that if the path from the property model's root to the given property is indexed, the used index context
	 * has an impact on the execution's result.
	 * <p>
	 * For determining the correct property, this handler's own index context is used.
	 *
	 * @param <PropertyElementType> The type of element to include.
	 * @param <ReferenceType> The type that references the element.
	 * @param property The property to include inside the model; <b>not</b> allowed to be null.
	 * @param element The element to include into the model; might be null.
	 * @return The reference of the included value, never null
	 */
	public <PropertyElementType, ReferenceType> ReferenceType include(IncludableProperty<ModelType, ?, PropertyElementType, ReferenceType> property, PropertyElementType element) {
		if (this.parent != null) {
			return this.parent.include(property, element, getContext());
		} else {
			return include(property, element, null);
		}
	}

	/**
	 * Includes the element inside the model data the given property points to.
	 * <p>
	 * Note that if the path from the property model's root to the given property is indexed, the used index context
	 * has an impact on the execution's result.
	 * <p>
	 * For determining the correct property, the given index context is used as an extension to the handler's own
	 * index context.
	 *
	 * @param <PropertyElementType> The type of element to include.
	 * @param <ReferenceType> The type that references the element.
	 * @param property The property to include inside the model; <b>not</b> allowed to be null.
	 * @param element The element to include into the model; might be null.
	 * @param context The context which is used for determining the correct property; might be null.
	 * @return The reference of the included value, never null
	 */
	public <PropertyElementType, ReferenceType> ReferenceType include(IncludableProperty<ModelType, ?, PropertyElementType, ReferenceType> property, PropertyElementType element, Context context) {
		if (this.parent != null) {
			return this.parent.include(property, element, getContext().union(context));
		} else {
			context = getContext().union(context);
			ReferenceType reference = property.include(this.model, element, context);
			update(property, context, Binding.UpdateType.ADD);
			return reference;
		}
	}

	/**
	 * Inserts the element inside the model data the given property points to.
	 * <p>
	 * Note that if the path from the property model's root to the given property is indexed, the used index context
	 * has an impact on the execution's result.
	 * <p>
	 * For determining the correct property, this handler's own index context is used.
	 *
	 * @param <PropertyElementType> The type of element to insert.
	 * @param <ReferenceType> The type that references the element.
	 * @param property The property to insert inside the model; <b>not</b> allowed to be null.
	 * @param element The element to insert into the model; might be null.
	 * @param reference The reference that determines where to insert the element; <b>not</b> allowed to be null.
	 */
	public <PropertyElementType, ReferenceType> void insert(InsertableProperty<ModelType, ?, PropertyElementType, ReferenceType> property, PropertyElementType element, ReferenceType reference) {
		if (this.parent != null) {
			this.parent.insert(property, element, reference, getContext());
		} else {
			this.insert(property, element, reference, null);
		}
	}

	/**
	 * Inserts the element inside the model data the given property points to.
	 * <p>
	 * Note that if the path from the property model's root to the given property is indexed, the used index context
	 * has an impact on the execution's result.
	 * <p>
	 * For determining the correct property, the given index context is used as an extension to the handler's own
	 * index context.
	 *
	 * @param <PropertyElementType> The type of element to insert.
	 * @param <ReferenceType> The type that references the element.
	 * @param property The property to insert inside the model; <b>not</b> allowed to be null.
	 * @param element The element to insert into the model; might be null.
	 * @param reference The reference that determines where to insert the element; <b>not</b> allowed to be null.
	 * @param context The context which is used for determining the correct property; might be null.
	 */
	public <PropertyElementType, ReferenceType> void insert(InsertableProperty<ModelType, ?, PropertyElementType, ReferenceType> property, PropertyElementType element, ReferenceType reference, Context context) {
		if (this.parent != null) {
			this.parent.insert(property, element, reference, getContext().union(context));
		} else {
			context = getContext().union(context);
			property.insert(this.model, element, reference, context);
			update(property, context, Binding.UpdateType.ADD);
		}
	}

	/**
	 * Strips the element from the model data the given property points to.
	 * <p>
	 * Note that if the path from the property model's root to the given property is indexed, the used index context
	 * has an impact on the execution's result.
	 * <p>
	 * For determining the correct property, this handler's own index context is used.
	 *
	 * @param <PropertyElementType> The type of element to strip.
	 * @param <ReferenceType> The type that references the element.
	 * @param property The property to strip from the model; <b>not</b> allowed to be null.
	 * @return The reference of the stripped value, never null
	 */
	public <PropertyElementType, ReferenceType> ReferencedValue<ReferenceType, PropertyElementType> strip(StripableProperty<ModelType, ?, PropertyElementType, ReferenceType> property) {
		if (this.parent != null) {
			return this.parent.strip(property, getContext());
		} else {
			return strip(property, null);
		}
	}

	/**
	 * Strips the element from the model data the given property points to.
	 * <p>
	 * Note that if the path from the property model's root to the given property is indexed, the used index context
	 * has an impact on the execution's result.
	 * <p>
	 * For determining the correct property, the given index context is used as an extension to the handler's own
	 * index context.
	 *
	 * @param <PropertyElementType> The type of element to strip.
	 * @param <ReferenceType> The type that references the element.
	 * @param property The property to strip from the model; <b>not</b> allowed to be null.
	 * @param context The context which is used for determining the correct property; might be null.
	 * @return The reference of the stripped value, never null
	 */
	public <PropertyElementType, ReferenceType> ReferencedValue<ReferenceType, PropertyElementType> strip(StripableProperty<ModelType, ?, PropertyElementType, ReferenceType> property, Context context) {
		if (this.parent != null) {
			return this.parent.strip(property, getContext().union(context));
		} else {
			context = getContext().union(context);
			ReferencedValue<ReferenceType, PropertyElementType> referencedValue = property.strip(this.model, context);
			update(property, context, Binding.UpdateType.REMOVE);
			return referencedValue;
		}
	}

	/**
	 * Drops the element from the model data the given property points to.
	 * <p>
	 * Note that if the path from the property model's root to the given property is indexed, the used index context
	 * has an impact on the execution's result.
	 * <p>
	 * For determining the correct property, this handler's own index context is used.
	 *
	 * @param <PropertyElementType> The type of element to drop.
	 * @param <ReferenceType> The type that references the element.
	 * @param property The property to drop from the model; <b>not</b> allowed to be null.
	 * @param element The element to drop from the model; might be null.
	 * @return The reference of the dropped value, never null
	 */
	public <PropertyElementType, ReferenceType> ReferenceType drop(DropableProperty<ModelType, ?, PropertyElementType, ReferenceType> property, PropertyElementType element) {
		if (this.parent != null) {
			return this.parent.drop(property, element, getContext());
		} else {
			return drop(property, element, null);
		}
	}

	/**
	 * Drops the element from the model data the given property points to.
	 * <p>
	 * Note that if the path from the property model's root to the given property is indexed, the used index context
	 * has an impact on the execution's result.
	 * <p>
	 * For determining the correct property, the given index context is used as an extension to the handler's own
	 * index context.
	 *
	 * @param <PropertyElementType> The type of element to drop.
	 * @param <ReferenceType> The type that references the element.
	 * @param property The property to drop from the model; <b>not</b> allowed to be null.
	 * @param element The element to drop from the model; might be null.
	 * @param context The context which is used for determining the correct property; might be null.
	 * @return The reference of the dropped value, never null
	 */
	public <PropertyElementType, ReferenceType> ReferenceType drop(DropableProperty<ModelType, ?, PropertyElementType, ReferenceType> property, PropertyElementType element, Context context) {
		if (this.parent != null) {
			return this.parent.drop(property, element, getContext().union(context));
		} else {
			context = getContext().union(context);
			ReferenceType reference = property.drop(this.model, element, context);
			update(property, context, Binding.UpdateType.REMOVE);
			return reference;
		}
	}

	/**
	 * Extracts the element from the model data the given property points to.
	 * <p>
	 * Note that if the path from the property model's root to the given property is indexed, the used index context
	 * has an impact on the execution's result.
	 * <p>
	 * For determining the correct property, this handler's own index context is used.
	 *
	 * @param <PropertyElementType> The type of element to extract.
	 * @param <ReferenceType> The type that references the element.
	 * @param property The property to extract from the model; <b>not</b> allowed to be null.
	 * @param reference The reference to the element to drop from the model; might <b>not</b> be null.
	 * @return The reference of the extracted value, never null
	 */
	public <PropertyElementType, ReferenceType> PropertyElementType extract(ExtractableProperty<ModelType, ?, PropertyElementType, ReferenceType> property, ReferenceType reference) {
		if (this.parent != null) {
			return this.parent.extract(property, reference, getContext());
		} else {
			return extract(property, reference, null);
		}
	}

	/**
	 * Extracts the element from the model data the given property points to.
	 * <p>
	 * Note that if the path from the property model's root to the given property is indexed, the used index context
	 * has an impact on the execution's result.
	 * <p>
	 * For determining the correct property, the given index context is used as an extension to the handler's own
	 * index context.
	 *
	 * @param <PropertyElementType> The type of element to extract.
	 * @param <ReferenceType> The type that references the element.
	 * @param property The property to extract from the model; <b>not</b> allowed to be null.
	 * @param reference The reference to the element to drop from the model; might <b>not</b> be null.
	 * @param context The context which is used for determining the correct property; might be null.
	 * @return The reference of the extracted value, never null
	 */
	public <PropertyElementType, ReferenceType> PropertyElementType extract(ExtractableProperty<ModelType, ?, PropertyElementType, ReferenceType> property, ReferenceType reference, Context context) {
		if (this.parent != null) {
			return this.parent.extract(property, reference, getContext().union(context));
		} else {
			context = getContext().union(context);
			PropertyElementType element = property.extract(this.model, reference, context);
			update(property, context, Binding.UpdateType.REMOVE);
			return element;
		}
	}
}
