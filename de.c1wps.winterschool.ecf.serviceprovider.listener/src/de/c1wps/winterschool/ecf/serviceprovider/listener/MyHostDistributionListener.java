/*******************************************************************************
 * Copyright (c) 2010 C1WPS GmbH. All rights reserved.
 *******************************************************************************/
package de.c1wps.winterschool.ecf.serviceprovider.listener;

import org.eclipse.ecf.osgi.services.distribution.IHostDistributionListener;
import org.eclipse.ecf.remoteservice.IRemoteServiceContainer;
import org.eclipse.ecf.remoteservice.IRemoteServiceRegistration;
import org.osgi.framework.ServiceReference;

@SuppressWarnings("restriction")
public class MyHostDistributionListener implements IHostDistributionListener {

	/* (non-Javadoc)
	 * @see org.eclipse.ecf.osgi.services.distribution.IHostDistributionListener#registered(org.osgi.framework.ServiceReference, org.eclipse.ecf.remoteservice.IRemoteServiceContainer, org.eclipse.ecf.remoteservice.IRemoteServiceRegistration)
	 */
	public void registered(ServiceReference arg0, IRemoteServiceContainer arg1,
			IRemoteServiceRegistration arg2) {
		System.out.println("registered");
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ecf.osgi.services.distribution.IHostDistributionListener#unregistered(org.osgi.framework.ServiceReference, org.eclipse.ecf.remoteservice.IRemoteServiceRegistration)
	 */
	public void unregistered(ServiceReference arg0,
			IRemoteServiceRegistration arg1) {
		System.out.println("unregistered");
	}
}
