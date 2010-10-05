package de.c1wps.winterschool.service.kunde;

import de.c1wps.winterschool.domain.kunde.material.Kunde;
import de.c1wps.winterschool.domain.kunde.service.IKundenService;

public abstract class SmartKundenServiceMemImpl implements IKundenService {

	public abstract boolean _rosgiAktualisiereKunde(Kunde kunde);
	
	/* (non-Javadoc)
	 * @see de.c1wps.winterschool.domain.kunde.service.IKundenService#aktualisiereKunde(de.c1wps.winterschool.domain.kunde.material.Kunde)
	 */
	public boolean aktualisiereKunde(Kunde kunde) {
		System.out.println("smartproxy");
		_rosgiAktualisiereKunde(kunde);
		return false;
	}
}
