/*******************************************************************************
 * Copyright (c) 2010 C1WPS GmbH. All rights reserved.
 *******************************************************************************/
package de.c1wps.winterschool.ecf.serviceconsumer.listener;

import org.eclipse.ecf.discovery.IServiceInfo;
import org.eclipse.ecf.osgi.services.discovery.IProxyDiscoveryListener;

@SuppressWarnings("restriction")
public class MyProxyDiscoveryListener implements IProxyDiscoveryListener {

	/* (non-Javadoc)
	 * @see org.eclipse.ecf.osgi.services.discovery.IProxyDiscoveryListener#discovered(org.eclipse.ecf.discovery.IServiceInfo)
	 */
	public void discovered(IServiceInfo arg0) {
		System.out.println(arg0);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ecf.osgi.services.discovery.IProxyDiscoveryListener#undiscovered(org.eclipse.ecf.discovery.IServiceInfo)
	 */
	public void undiscovered(IServiceInfo arg0) {
		System.out.println(arg0);
	}
}
