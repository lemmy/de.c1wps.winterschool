/*******************************************************************************
 * Copyright (c) 2010 C1WPS GmbH. All rights reserved.
 *******************************************************************************/
package de.c1wps.winterschool.domain.kunde.material;

import java.io.Serializable;


/**
 * Klasse Adresse
 * 
 * Eine Adresse besteht aus den Adressdaten:
 * Strasse, Hausnummer, Plz und Ort 
 * 
 * @author Massoud Najafi
 */
public class Adresse implements Serializable{
	private static final long serialVersionUID = -6702479686629330366L;
	private String _strasse;
	private int _plz;
	private String _hausnummer;
	private String _ort;
	
	
	/**
	 * Konstruktur f�r eine Adresse
	 * 
	 * @param strasse - z.B. Musterstrasse
	 * @param hausnummer - z.B. 22 a
	 * @param plz - die Postleitzahl z.B. 21222
	 * @param ort - z.B. HH
	 */
	public Adresse(String strasse, String hausnummer, int plz, String ort) {
		assert strasse != null : "Vorbedingung  strasse != null";
		assert plz > 0 : "Vorbedingung  plz > 0";
		assert ort != null : "Vorbedingung  ort != null";
		
		_strasse = strasse;
		_plz = plz;
		_hausnummer = hausnummer;
		_ort = ort;
	}
	
	/**
	 * @return gibt die Hausnummer als String zur�ck
	 */
	public String getHausnummer() {
		return _hausnummer;
	}

	/**
	 * @return gibt den Ort als String zur�ck
	 */
	public String getOrt() {
		return _ort;
	}

	/**
	 * @return gibt die Plz zur�ck
	 */
	public int getPlz() {
		return _plz;
	}

	/**
	 * @return gibt die Strasse als String zur�ck
	 */
	public String getStrasse() {
		return _strasse;
	}

	/**
	 * Setzt die Hausnummer
	 * @param hausnummer - die neue Hausnummer
	 */
	public void setHausnummer(String hausnummer) {
		assert hausnummer!= null : "Vorbedingung  hausnummer!= null";
		_hausnummer = hausnummer;
	}

	/**
	 * Setzt den Ort
	 * @param ort - der neue Ort
	 */
	public void setOrt(String ort) {
		assert ort != null : "Vorbedingung  ort != null";
		_ort = ort;
	}
	
	/**
	 * Setzt die Postleitzahl
	 * @param plz - die neue Postleitzahl
	 */
	public void setPlz(int plz) {
		assert plz > 0 : "Vorbedingung  plz > 0";
		_plz = plz;
	}

	/**
	 * Setzt die Strasse
	 * @param strasse - die neue Strasse
	 */
	public void setStrasse(String strasse) {
		assert strasse != null : "Vorbedingung  strasse != null";
		_strasse = strasse;
	}
	
	@Override
	public boolean equals(Object obj) {
		Adresse adresse = (Adresse)obj;
		
		return _strasse.equals(adresse.getStrasse()) && 
		_hausnummer.equals(adresse.getHausnummer()) && 
		_plz == adresse.getPlz() && 
		_ort.equals(adresse.getOrt());
	}
	
	@Override	
	public int hashCode() {
		return (_strasse + _hausnummer + _plz + _ort).hashCode();
	}
	
	@Override
	public String toString() {
		return 	_strasse + " " + _hausnummer + ", " + _plz + " " + _ort;
	}
}
