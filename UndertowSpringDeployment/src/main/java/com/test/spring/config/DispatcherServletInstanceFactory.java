package com.test.spring.config;

import io.undertow.servlet.api.InstanceFactory;
import io.undertow.servlet.api.InstanceHandle;

import org.springframework.web.servlet.DispatcherServlet;

import com.test.SupportAssistApplicationContext;

public class DispatcherServletInstanceFactory implements
		InstanceFactory<DispatcherServlet> {

	public InstanceHandle<DispatcherServlet> createInstance()
			throws InstantiationException {
		DispatcherServletInstanceHandle dispatcherServletInstanceHandle = new DispatcherServletInstanceHandle();
		return dispatcherServletInstanceHandle;
	}

	class DispatcherServletInstanceHandle implements
			InstanceHandle<DispatcherServlet> {

		@Override
		public DispatcherServlet getInstance() {
			DispatcherServlet dispatcherServlet = new DispatcherServlet(
					SupportAssistApplicationContext.INSTANCE.getContext());
			return dispatcherServlet;
		}

		@Override
		public void release() {
			// TODO Auto-generated method stub
		}

	}

}