/*******************************************************************************
 * Copyright (c) 2010 C1WPS GmbH. All rights reserved.
 *******************************************************************************/
package de.c1wps.winterschool.ecf.serviceconsumer.listener;

import org.eclipse.ecf.osgi.services.discovery.IProxyDiscoveryListener;
import org.eclipse.ecf.osgi.services.distribution.IProxyContainerFinder;
import org.eclipse.ecf.osgi.services.distribution.IProxyDistributionListener;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

@SuppressWarnings("restriction")
public class ServiceConsumerListenerActivator implements BundleActivator {

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		context.registerService(IProxyDiscoveryListener.class.getName(), new MyProxyDiscoveryListener(), null);
		context.registerService(IProxyDistributionListener.class.getName(), new MyProxyDistributionListener(), null);
		
		context.registerService(IProxyContainerFinder.class.getName(), new MyProxyContainerFinder(), null);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
	}
}
