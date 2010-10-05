/*******************************************************************************
 * Copyright (c) 2010 C1WPS GmbH. All rights reserved.
 *******************************************************************************/
package de.c1wps.winterschool.ecf.serviceconsumer.listener;

import org.eclipse.ecf.osgi.services.discovery.IRemoteServiceEndpointDescription;
import org.eclipse.ecf.osgi.services.distribution.AbstractDistributionListener;
import org.eclipse.ecf.osgi.services.distribution.IProxyDistributionListener;
import org.eclipse.ecf.remoteservice.IRemoteServiceContainer;
import org.eclipse.ecf.remoteservice.IRemoteServiceReference;
import org.osgi.framework.ServiceRegistration;

@SuppressWarnings("restriction")
public class MyProxyDistributionListener extends
AbstractDistributionListener implements IProxyDistributionListener {
	/* (non-Javadoc)
	 * @see org.eclipse.ecf.osgi.services.distribution.IProxyDistributionListener#retrievingRemoteServiceReferences(org.eclipse.ecf.osgi.services.discovery.IRemoteServiceEndpointDescription, org.eclipse.ecf.remoteservice.IRemoteServiceContainer)
	 */
	public void retrievingRemoteServiceReferences(
			IRemoteServiceEndpointDescription endpointDescription,
			IRemoteServiceContainer remoteServiceContainer) {
		if (endpointDescription == null || remoteServiceContainer == null)
			return;
		StringBuffer sb = new StringBuffer(
				"OSGi ECF service distribution: retrievingRemoteServiceReferences").append("\n"); //$NON-NLS-1$ //$NON-NLS-2$

		sb.append(createTabs(1))
				.append("endpointDescription=").append(endpointDescription).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$

		sb.append(createTabs(1)).append("remoteServiceContainer") //$NON-NLS-1$
				.append("\n") //$NON-NLS-1$
				.append(printRemoteServiceContainer(2, remoteServiceContainer));
		sb.append("\n"); //$NON-NLS-1$
		log(null, sb.toString(), null);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ecf.osgi.services.distribution.IProxyDistributionListener#registering(org.eclipse.ecf.osgi.services.discovery.IRemoteServiceEndpointDescription, org.eclipse.ecf.remoteservice.IRemoteServiceContainer, org.eclipse.ecf.remoteservice.IRemoteServiceReference)
	 */
	public void registering(
			IRemoteServiceEndpointDescription endpointDescription,
			IRemoteServiceContainer remoteServiceContainer,
			IRemoteServiceReference remoteServiceReference) {
		if (endpointDescription == null || remoteServiceContainer == null
				|| remoteServiceReference == null)
			return;
		StringBuffer sb = new StringBuffer(
				"OSGi ECF service distribution: registering").append("\n"); //$NON-NLS-1$ //$NON-NLS-2$

		sb.append(createTabs(1))
				.append("endpointDescription=").append(endpointDescription).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$

		sb.append(createTabs(1)).append("remoteServiceContainer") //$NON-NLS-1$
				.append("\n") //$NON-NLS-1$
				.append(printRemoteServiceContainer(2, remoteServiceContainer));
		sb.append("\n"); //$NON-NLS-1$
		sb.append(createTabs(1)).append("remoteServiceReference") //$NON-NLS-1$
				.append("\n") //$NON-NLS-1$
				.append(printRemoteServiceReference(2, remoteServiceReference));
		sb.append("\n"); //$NON-NLS-1$
		log(null, sb.toString(), null);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ecf.osgi.services.distribution.IProxyDistributionListener#registered(org.eclipse.ecf.osgi.services.discovery.IRemoteServiceEndpointDescription, org.eclipse.ecf.remoteservice.IRemoteServiceContainer, org.eclipse.ecf.remoteservice.IRemoteServiceReference, org.osgi.framework.ServiceRegistration)
	 */
	public void registered(
			IRemoteServiceEndpointDescription endpointDescription,
			IRemoteServiceContainer remoteServiceContainer,
			IRemoteServiceReference remoteServiceReference,
			ServiceRegistration proxyServiceRegistration) {
		if (endpointDescription == null || remoteServiceContainer == null
				|| remoteServiceReference == null
				|| proxyServiceRegistration == null)
			return;
		StringBuffer sb = new StringBuffer(
				"OSGi ECF service distribution: registered").append("\n"); //$NON-NLS-1$ //$NON-NLS-2$

		sb.append(createTabs(1))
				.append("endpointDescription=").append(endpointDescription).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$

		sb.append(createTabs(1)).append("remoteServiceContainer") //$NON-NLS-1$
				.append("\n") //$NON-NLS-1$
				.append(printRemoteServiceContainer(2, remoteServiceContainer));
		sb.append("\n"); //$NON-NLS-1$
		sb.append(createTabs(1)).append("remoteServiceReference") //$NON-NLS-1$
				.append("\n") //$NON-NLS-1$
				.append(printRemoteServiceReference(2, remoteServiceReference));
		sb.append(createTabs(1)).append("proxyServiceRegistration=") //$NON-NLS-1$
				.append(proxyServiceRegistration);
		sb.append("\n"); //$NON-NLS-1$
		log(null, sb.toString(), null);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ecf.osgi.services.distribution.IProxyDistributionListener#unregistered(org.eclipse.ecf.osgi.services.discovery.IRemoteServiceEndpointDescription, org.osgi.framework.ServiceRegistration)
	 */
	public void unregistered(
			IRemoteServiceEndpointDescription endpointDescription,
			ServiceRegistration proxyServiceRegistration) {

		StringBuffer sb = new StringBuffer(
				"OSGi ECF service distribution: unregistered").append("\n"); //$NON-NLS-1$ //$NON-NLS-2$
		sb.append(createTabs(1))
				.append("endpointDescription=").append(endpointDescription).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$
		sb.append(createTabs(1)).append("proxyServiceRegistration=") //$NON-NLS-1$
				.append("\n") //$NON-NLS-1$
				.append(proxyServiceRegistration);
		sb.append("\n"); //$NON-NLS-1$
		log(null, sb.toString(), null);

	}
}
