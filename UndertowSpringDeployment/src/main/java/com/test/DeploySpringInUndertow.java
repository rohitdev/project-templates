package com.test;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.servlet.api.InstanceFactory;
import io.undertow.servlet.api.InstanceHandle;
import io.undertow.servlet.api.ListenerInfo;
import io.undertow.servlet.api.ServletInfo;

import java.util.concurrent.Semaphore;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.servlet.DispatcherServlet;

public class DeploySpringInUndertow {

	public static void main(String[] args) throws ServletException,
			InterruptedException {
		DeploySpringInUndertow deploySpringInUndertow = new DeploySpringInUndertow();
		deploySpringInUndertow.configureUndertow();
		server.start();
		semaphore.acquire();
	}

	private void configureUndertow() throws ServletException {

		DeploymentInfo servletBuilder = Servlets.deployment()
				.setClassLoader(DeploySpringInUndertow.class.getClassLoader())
				.setContextPath(CONTEXT_PATH).setDeploymentName("test.war")
				.addServlets(createDispatcherServlet())
				.addListener(createContextLoaderListener());

		DeploymentManager manager = Servlets.defaultContainer().addDeployment(
				servletBuilder);
		manager.deploy();
		PathHandler path = Handlers.path(Handlers.redirect("/myapp"))
				.addPrefixPath("/myapp", manager.start());

		server = Undertow.builder().addHttpListener(8080, "localhost")
				.setHandler(path).build();

	}

	private ListenerInfo createContextLoaderListener() {
		// contextHandler.addEventListener(new ContextLoaderListener(context));
		return new ListenerInfo(ContextLoaderListener.class,
				new ContextLoaderListenerInstanceFactory());
	}

	private ServletInfo createDispatcherServlet() {

		// contextHandler.addServlet(new ServletHolder(dispatcherServlet),
		// MAPPING_URL);
		return Servlets.servlet("DispatcherServlet", DispatcherServlet.class,
				new DispatcherServletInstanceFactory()).addMapping(MAPPING_URL);
	}

	class ContextLoaderListenerInstanceFactory implements
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

	class DispatcherServletInstanceFactory implements
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

	private static final Semaphore semaphore = new Semaphore(0);

	private static final String MAPPING_URL = "/*";
	private static final String CONTEXT_PATH = "/myapp";

	private static Undertow server;
	private static final Logger log = LoggerFactory
			.getLogger(DeploySpringInUndertow.class);
}
