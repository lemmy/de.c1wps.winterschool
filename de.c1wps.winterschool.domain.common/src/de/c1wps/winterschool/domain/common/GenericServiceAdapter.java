/*******************************************************************************
 * Copyright (c) 2010 C1WPS GmbH. All rights reserved.
 *******************************************************************************/
package de.c1wps.winterschool.domain.common;

public class GenericServiceAdapter<ServiceInterface> implements
		IGenericServiceListener<ServiceInterface> {

	public void bindService(ServiceInterface service) {
	}

	public void unbindService(ServiceInterface service) {
	}
}
