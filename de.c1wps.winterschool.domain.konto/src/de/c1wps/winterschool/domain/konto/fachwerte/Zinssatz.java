/*******************************************************************************
 * Copyright (c) 2010 C1WPS GmbH. All rights reserved.
 *******************************************************************************/
package de.c1wps.winterschool.domain.konto.fachwerte;


public class Zinssatz extends BasisFachwert
{
   /**
    * Exemplarvariable zum Speichern des Wertes des Zinssatzes. Der Zinssatz wird als Faktor
    * gespeichert, also bedeutet 1.0: 100% und 0.03: 3%.
    */
   private float _wert;

   /**
    * serialUID
    */
   private static final long serialVersionUID = -4175841412657336244L;
	
	
   /**
    * Konstruktor f�r den Zinssatz. Der Konstruktor akzeptiert Werte < 0.5 als < 50% und wandelt
    * alle Werte >= 0.5 so um, dass sie >= 0,5% sind.
    * 
    * @param wert Der Wert des Zinssatz als float.
    */
   private Zinssatz(float wert)
   {
      _wert = wert >= 0.5f ? wert / 100 : wert;
   }

   public static Zinssatz valueOf(float zinssatz)
   {
   	assert isValid(zinssatz) : "Precondition isValid(zinssatz)";
   	return new Zinssatz(zinssatz);
   }

   /**
    * Gibt den Wert des Zinssatz zur�ck. Der Zinssatz wird als Faktor gespeichert, also bedeutet
    * 1.0: 100% und 0.03: 3%.
    * 
    * @return Der Wert des Zinssatzes als float.
    */
   public float value()
   {
   	return _wert;
   }
   
   public static boolean isValid(float zinssatz)
   {
   		return zinssatz >= 0;
   }

   public Zinssatz addiere(Zinssatz zins) {
		assert zins != null : "Vorbedingung zins != null";
		assert zins.value() >= 0 : "Vorbedingung zins.value() >= 0";
		return new Zinssatz(_wert + zins.value());
   }
   
	public String toString()
	{
		return Float.toString(_wert);
	}
}



