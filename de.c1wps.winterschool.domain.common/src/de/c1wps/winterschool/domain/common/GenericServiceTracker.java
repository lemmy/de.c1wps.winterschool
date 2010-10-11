/*******************************************************************************
 * Copyright (c) 2010 C1WPS GmbH. All rights reserved.
 *******************************************************************************/
package de.c1wps.winterschool.domain.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

public class GenericServiceTracker<S> extends ServiceTracker {

	private S service;
	private List<IGenericServiceListener<S>> serviceListener;
	private final Class<S> clazz;

	public GenericServiceTracker(BundleContext context, Class<S> clazz) {

		super(context, clazz.getName(), null);
		this.clazz = clazz;
		this.serviceListener = Collections
				.synchronizedList(new ArrayList<IGenericServiceListener<S>>());
	}

	/* (non-Javadoc)
	 * @see org.osgi.util.tracker.ServiceTracker#addingService(org.osgi.framework.ServiceReference)
	 */
	@Override
	public Object addingService(ServiceReference reference) {
		service = clazz.cast(super.addingService(reference));
		for (IGenericServiceListener<S> listener : serviceListener) {
			listener.bindService(service);
		}
		return service;
	}
	
	/* (non-Javadoc)
	 * @see org.osgi.util.tracker.ServiceTracker#removedService(org.osgi.framework.ServiceReference, java.lang.Object)
	 */
	@Override
	public void removedService(ServiceReference reference, Object aService) {
		service = clazz.cast(aService);
		for (IGenericServiceListener<S> listener : serviceListener) {
			listener.unbindService(service);
		}
		super.removedService(reference, service);
	}

	public void addServiceListener(IGenericServiceListener<S> serviceListener) {
		// inform immediately if service available
		if (this.service != null) {
			serviceListener.bindService(service);
		}
		this.serviceListener.add(serviceListener);
	}
}
