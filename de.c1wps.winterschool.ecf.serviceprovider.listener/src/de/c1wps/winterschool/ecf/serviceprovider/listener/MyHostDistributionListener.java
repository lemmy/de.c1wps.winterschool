/*******************************************************************************
 * Copyright (c) 2010 C1WPS GmbH. All rights reserved.
 *******************************************************************************/
package de.c1wps.winterschool.ecf.serviceprovider.listener;

import org.eclipse.ecf.osgi.services.distribution.AbstractDistributionListener;
import org.eclipse.ecf.osgi.services.distribution.IHostDistributionListener;
import org.eclipse.ecf.remoteservice.IRemoteServiceContainer;
import org.eclipse.ecf.remoteservice.IRemoteServiceRegistration;
import org.osgi.framework.ServiceReference;

@SuppressWarnings("restriction")
public class MyHostDistributionListener extends AbstractDistributionListener implements IHostDistributionListener {

	/* (non-Javadoc)
	 * @see org.eclipse.ecf.osgi.services.distribution.IHostDistributionListener#registered(org.osgi.framework.ServiceReference, org.eclipse.ecf.remoteservice.IRemoteServiceContainer, org.eclipse.ecf.remoteservice.IRemoteServiceRegistration)
	 */
	public void registered(ServiceReference serviceReference,
			IRemoteServiceContainer remoteServiceContainer,
			IRemoteServiceRegistration remoteRegistration) {

		if (serviceReference == null || remoteServiceContainer == null
				|| remoteRegistration == null)
			return;

		StringBuffer sb = new StringBuffer(
				"OSGi ECF service distribution: registered").append("\n"); //$NON-NLS-1$ //$NON-NLS-2$
		sb.append(createTabs(1)).append("serviceReference=") //$NON-NLS-1$
				.append(serviceReference);
		sb.append("\n"); //$NON-NLS-1$
		sb.append(createTabs(1)).append("remoteServiceContainer") //$NON-NLS-1$
				.append("\n") //$NON-NLS-1$
				.append(printRemoteServiceContainer(2, remoteServiceContainer));
		sb.append("\n"); //$NON-NLS-1$
		sb.append(createTabs(1)).append("remoteServiceRegistration") //$NON-NLS-1$
				.append("\n") //$NON-NLS-1$
				.append(printRemoteServiceRegistration(2, remoteRegistration));
		log(serviceReference, sb.toString(), null);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ecf.osgi.services.distribution.IHostDistributionListener#unregistered(org.osgi.framework.ServiceReference, org.eclipse.ecf.remoteservice.IRemoteServiceRegistration)
	 */
	public void unregistered(ServiceReference serviceReference,
			IRemoteServiceRegistration remoteRegistration) {
		if (serviceReference == null || remoteRegistration == null)
			return;

		StringBuffer sb = new StringBuffer(
				"OSGi ECF service distribution: unregistered").append("\n"); //$NON-NLS-1$ //$NON-NLS-2$
		sb.append(createTabs(1)).append("serviceReference=") //$NON-NLS-1$
				.append(serviceReference);
		sb.append("\n"); //$NON-NLS-1$
		sb.append(createTabs(1)).append("remoteServiceRegistration") //$NON-NLS-1$
				.append(printRemoteServiceRegistration(2, remoteRegistration));
		sb.append("\n"); //$NON-NLS-1$
		log(serviceReference, sb.toString(), null);
	}
}
