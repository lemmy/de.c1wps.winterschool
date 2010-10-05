/*******************************************************************************
 * Copyright (c) 2010 C1WPS GmbH. All rights reserved.
 *******************************************************************************/
package de.c1wps.winterschool.ecf.serviceconsumer.listener;

import org.eclipse.ecf.osgi.services.discovery.IRemoteServiceEndpointDescription;
import org.eclipse.ecf.osgi.services.distribution.IProxyDistributionListener;
import org.eclipse.ecf.remoteservice.IRemoteServiceContainer;
import org.eclipse.ecf.remoteservice.IRemoteServiceReference;
import org.osgi.framework.ServiceRegistration;

@SuppressWarnings("restriction")
public class MyProxyDistributionListener implements IProxyDistributionListener {

	/* (non-Javadoc)
	 * @see org.eclipse.ecf.osgi.services.distribution.IProxyDistributionListener#unregistered(org.eclipse.ecf.osgi.services.discovery.IRemoteServiceEndpointDescription, org.osgi.framework.ServiceRegistration)
	 */
	public void unregistered(IRemoteServiceEndpointDescription arg0,
			ServiceRegistration arg1) {
		System.out.println("unregisterd");
		System.out.println(arg0);
		System.out.println(arg1);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ecf.osgi.services.distribution.IProxyDistributionListener#registered(org.eclipse.ecf.osgi.services.discovery.IRemoteServiceEndpointDescription, org.eclipse.ecf.remoteservice.IRemoteServiceContainer, org.eclipse.ecf.remoteservice.IRemoteServiceReference, org.osgi.framework.ServiceRegistration)
	 */
	public void registered(IRemoteServiceEndpointDescription arg0,
			IRemoteServiceContainer arg1, IRemoteServiceReference arg2,
			ServiceRegistration arg3) {
		System.out.println("registered");
		System.out.println(arg0);
		System.out.println(arg1);
		System.out.println(arg2);
		System.out.println(arg3);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ecf.osgi.services.distribution.IProxyDistributionListener#registering(org.eclipse.ecf.osgi.services.discovery.IRemoteServiceEndpointDescription, org.eclipse.ecf.remoteservice.IRemoteServiceContainer, org.eclipse.ecf.remoteservice.IRemoteServiceReference)
	 */
	public void registering(IRemoteServiceEndpointDescription arg0,
			IRemoteServiceContainer arg1, IRemoteServiceReference arg2) {
		System.out.println("registering");
		System.out.println(arg0);
		System.out.println(arg1);
		System.out.println(arg2);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ecf.osgi.services.distribution.IProxyDistributionListener#retrievingRemoteServiceReferences(org.eclipse.ecf.osgi.services.discovery.IRemoteServiceEndpointDescription, org.eclipse.ecf.remoteservice.IRemoteServiceContainer)
	 */
	public void retrievingRemoteServiceReferences(
			IRemoteServiceEndpointDescription arg0, IRemoteServiceContainer arg1) {
		System.out.println("retrievingRemoteServiceReferences");
		System.out.println(arg0);
		System.out.println(arg1);
	}
}
