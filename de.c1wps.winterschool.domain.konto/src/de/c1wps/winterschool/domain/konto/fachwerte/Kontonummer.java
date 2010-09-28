package de.c1wps.winterschool.domain.konto.fachwerte;

import java.io.Serializable;

/**
 * Kontonummer
 *
 * (Fachwert)
 */
public class Kontonummer implements Serializable
{
   private int _value;
   
   private static final long serialVersionUID = 603348283203590511L;
   
   /**
    * Konstruktor
    * 
    * nutze die value()-Methoden, um Exemplare der Klasse Kontonummer zu erhalten
    */
   protected Kontonummer(int value)
   {
	   _value = value;
   }
   
   //
   // statische Methoden
   // 
   
   /**
    * Erzeugt aus einem gegebenen Integer eine Kontonummer
    * @require isValid(intValue)
    */
   public static Kontonummer valueOf(int intValue)
   {
      assert isValid(intValue) : "Precondition: isValid(intValue)";
      return new Kontonummer(intValue);
   }
   
   /**
    * Erzeugt aus einem gegebenen String eine Kontonummer
    * @require isValid(stringValue)
    */
   public static Kontonummer valueOf(String stringValue)
   {
      assert isValid(stringValue) : "Precondition: isValid(stringValue)";
      int intValue = Integer.parseInt(stringValue);
      return valueOf(intValue);
   }
   
   /**
    * Prüft, ob ein gegebener Integer eine gültige Kontonummer repräsentiert
    */
   public static boolean isValid(int intValue)
   {  
      return intValue > 1000;
   }
   
   /**
    * Prüft, ob ein gegebener String eine gültige Kontonummer repräsentiert
    */
   public static boolean isValid(String value)
   {
      int intValue;

      try {
         intValue = Integer.parseInt(value);
      } catch (NumberFormatException e)
      {
         return false;
      }
      
      return isValid(intValue);
   }
   
   /**
    * Gibt die Integer-Repräsentation zu einer VerwaltungsNummer
    */
   public int value()
   {
      return _value;
   }

   public String toString()
   {
      return Integer.toString(_value);
   }
   
   public static Kontonummer defaultValue()
   {
	   return Kontonummer.valueOf("1001");
   }
   
	public boolean equals(Object obj) {
		Kontonummer nr = (Kontonummer) obj;
		return _value == nr._value;
	}
	
	public int hashCode() {
		return _value;
	}
}
