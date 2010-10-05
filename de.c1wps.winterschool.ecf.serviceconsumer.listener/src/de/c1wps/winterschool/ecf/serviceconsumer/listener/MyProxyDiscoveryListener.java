/*******************************************************************************
 * Copyright (c) 2010 C1WPS GmbH. All rights reserved.
 *******************************************************************************/
package de.c1wps.winterschool.ecf.serviceconsumer.listener;

import org.eclipse.ecf.discovery.IServiceInfo;
import org.eclipse.ecf.osgi.services.discovery.AbstractDiscoveryListener;
import org.eclipse.ecf.osgi.services.discovery.IProxyDiscoveryListener;

@SuppressWarnings("restriction")
public class MyProxyDiscoveryListener extends AbstractDiscoveryListener implements IProxyDiscoveryListener {

	/* (non-Javadoc)
	 * @see org.eclipse.ecf.osgi.services.discovery.IProxyDiscoveryListener#discovered(org.eclipse.ecf.discovery.IServiceInfo)
	 */
	public void discovered(IServiceInfo serviceInfo) {
		if (serviceInfo == null)
			return;
		StringBuffer sb = new StringBuffer(
				"OSGi ECF service discovery: remote service discovered") //$NON-NLS-1$
				.append("\n"); //$NON-NLS-1$
		sb.append(printServiceInfo(1, serviceInfo));
		log(null, sb.toString(), null);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ecf.osgi.services.discovery.IProxyDiscoveryListener#undiscovered(org.eclipse.ecf.discovery.IServiceInfo)
	 */
	public void undiscovered(IServiceInfo serviceInfo) {
		if (serviceInfo == null)
			return;
		StringBuffer sb = new StringBuffer(
				"OSGi ECF service discovery: remote service undiscovered") //$NON-NLS-1$
				.append("\n"); //$NON-NLS-1$
		sb.append(printServiceInfo(1, serviceInfo));
		log(null, sb.toString(), null);
	}
}
