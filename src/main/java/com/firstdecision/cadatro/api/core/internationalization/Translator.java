package com.firstdecision.cadatro.api.core.internationalization;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;

@Component
public class Translator {

	private static ResourceBundleMessageSource messageSource;

	Translator(ResourceBundleMessageSource messageSource) {
		Translator.messageSource = messageSource;
	}

	public static String toLocale(String msgCode) {
		return getMessage(msgCode, null);
	}

	public static String toLocale(String msgCode, Object[] args) {
		return getMessage(msgCode, args);
	}

	public static String toLocale(ObjectError error) {
		return messageSource.getMessage(error, LocaleContextHolder.getLocale());
	}

	private static String getMessage(String msgCode, Object[] args) {
		return (messageSource == null) ? 
				msgCode : messageSource.getMessage(msgCode, args, LocaleContextHolder.getLocale());
	}

	
	
}
