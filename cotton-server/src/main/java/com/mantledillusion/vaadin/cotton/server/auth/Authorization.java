package com.mantledillusion.vaadin.cotton.server.auth;


import com.vaadin.flow.server.VaadinRequest;

import java.util.Arrays;
import java.util.Optional;

/**
 * Utility class providing static functionality on the current {@link VaadinRequest}'s principal.
 */
public class Authorization {

    /**
     * Returns whether there is a principal on the current {@link VaadinRequest} that is also in all the given roles.
     *
     * @param roles The roles the principal has to be in; might <b>not</b> be null.
     * @return True if there is a principal that is also in all the given roles, false otherwise
     */
    public static boolean permitted(String... roles) {
        return Optional.ofNullable(VaadinRequest.getCurrent())
                .filter(request -> request.getUserPrincipal() != null)
                .map(request -> Arrays.stream(roles)
                        .allMatch(request::isUserInRole))
                .orElse(false);
    }
}
