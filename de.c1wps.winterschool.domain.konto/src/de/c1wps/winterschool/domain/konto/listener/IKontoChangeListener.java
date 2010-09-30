/*******************************************************************************
 * Copyright (c) 2010 C1WPS GmbH. All rights reserved.
 *******************************************************************************/
package de.c1wps.winterschool.domain.konto.listener;

import de.c1wps.winterschool.domain.konto.material.Konto;

public interface IKontoChangeListener {

	public void kontoGeaendert(Konto konto);
}
