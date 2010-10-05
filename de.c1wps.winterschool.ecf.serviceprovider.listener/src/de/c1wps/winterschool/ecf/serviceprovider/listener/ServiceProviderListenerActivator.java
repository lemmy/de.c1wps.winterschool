/*******************************************************************************
 * Copyright (c) 2010 C1WPS GmbH. All rights reserved.
 *******************************************************************************/
package de.c1wps.winterschool.ecf.serviceprovider.listener;

import org.eclipse.ecf.osgi.services.discovery.IHostDiscoveryListener;
import org.eclipse.ecf.osgi.services.distribution.IHostContainerFinder;
import org.eclipse.ecf.osgi.services.distribution.IHostDistributionListener;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

@SuppressWarnings("restriction")
public class ServiceProviderListenerActivator implements BundleActivator {

	/* (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		
		context.registerService(IHostDiscoveryListener.class.getName(), new MyHostDiscoveryListener(), null);
		context.registerService(IHostDistributionListener.class.getName(), new MyHostDistributionListener(), null);
		
		context.registerService(IHostContainerFinder.class.getName(), new MyHostContainerFinder(), null);
	}

	/* (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
	}
}
