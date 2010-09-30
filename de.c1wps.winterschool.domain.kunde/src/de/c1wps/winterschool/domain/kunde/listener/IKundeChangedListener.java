/*******************************************************************************
 * Copyright (c) 2010 C1WPS GmbH. All rights reserved.
 *******************************************************************************/
package de.c1wps.winterschool.domain.kunde.listener;

import de.c1wps.winterschool.domain.kunde.fachwerte.Kundennummer;

public interface IKundeChangedListener {

	void kundeGeandert(Kundennummer kundeNummer);
}
