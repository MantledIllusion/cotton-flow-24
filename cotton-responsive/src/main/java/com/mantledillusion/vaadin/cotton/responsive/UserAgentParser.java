package com.mantledillusion.vaadin.cotton.responsive;

import java.util.Optional;

public interface UserAgentParser {

    Optional<UserAgent> parse(HeaderContainer headerContainer);
}
