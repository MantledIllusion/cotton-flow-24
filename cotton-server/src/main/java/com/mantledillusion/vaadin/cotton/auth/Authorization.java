package com.mantledillusion.vaadin.cotton.auth;


import com.mantledillusion.essentials.expression.Expression;
import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.server.VaadinServletRequest;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.ServletException;

import java.security.Principal;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

/**
 * Utility class providing static functionality on the current {@link VaadinRequest}'s principal.
 */
public class Authorization {

    /**
     * Returns whether there is a {@link Principal} on the current {@link VaadinRequest}.
     *
     * @return True if there is a {@link Principal}, false otherwise
     */
    public static boolean hasPrincipal() {
        return getPrincipal()
                .isPresent();
    }

    /**
     * Returns the {@link Principal} on the current {@link VaadinRequest}.
     *
     * @return The {@link Principal}, never null, might be empty if there is none
     */
    public static Optional<Principal> getPrincipal() {
        return Optional.ofNullable(VaadinRequest.getCurrent())
                .map(VaadinRequest::getUserPrincipal);
    }

    /**
     * Returns the name of the {@link Principal} on the current {@link VaadinRequest}.
     *
     * @return The {@link Principal}'s name, never null, might be empty if there is none
     */
    public static Optional<String> getPrincipalName() {
        return getPrincipal()
                .map(Principal::getName);
    }

    /**
     * Logs a @{@link Principal} in on the current {@link VaadinRequest} using the given credentials.
     *
     * @param username The name of the @{@link Principal}; might <b>not</b> be null.
     * @param password The password to log the @{@link Principal} in with; might <b>not</b> be null.
     * @return The logged in {@link Principal}, never null
     */
    public static Principal loginPrincipal(String username, String password) {
        var request = Optional.ofNullable(VaadinServletRequest.getCurrent())
                .orElseThrow(() -> new IllegalStateException("Unable to login a principal on a non-request thread"));
        try {
            request.login(username, password);
            request.getHttpServletRequest().changeSessionId();
            return request.getUserPrincipal();
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Logs the {@link Principal} out of the current {@link VaadinRequest}.
     *
     * @return The {@link Principal}, never null, might be empty if there was none to log out
     */
    public static Optional<Principal> logoutPrincipal() {
        var request = Optional.ofNullable(VaadinServletRequest.getCurrent())
                .orElseThrow(() -> new IllegalStateException("Unable to logout a principal from a non-request thread"));

        var principal = Optional.ofNullable(request.getUserPrincipal());

        if (principal.isPresent()) {
            try {
                request.logout();
                request.getWrappedSession().invalidate();
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }

        return principal;
    }

    /**
     * Returns whether there is a {@link Principal} on the current {@link VaadinRequest} whose roles match the given expression.
     *
     * @param roleExpression An expression of roles the {@link Principal} has to match; might <b>not</b> be null.
     * @return True if there is a {@link Principal} that also matches the given expression, false otherwise
     */
    public static boolean isPermitted(Expression<String> roleExpression) {
        return roleExpression.evaluate(Authorization::isPermitted);
    }

    /**
     * Returns whether there is a {@link Principal} on the current {@link VaadinRequest} that is also in all the given roles.
     *
     * @param roles The roles the {@link Principal} has to be in; might <b>not</b> be null.
     * @return True if there is a {@link Principal} that is also in all the given roles, false otherwise
     */
    public static boolean isPermitted(String... roles) {
        return Optional.ofNullable(VaadinRequest.getCurrent())
                .filter(request -> request.getUserPrincipal() != null)
                .map(request -> Arrays.stream(roles)
                        .allMatch(request::isUserInRole))
                .orElse(false);
    }

    /**
     * Returns whether the {@link Principal} (or lack thereof) on the current {@link VaadinRequest} applies to
     * the @{@link DenyAll}, @{@link AnonymousAllowed}, @{@link PermitAll} and @{@link RolesAllowed} annotations
     * on the given type.
     *
     * @param type The type the anonymous user or {@link Principal} has to apply to; might <b>not</b> be null.
     * @return True if the anonymous user or {@link Principal} applies to the type, false otherwise
     */
    public static boolean isPermitted(Class<?> type) {
        // THE TYPE CANNOT HAVE @DenyAll
        return !type.isAnnotationPresent(DenyAll.class)
                    // ... AND EITHER HAS TO HAVE @PermitAll
                    && (type.isAnnotationPresent(AnonymousAllowed.class)
                        // ... OR DECLARES @PermitAll
                        || (type.isAnnotationPresent(PermitAll.class)
                            // ... AND THERE IS A PRINCIPAL
                            && Authorization.hasPrincipal())
                        // ... OR SPECIFIES @RolesAllows
                        || (type.isAnnotationPresent(RolesAllowed.class)
                            // ... OF WHICH THE PRINCIPAL HAS TO HAVE AT LEAST ONE
                            && Arrays.stream(type.getAnnotation(RolesAllowed.class).value())
                                .anyMatch(Authorization::isPermitted)));
    }

    /**
     * Returns the roles defined to the given type by @{@link RolesAllowed}.
     *
     * @param type The type to fetch roles from; might <b>not</b> be null.
     * @return A set of roles a {@link Principal} has to apply to, never null, might be empty
     */
    public static Set<String> getPermissions(Class<?> type) {
        return Set.of(type.getAnnotation(RolesAllowed.class).value());
    }
}
