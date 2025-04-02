package com.mantledillusion.vaadin.cotton.router;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.router.*;

import java.util.*;

/**
 * Utility class providing static functionality on the current {@link com.vaadin.flow.component.UI}'s route.
 */
public class Routing {

    /**
     * A basic builder for routes.
     *
     * @param <B> The type of the implementing builder.
     */
    public static abstract class RoutingBuilder<B extends RoutingBuilder<B>> {

        private final Map<String, List<String>> queryParameters = new HashMap<>();

        private RoutingBuilder(QueryParameters queryParameters) {
            this.queryParameters.putAll(queryParameters.getParameters());
        }

        /**
         * Builder method, overrides the query param of the given name with the given values.
         *
         * @param name The name of the parameter to set; might <b>not</b> be null
         * @param values The values to set; might <b>not</b> be null
         * @return this
         */
        @SuppressWarnings("unchecked")
        public B withQueryParam(String name, Object... values) {
            if (name == null) {
                throw new IllegalArgumentException("Unable to set query parameter using a null name");
            } else if (values == null || values.length == 0) {
                throw new IllegalArgumentException("Unable to set query parameter using null or no values");
            }
            this.queryParameters.put(name, Arrays.stream(values)
                    .map(String::valueOf)
                    .toList());
            return (B) this;
        }

        /**
         * Builder method, drops the query param of the given name.
         *
         * @param name The name of the parameter to remove; might <b>not</b> be null
         * @return this
         */
        @SuppressWarnings("unchecked")
        public B withoutQueryParam(String name) {
            if (name == null) {
                throw new IllegalArgumentException("Unable to remove query parameter using a null name");
            }
            this.queryParameters.remove(name);
            return (B) this;
        }

        /**
         * Causes the current thread's {@link UI} to navigate to the configured target.
         */
        public final void navigate() {
            navigate(new QueryParameters(this.queryParameters));
        }

        protected abstract void navigate(QueryParameters parameters);
    }

    /**
     * A builder for routes which allows setting query parameters.
     */
    public static abstract class RoutingQueryBuilder extends RoutingBuilder<RoutingQueryBuilder> {

        private RoutingQueryBuilder(QueryParameters queryParameters) {
            super(queryParameters);
        }
    }

    /**
     * A builder for routes which allows setting path and query parameters.
     */
    public static class RoutingPathBuilder extends RoutingBuilder<RoutingPathBuilder> {

        private final Class<? extends Component> navigationTarget;
        private final Map<String, String> routeParameters = new HashMap<>();

        private RoutingPathBuilder(Class<? extends Component> navigationTarget, RouteParameters routeParameters, QueryParameters queryParameters) {
            super(queryParameters);
            this.navigationTarget = navigationTarget;

            routeParameters.getParameterNames().forEach(name -> this.routeParameters.put(name, routeParameters.get(name).orElseThrow()));
        }

        /**
         * Builder method, overrides the path param of the given name with the given value.
         *
         * @param name The name of the parameter to set; might <b>not</b> be null
         * @param value The value to set; might <b>not</b> be null
         * @return this
         */
        public RoutingPathBuilder withPathParam(String name, Object value) {
            if (name == null) {
                throw new IllegalArgumentException("Unable to set path parameter using a null name");
            } else if (value == null) {
                throw new IllegalArgumentException("Unable to set path parameter using null or no values");
            }
            this.routeParameters.put(name, String.valueOf(value));
            return this;
        }

        /**
         * Builder method, removes the path param of the given name.
         *
         * @param name The name of the parameter to remove; might <b>not</b> be null
         * @return this
         */
        public RoutingPathBuilder withoutPathParam(String name) {
            if (name == null) {
                throw new IllegalArgumentException("Unable to remove path parameter using a null name");
            }
            this.routeParameters.remove(name);
            return this;
        }

        @Override
        protected void navigate(QueryParameters queryParameters) {
            getCurrent().navigate(this.navigationTarget, new RouteParameters(this.routeParameters), queryParameters);
        }
    }

    /**
     * Reloads the current thread's {@link UI}.
     *
     * @see Page#reload()
     */
    private static void reload() {
        getCurrent().getPage().reload();
    }

    /**
     * Navigates the current thread's {@link UI} to the given location.
     *
     * @see UI#navigate(String, QueryParameters)
     * @param location The location to navigate to; might <b>not</b> be null.
     */
    public static void navigate(String location) {
        navigate(location, QueryParameters.empty());
    }

    /**
     * Navigates the current thread's {@link UI} to the given location.
     *
     * @see UI#navigate(String, QueryParameters)
     * @param location The location to navigate to; might <b>not</b> be null.
     * @param queryParameters The query parameters to use; might <b>not</b> be null.
     */
    public static void navigate(String location, QueryParameters queryParameters) {
        create(location, queryParameters).navigate();
    }

    /**
     * Begins building a routing to the given location.
     *
     * @see UI#navigate(String, QueryParameters)
     * @param location The location to navigate to; might <b>not</b> be null.
     * @return A new {@link RoutingQueryBuilder}, never null
     */
    public static RoutingQueryBuilder create(String location) {
        return create(location, QueryParameters.empty());
    }

    /**
     * Begins building a routing to the given location.
     *
     * @see UI#navigate(String, QueryParameters)
     * @param location The location to navigate to; might <b>not</b> be null.
     * @param queryParameters The query parameters to use; might <b>not</b> be null.
     * @return A new {@link RoutingQueryBuilder}, never null
     */
    public static RoutingQueryBuilder create(String location, QueryParameters queryParameters) {
        if (location == null) {
            throw new IllegalArgumentException("Cannot create a routing to a null location");
        } else if (queryParameters == null) {
            throw new IllegalArgumentException("Cannot create a routing using null query parameters");
        }
        return new RoutingQueryBuilder(queryParameters) {

            @Override
            protected void navigate(QueryParameters parameters) {
                getCurrent().navigate(location, parameters);
            }
        };
    }

    /**
     * Navigates the current thread's {@link UI} to the given target.
     *
     * @see UI#navigate(Class, Object, QueryParameters)
     * @param <C> The type of the {@link HasUrlParameter} to build a route to.
     * @param <T> The type of the parameter to route with.
     * @param navigationTarget The target to navigate to; might <b>not</b> be null.
     * @param pathParameter The {@link HasUrlParameter}'s path parameter; might be null.
     */
    public static <C extends Component & HasUrlParameter<T>, T> void navigate(Class<C> navigationTarget, T pathParameter) {
        navigate(navigationTarget, pathParameter, QueryParameters.empty());
    }

    /**
     * Navigates the current thread's {@link UI} to the given target.
     *
     * @see UI#navigate(Class, Object, QueryParameters)
     * @param <C> The type of the {@link HasUrlParameter} to build a route to.
     * @param <T> The type of the parameter to route with.
     * @param navigationTarget The target to navigate to; might <b>not</b> be null.
     * @param pathParameter The {@link HasUrlParameter}'s path parameter; might be null.
     * @param queryParameters The query parameters to use; might <b>not</b> be null.
     */
    public static <C extends Component & HasUrlParameter<T>, T> void navigate(Class<C> navigationTarget, T pathParameter, QueryParameters queryParameters) {
        create(navigationTarget, pathParameter, queryParameters).navigate();
    }

    /**
     * Begins building a routing to the given target.
     *
     * @see UI#navigate(Class, Object, QueryParameters)
     * @param <C> The type of the {@link HasUrlParameter} to build a route to.
     * @param <T> The type of the parameter to route with.
     * @param navigationTarget The target to navigate to; might <b>not</b> be null.
     * @param pathParameter The {@link HasUrlParameter}'s path parameter; might be null.
     * @return A new {@link RoutingQueryBuilder}, never null
     */
    public static <C extends Component & HasUrlParameter<T>, T> RoutingQueryBuilder create(Class<C> navigationTarget, T pathParameter) {
        return create(navigationTarget, pathParameter, QueryParameters.empty());
    }

    /**
     * Begins building a routing to the given target.
     *
     * @see UI#navigate(Class, Object, QueryParameters)
     * @param <C> The type of the {@link HasUrlParameter} to build a route to.
     * @param <T> The type of the parameter to route with.
     * @param navigationTarget The target to navigate to; might <b>not</b> be null.
     * @param pathParameter The {@link HasUrlParameter}'s path parameter; might be null.
     * @param queryParameters The query parameters to use; might <b>not</b> be null.
     * @return A new {@link RoutingQueryBuilder}, never null
     */
    public static <C extends Component & HasUrlParameter<T>, T> RoutingQueryBuilder create(Class<C> navigationTarget, T pathParameter, QueryParameters queryParameters) {
        if (navigationTarget == null) {
            throw new IllegalArgumentException("Cannot create a routing to a null target");
        } else if (queryParameters == null) {
            throw new IllegalArgumentException("Cannot create a routing using null query parameters");
        }
        return new RoutingQueryBuilder(queryParameters) {

            @Override
            protected void navigate(QueryParameters parameters) {
                getCurrent().navigate(navigationTarget, pathParameter, parameters);
            }
        };
    }

    /**
     * Navigates the current thread's {@link UI} to the given target.
     *
     * @see UI#navigate(Class, RouteParameters, QueryParameters)
     * @param navigationTarget The target to navigate to; might <b>not</b> be null.
     */
    public static void navigate(Class<? extends Component> navigationTarget) {
        navigate(navigationTarget, RouteParameters.empty(), QueryParameters.empty());
    }

    /**
     * Navigates the current thread's {@link UI} to the given target.
     *
     * @see UI#navigate(Class, RouteParameters, QueryParameters)
     * @param navigationTarget The target to navigate to; might <b>not</b> be null.
     * @param pathParameters The path parameters to use; might <b>not</b> be null.
     */
    public static void navigate(Class<? extends Component> navigationTarget, RouteParameters pathParameters) {
        navigate(navigationTarget, pathParameters, QueryParameters.empty());
    }

    /**
     * Navigates the current thread's {@link UI} to the given target.
     *
     * @see UI#navigate(Class, RouteParameters, QueryParameters)
     * @param navigationTarget The target to navigate to; might <b>not</b> be null.
     * @param queryParameters The query parameters to use; might <b>not</b> be null.
     */
    public static void navigate(Class<? extends Component> navigationTarget, QueryParameters queryParameters) {
        navigate(navigationTarget, RouteParameters.empty(), queryParameters);
    }

    /**
     * Navigates the current thread's {@link UI} to the given target.
     *
     * @see UI#navigate(Class, RouteParameters, QueryParameters)
     * @param navigationTarget The target to navigate to; might <b>not</b> be null.
     * @param pathParameters The path parameters to use; might <b>not</b> be null.
     * @param queryParameters The query parameters to use; might <b>not</b> be null.
     */
    public static void navigate(Class<? extends Component> navigationTarget, RouteParameters pathParameters, QueryParameters queryParameters) {
        create(navigationTarget, pathParameters, queryParameters).navigate();
    }

    /**
     * Begins building a routing to the given target.
     *
     * @see UI#navigate(Class, RouteParameters, QueryParameters)
     * @param navigationTarget The target to navigate to; might <b>not</b> be null.
     * @return A new {@link RoutingQueryBuilder}, never null
     */
    public static RoutingPathBuilder create(Class<? extends Component> navigationTarget) {
        return new RoutingPathBuilder(navigationTarget, RouteParameters.empty(), QueryParameters.empty());
    }

    /**
     * Begins building a routing to the given target.
     *
     * @see UI#navigate(Class, RouteParameters, QueryParameters)
     * @param navigationTarget The target to navigate to; might <b>not</b> be null.
     * @param pathParameters The path parameters to use; might <b>not</b> be null.
     * @return A new {@link RoutingPathBuilder}, never null
     */
    public static RoutingPathBuilder create(Class<? extends Component> navigationTarget, RouteParameters pathParameters) {
        return new RoutingPathBuilder(navigationTarget, pathParameters, QueryParameters.empty());
    }

    /**
     * Begins building a routing to the given target.
     *
     * @see UI#navigate(Class, RouteParameters, QueryParameters)
     * @param navigationTarget The target to navigate to; might <b>not</b> be null.
     * @param queryParameters The query parameters to use; might <b>not</b> be null.
     * @return A new {@link RoutingPathBuilder}, never null
     */
    public static RoutingPathBuilder create(Class<? extends Component> navigationTarget, QueryParameters queryParameters) {
        return new RoutingPathBuilder(navigationTarget, RouteParameters.empty(), queryParameters);
    }

    /**
     * Begins building a routing to the given target.
     *
     * @see UI#navigate(Class, RouteParameters, QueryParameters)
     * @param navigationTarget The target to navigate to; might <b>not</b> be null.
     * @param pathParameters The path parameters to use; might <b>not</b> be null.
     * @param queryParameters The query parameters to use; might <b>not</b> be null.
     * @return A new {@link RoutingPathBuilder}, never null
     */
    public static RoutingPathBuilder create(Class<? extends Component> navigationTarget, RouteParameters pathParameters, QueryParameters queryParameters) {
        if (navigationTarget == null) {
            throw new IllegalArgumentException("Cannot create a routing to a null target");
        } else if (pathParameters == null) {
            throw new IllegalArgumentException("Cannot create a routing using null path parameters");
        } else if (queryParameters == null) {
            throw new IllegalArgumentException("Cannot create a routing using null query parameters");
        }
        return new RoutingPathBuilder(navigationTarget, pathParameters, queryParameters);
    }

    private static UI getCurrent() {
        return Optional.of(UI.getCurrent())
                .orElseThrow(() -> new IllegalStateException("No UI present on current thread"));
    }
}
