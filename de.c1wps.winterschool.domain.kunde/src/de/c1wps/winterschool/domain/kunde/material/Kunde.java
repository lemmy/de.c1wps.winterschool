/*******************************************************************************
 * Copyright (c) 2010 C1WPS GmbH. All rights reserved.
 *******************************************************************************/
package de.c1wps.winterschool.domain.kunde.material;

import de.c1wps.winterschool.domain.kunde.fachwerte.Kundennummer;

public class Kunde extends Person{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8276603133338718051L;
	private Kundennummer _kundennummer;
	
	@SuppressWarnings("unused")
	private Kunde() {
		super();
	}
	
	public Kunde(Kundennummer kundennummer, String vorname, String nachname, Adresse adresse) {
		super(vorname, nachname, adresse);
		_kundennummer = kundennummer;
	}
	
	public Kundennummer getKundennummer() {
		return _kundennummer;
	}
			
	public String toString() {
		String info =  "Kd.nr:" + _kundennummer + ", " + _vorname + " " + _nachname + " " + _adresse + "\n";
		return info;
	}
	
	public Kunde copy() {
		Adresse adresse = new Adresse(getAdresse().getStrasse(), getAdresse().getHausnummer(), getAdresse().getPlz(), getAdresse().getOrt());
		return new Kunde(_kundennummer, getVorname(), getNachname(), adresse);
	}
}
