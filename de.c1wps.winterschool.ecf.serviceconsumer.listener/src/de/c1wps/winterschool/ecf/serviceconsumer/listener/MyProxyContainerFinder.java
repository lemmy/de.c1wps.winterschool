/*******************************************************************************
 * Copyright (c) 2010 C1WPS GmbH. All rights reserved.
 *******************************************************************************/
package de.c1wps.winterschool.ecf.serviceconsumer.listener;

import org.eclipse.ecf.discovery.identity.IServiceID;
import org.eclipse.ecf.osgi.services.discovery.IRemoteServiceEndpointDescription;
import org.eclipse.ecf.osgi.services.distribution.DefaultProxyContainerFinder;
import org.eclipse.ecf.osgi.services.distribution.IProxyContainerFinder;
import org.eclipse.ecf.remoteservice.IRemoteServiceContainer;

@SuppressWarnings("restriction")
public class MyProxyContainerFinder extends DefaultProxyContainerFinder
		implements IProxyContainerFinder {

	public MyProxyContainerFinder() {
		super(true);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ecf.osgi.services.distribution.DefaultProxyContainerFinder#findProxyContainers(org.eclipse.ecf.discovery.identity.IServiceID, org.eclipse.ecf.osgi.services.discovery.IRemoteServiceEndpointDescription)
	 */
	@Override
	public IRemoteServiceContainer[] findProxyContainers(IServiceID serviceID,
			IRemoteServiceEndpointDescription endpointDescription) {
		return super.findProxyContainers(serviceID, endpointDescription);
	}
}
