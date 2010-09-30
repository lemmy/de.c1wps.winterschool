/*******************************************************************************
 * Copyright (c) 2010 C1WPS GmbH. All rights reserved.
 *******************************************************************************/
package de.c1wps.winterschool.domain.konto.material;

import java.io.Serializable;


public class BasisMaterial implements Serializable {
	
	private static final long serialVersionUID = -7595196635062400069L;


	public BasisMaterial() {
	      _version = 1;
	}

	public BasisMaterial(int version) {
	      _version = version;
	}
	
	public int getVersion() {
		return _version;
	}
	
	public void incrementVersion() {
		_version++;
	}

	
	private int _version;
}
