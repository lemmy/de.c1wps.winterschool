/*******************************************************************************
 * Copyright (c) 2010 C1WPS GmbH. All rights reserved.
 *******************************************************************************/
package de.c1wps.winterschool.domain.kunde.fachwerte;


/**
 * Kundennummer
 * 
 * (Fachwert)
 */
public class Kundennummer {

	private int _wert;

	@SuppressWarnings("unused")
	private Kundennummer() {
		
	}
	
	/**
	 * Konstruktor
	 * 
	 * nutze die value()-Methoden, um Exemplare der Klasse Kundennummer zu
	 * erhalten
	 */
	protected Kundennummer(int value) {
		_wert = value;
	}

	//
	// statische Methoden
	//

	/**
	 * Erzeugt aus einem gegebenen Integer eine Kundennummer
	 * 
	 * @require isValid(intValue)
	 */
	public static Kundennummer valueOf(int intValue) {
		assert isValid(intValue) : "Precondition: isValid(intValue)";
		return new Kundennummer(intValue);
	}

	/**
	 * Erzeugt aus einem gegebenen String eine Kundennummer
	 * 
	 * @require isValid(stringValue)
	 */
	public static Kundennummer valueOf(String stringValue) {
		assert isValid(stringValue) : "Precondition: isValid(stringValue)";
		int intValue = Integer.parseInt(stringValue);
		return valueOf(intValue);
	}

	/**
	 * Pr�ft, ob ein gegebener Integer eine g�ltige Kundennummer repr�sentiert
	 */
	public static boolean isValid(int intValue) {
		return intValue > 1000;
	}

	/**
	 * Pr�ft, ob ein gegebener String eine g�ltige Kundennummer repr�sentiert
	 */
	public static boolean isValid(String value) {
		int intValue;

		try {
			intValue = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return false;
		}

		return isValid(intValue);
	}

	public int value() {
		return _wert;
	}

	public String toString() {
		return Integer.toString(_wert);
	}

	public boolean equals(Object obj) {
		Kundennummer nr = (Kundennummer) obj;
		return _wert == nr._wert;
	}

	public int hashCode() {
		return _wert;
	}

	public static Kundennummer defaultValue() {
		return Kundennummer.valueOf(1001);
	}
}
