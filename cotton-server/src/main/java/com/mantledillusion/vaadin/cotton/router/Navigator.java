package com.mantledillusion.vaadin.cotton.router;

import com.vaadin.flow.component.UI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Utility class providing static functionality on the get current {@link UI}'s navigation.
 */
public class Navigator {

    /**
     * Returns the first value of the query parameter with the given name.
     *
     * @param name The name of the query parameter; might <b>not</b> be null.
     * @return The query parameter, never null, might be empty if there are no values
     */
    public static Optional<String> peekQueryParameter(String name) {
        return alterQueryParameter(name, parameters -> parameters.isEmpty()
                ? Optional.empty()
                : Optional.of(parameters.iterator().next()));
    }

    /**
     * Returns the values of the query parameter with the given name.
     *
     * @param name The name of the query parameter; might <b>not</b> be null.
     * @return The query parameters, never null, might be empty if there are no values
     */
    public static List<String> peekQueryParameters(String name) {
        return alterQueryParameter(name, parameters -> parameters);
    }

    /**
     * Returns and removes the first value of the query parameter with the given name.
     *
     * @param name The name of the query parameter; might <b>not</b> be null.
     * @return The query parameter, never null, might be empty if there are no values
     */
    public static Optional<String> popQueryParameter(String name) {
        return alterQueryParameter(name, parameters -> {
            var iter = parameters.iterator();
            Optional<String> result = Optional.empty();
            if (iter.hasNext()) {
                result = Optional.of(iter.next());
                iter.remove();
            }
            return result;
        });
    }

    /**
     * Returns removes the values of the query parameter with the given name.
     *
     * @param name The name of the query parameter; might <b>not</b> be null.
     * @return The query parameters, never null, might be empty if there are no values
     */
    public static List<String> popQueryParameters(String name) {
        return alterQueryParameter(name, parameters -> {
            var result = new ArrayList<>(parameters);
            parameters.clear();
            return result;
        });
    }

    /**
     * Adds values to the query parameter with the given name.
     *
     * @param name The name of the query parameter; might <b>not</b> be null.
     * @return The query parameters, never null, might be empty if there are no values
     */
    public static List<String> pushQueryParameters(String name, String... values) {
        return alterQueryParameter(name, parameters -> {
            parameters.addAll(Arrays.asList(values));
            return parameters;
        });
    }

    /**
     * Replaces the values of the query parameter with the given name.
     *
     * @param name The name of the query parameter; might <b>not</b> be null.
     * @return The query parameters, never null, might be empty if there are no values
     */
    public static List<String> replaceQueryParameters(String name, String... values) {
        return alterQueryParameter(name, parameters -> {
            parameters.clear();
            parameters.addAll(Arrays.asList(values));
            return parameters;
        });
    }

    private static <V> V alterQueryParameter(String name, Function<List<String>, V> alteringExtractor) {
        var ui = Optional.ofNullable(UI.getCurrent())
                .orElseThrow(() -> new IllegalStateException("Unable to access query parameters on a non-request thread"));
        var location = ui.getActiveViewLocation();
        var parameters = location.getQueryParameters();

        var unalteredValues = parameters.getParameters(name);
        var alteredValues = new ArrayList<>(unalteredValues);
        var extracted = alteringExtractor.apply(alteredValues);

        if (!unalteredValues.equals(alteredValues)) {
            parameters = parameters.excluding(name);
            if (!alteredValues.isEmpty()) {
                parameters.merging(name, alteredValues.toArray(new String[0]));
            }

            var pathString = location.getPath();
            var queryString = parameters.getQueryString();
            if (!queryString.isEmpty()) {
                pathString += '/' + queryString;
            }

            ui.getPage()
                    .getHistory()
                    .replaceState(null, pathString);
        }

        return extracted;
    }
}
