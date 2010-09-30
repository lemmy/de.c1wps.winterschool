/*******************************************************************************
 * Copyright (c) 2010 C1WPS GmbH. All rights reserved.
 *******************************************************************************/
package de.c1wps.winterschool.domain.konto.fachwerte;

import java.io.Serializable;

public class Betrag implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6198830539013172712L;
	/**
	 * Der Betrag als Longrepr�sentation. 
	 * (AAAA.BB) z.B : 10022 -> 100.22 
	 */
	private long _wert;

	/**
	 * Konstruktor 
	 * Erstellt einen Betrag aus einem Longwert. 
	 * Die ersten zwei Stellen des Longwert werden als Nachkommastellen interpretiert.
	 * (AAAA.BB) z.B : 10022 -> 100.22 
	 */	
	private Betrag(long wert)
	{
		_wert = wert;
	}

	/**
	 * gibt den Betrag als long Repr�sentation zur�ck. 
	 * (AAAA.BB) z.B : 100.22 -> 10022   
	 */
	public long value()
	{
		return _wert;
	}

	/**
	 * Erstellt einen Betrag aus einem Longwert. 
	 * Die ersten zwei Stellen des Longwert werden als Nachkommastellen interpretiert.
	 * (AAAA.BB) z.B : 10022 -> 100.22
	 *  	 
	 * @param betrag - der Betrag in Longrepr�sentation 
	 * @return den neu erzeugten Betrag
	 */
	public static Betrag valueOf(long betrag)
	{
		assert isValid(betrag) : "Precondition isValid(betrag)";
		return new Betrag(betrag);
	}

	/**
	 * Pr�ft, ob der angegebene Betrag als Longwert, ein g�ltiger Betrag ist.
	 *  
	 * @param betrag - der zu �berpr�fende Longwert.
	 * 
	 * @return Ist der Longwert g�ltig.
	 */
	public static boolean isValid(long betrag)
	{
		return betrag >= 0;
	}

	/**
	 * Pr�ft, ob der angegebene angegebene Betrag als String, ein g�ltiger Betrag ist.
	 *  
	 * @param betragString - der zu �berpr�fende String.
	 * 
	 * @return Ist der String ein g�ltiger Betrag.
	 */
	public static boolean isValid(String betragString)
	{
		if (betragString == null)
		{
			return false;
		}

		betragString = betragString.trim();
		String[] betragStringTeile = betragString.split(",");

		if (betragStringTeile.length > 2 || betragStringTeile.length == 0)
		{
			return false;
		}
		
		long betrag;

		try
		{
			betrag = Long.parseLong(betragStringTeile[0]) * 100;

			if (betrag < 0)
				return false;
			else if (betragStringTeile.length == 2)
			{
				long nachkommastellen = Long.parseLong(betragStringTeile[1]);

				if(nachkommastellen > 99 || nachkommastellen < 0)
					return false;
			}
			
		} catch (NumberFormatException e)
		{
			return false;
		}
		return true;
	}

	/**
	 * Wandelt einen Betrag als String anhand folgender Formatierung in einen Betrag um:
	 * "...dddd,dd"  - dabei steht "d" f�r eine Dezimalstelle
	 * 
	 * @param betragString - der Betrag als String 
	 * @return den neu erzeugten Betrag
	 */
	public static Betrag valueOf(String betragString)
	{
		assert isValid(betragString) : "Precondition isValid(betragString)";

		betragString = betragString.trim();
		String[] betragStringTeile = betragString.split(",");

		long betrag = Long.parseLong(betragStringTeile[0]) * 100;

		if (betragStringTeile.length == 2)
		{
			long nachkommastellen = Long.parseLong(betragStringTeile[1]);
			if(betragStringTeile[1].length() == 1)
				nachkommastellen *= 10;
			betrag += nachkommastellen;
		}

		return new Betrag(betrag);
	}

	@Override
	public String toString()
	{
		long vorkomma = _wert / 100;
		long nachkomma = _wert % 100;

		String a = vorkomma
		+ ","
		+ ((nachkomma >= 10) ? "" + nachkomma : "0"+ nachkomma);
		return a;
	}
	
	@Override
	public boolean equals(Object obj) {
		assert obj instanceof Betrag : "Precondition obj instanceof Betrag";
		Betrag betrag = (Betrag) obj;
		return _wert == betrag._wert;
	}
	
	@Override
	public int hashCode() {
		return new Long(_wert).hashCode();
	}
}
