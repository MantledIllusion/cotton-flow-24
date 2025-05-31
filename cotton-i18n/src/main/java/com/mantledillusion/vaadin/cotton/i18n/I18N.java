package com.mantledillusion.vaadin.cotton.i18n;

import com.vaadin.flow.i18n.I18NProvider;
import com.vaadin.flow.internal.LocaleUtil;

import java.util.Locale;

/**
 * Static localization provider.
 */
public class I18N {

    private I18N() {}

    /**
     * Translates the given message ID using the {@link I18NProvider} of the current session's {@link Locale}.
     *
     * @see I18NProvider#getTranslation(Object, Locale, Object...)
     * @param msgId The message id to localize; might be null.
     * @param indexedMessageParameters Optional parameters to replace at their index in the message; might be null
     * @return A localized and parameter filled message, or the given msgId if localization was not possible
     */
    public static String getTranslation(Object msgId, Object... indexedMessageParameters) {
        return getTranslation(LocaleUtil.getLocale(LocaleUtil::getI18NProvider), msgId, indexedMessageParameters);
    }

    /**
     * Translates the given message ID using the {@link I18NProvider} of the given {@link Locale}.
     *
     * @see I18NProvider#getTranslation(Object, Locale, Object...)
     * @param locale The {@link Locale} to translate to; might be null {@link Locale}
     * @param msgId The message id to localize; might be null.
     * @param indexedMessageParameters Optional parameters to replace at their index in the message; might be null
     * @return A localized and parameter filled message, or the given msgId if localization was not possible
     */
    public static String getTranslation(Locale locale, Object msgId, Object... indexedMessageParameters) {
        return msgId == null ? null : LocaleUtil.getI18NProvider()
                .map(i18n -> i18n.getTranslation(msgId, locale, indexedMessageParameters))
                .orElse(String.valueOf(msgId));
    }
}
