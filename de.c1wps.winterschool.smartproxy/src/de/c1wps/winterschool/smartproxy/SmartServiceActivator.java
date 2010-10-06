/*******************************************************************************
 * Copyright (c) 2010 C1WPS GmbH. All rights reserved.
 *******************************************************************************/
package de.c1wps.winterschool.smartproxy;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;

import ch.ethz.iks.r_osgi.RemoteOSGiService;
import ch.ethz.iks.r_osgi.SurrogateRegistration;
import de.c1wps.winterschool.domain.kunde.service.IKundenService;

public class SmartServiceActivator implements BundleActivator, SurrogateRegistration, ServiceListener {

	private BundleContext context;

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		this.context = bundleContext;
		context.addServiceListener(this, "(" + Constants.OBJECTCLASS + "=" + IKundenService.class.getName() + ")");
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
	}

	/* (non-Javadoc)
	 * @see org.osgi.framework.ServiceListener#serviceChanged(org.osgi.framework.ServiceEvent)
	 */
	public void serviceChanged(ServiceEvent event) {
		int type = event.getType();
		switch (type) {
		case ServiceEvent.REGISTERED:
			final ServiceReference legacyService = event.getServiceReference();
			
			final Dictionary<String, Object> properties = new Hashtable<String, Object>();
			properties.put("service.exported.interfaces", "*");
//			properties.put(RemoteOSGiService.R_OSGi_REGISTRATION, Boolean.TRUE);
			properties.put(RemoteOSGiService.SMART_PROXY, SmartKundenServiceMemImpl.class.getName());
			properties.put(SurrogateRegistration.SERVICE_REFERENCE, legacyService);
			
			context.registerService(SurrogateRegistration.class.getName(), this, properties);
			
			break;

		default:
			break;
		}
	}
}
