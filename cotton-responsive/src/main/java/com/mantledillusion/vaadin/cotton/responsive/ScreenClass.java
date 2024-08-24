package com.mantledillusion.vaadin.cotton.responsive;

/**
 * A {@link ResponsiveClass} for matching screen classes.
 */
public class ScreenClass implements ResponsiveClass {

    /**
     * A large screen of a desktop computer, TV (including set top boxed or game consoles) or other home appliances
     */
    public static final String PANEL = "PANEL";

    /**
     * A compact screen of a mobile phone, tablet, e-reader, watch or car
     */
    public static final String DISPLAY = "DISPLAY";

    /**
     * A virtually rendered screen of web crawler, voice assistant or AR/VR device
     */
    public static final String VIRTUAL = "VIRTUAL";

    /**
     * An anonymized or unclassified screen
     */
    public static final String UNKNOWN = "UNKNOWN";
}
