/*******************************************************************************
 * Copyright (c) 2010 C1WPS GmbH. All rights reserved.
 *******************************************************************************/
package de.c1wps.winterschool.smartproxy;

import de.c1wps.winterschool.domain.kunde.material.Kunde;
import de.c1wps.winterschool.domain.kunde.service.IKundenService;

public abstract class SmartKundenServiceMemImpl implements IKundenService {

	public abstract boolean _rosgiAktualisiereKunde(Kunde kunde);
	
	/* (non-Javadoc)
	 * @see de.c1wps.winterschool.domain.kunde.service.IKundenService#aktualisiereKunde(de.c1wps.winterschool.domain.kunde.material.Kunde)
	 */
	public boolean aktualisiereKunde(Kunde kunde) {
		System.out.println("this is the smart proxy registered as a surrogate");
		return _rosgiAktualisiereKunde(kunde);
	}
}
