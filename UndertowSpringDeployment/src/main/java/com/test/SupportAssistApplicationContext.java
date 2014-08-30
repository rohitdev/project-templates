package com.test;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public enum SupportAssistApplicationContext {

	INSTANCE;

	public final AnnotationConfigWebApplicationContext getContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setConfigLocation(CONFIG_LOCATION);
		return context;
	}

	private static final String CONFIG_LOCATION = "com.test.common.config";

}
