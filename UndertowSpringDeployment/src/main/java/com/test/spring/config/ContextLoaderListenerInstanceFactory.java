package com.test.spring.config;

import io.undertow.servlet.api.InstanceFactory;
import io.undertow.servlet.api.InstanceHandle;

import org.springframework.web.context.ContextLoaderListener;

import com.test.SupportAssistApplicationContext;

public class ContextLoaderListenerInstanceFactory implements
		InstanceFactory<ContextLoaderListener> {

	public InstanceHandle<ContextLoaderListener> createInstance()
			throws InstantiationException {
		ContextLoaderListenerInstanceHandle ContextLoaderListenerInstanceHandle = new ContextLoaderListenerInstanceHandle();
		return ContextLoaderListenerInstanceHandle;
	}

	class ContextLoaderListenerInstanceHandle implements
			InstanceHandle<ContextLoaderListener> {

		@Override
		public ContextLoaderListener getInstance() {
			ContextLoaderListener ContextLoaderListener = new ContextLoaderListener(
					SupportAssistApplicationContext.INSTANCE.getContext());
			return ContextLoaderListener;
		}

		@Override
		public void release() {
			// TODO Auto-generated method stub
		}

	}

}