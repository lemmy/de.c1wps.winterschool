/*******************************************************************************
 * Copyright (c) 2010 C1WPS GmbH. All rights reserved.
 *******************************************************************************/
package de.c1wps.winterschool.ecf.serviceprovider.listener;

import org.eclipse.ecf.osgi.services.distribution.DefaultHostContainerFinder;
import org.eclipse.ecf.osgi.services.distribution.IHostContainerFinder;
import org.eclipse.ecf.remoteservice.IRemoteServiceContainer;
import org.osgi.framework.ServiceReference;

@SuppressWarnings("restriction")
public class MyHostContainerFinder extends DefaultHostContainerFinder implements
		IHostContainerFinder {

	public MyHostContainerFinder() {
		super(true, new String[]{"ecf.generic.server"});
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ecf.osgi.services.distribution.DefaultHostContainerFinder#findHostContainers(org.osgi.framework.ServiceReference, java.lang.String[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public IRemoteServiceContainer[] findHostContainers(
			ServiceReference serviceReference,
			String[] serviceExportedInterfaces,
			String[] serviceExportedConfigs, String[] serviceIntents) {
		return super.findHostContainers(serviceReference, serviceExportedInterfaces,
				serviceExportedConfigs, serviceIntents);
	}

}
