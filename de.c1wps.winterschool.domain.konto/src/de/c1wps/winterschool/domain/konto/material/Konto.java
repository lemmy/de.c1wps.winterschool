/*******************************************************************************
 * Copyright (c) 2010 C1WPS GmbH. All rights reserved.
 *******************************************************************************/
package de.c1wps.winterschool.domain.konto.material;

import java.util.Collection;
import java.util.LinkedList;

import de.c1wps.winterschool.domain.konto.fachwerte.Betrag;
import de.c1wps.winterschool.domain.konto.fachwerte.Kontonummer;
import de.c1wps.winterschool.domain.konto.fachwerte.Saldo;
import de.c1wps.winterschool.domain.konto.fachwerte.Zinssatz;
import de.c1wps.winterschool.domain.kunde.fachwerte.Kundennummer;

public class Konto extends BasisMaterial
{

	
   /**
    * serialUID
    */
   private static final long serialVersionUID = -3490145888754083862L;

   /**
    * Exemplarvariable zur Verwaltung der Kontobewegungen.
    */
   private Collection<KontoBewegung> _bewegungen;

   /**
    * Exemplarvariable zum speichern des Dispositionslimits.
    */
   private Betrag _dispo;

   /**
    * Exemplarvariable f�r die Kontonummer.
    */
   private Kontonummer _kontonummer;
   

   /**
    * Exemplarvariable zum speichern des aktuellen Kontosaldos.
    */
   private Saldo _saldo;

   /**
    * Exemplarvariable zum speichern des Zinssatzobjektes.
    */
   private Zinssatz _sollzins;

   private Kundennummer _kundennummer;

   /**
    * Konstante Zusatzzins f�r �berziehung des Dispolimits.
    */
   final private Zinssatz DISPO_ZINS = Zinssatz.valueOf(0.02f);

   /**
    * Konstante f�r die Geb�hren.
    */
   final private Betrag KONTOFUEHRUNGSGEBUEHREN = Betrag.valueOf(200);


   /**
    * Konstruktor mit dem Kunden als Parameter
    * 
    * @param kunde
    */
   public Konto(Kundennummer kundennummer, Kontonummer kontonummer)
   {
	   this(kundennummer, 
			kontonummer, 
			Saldo.valueOf("0", true),
			Zinssatz.valueOf(0.360f),
			Betrag.valueOf("500,00"));
   }

   public Konto(Kundennummer kundennummer, Kontonummer kontonummer, Saldo saldo, Zinssatz sollzins, Betrag dispo) {
		this(kundennummer, kontonummer, saldo, sollzins, dispo, 1, new LinkedList<KontoBewegung>());
	}
   
   public Konto(Kundennummer kundennummer, Kontonummer kontonummer, Saldo saldo, Zinssatz sollzins, Betrag dispo, int version) {
		this(kundennummer, kontonummer, saldo, sollzins, dispo, version, new LinkedList<KontoBewegung>());
	}

   public Konto(Kundennummer kundennummer, Kontonummer kontonummer, Saldo saldo, Zinssatz sollzins, Betrag dispo, int version, Collection<KontoBewegung> bewegungen) {
	super(version);
	_kundennummer = kundennummer;
	_kontonummer = kontonummer;
	_saldo = saldo;
	_sollzins = sollzins;
	_dispo = dispo;
	_bewegungen = bewegungen;
}

   /**
    * Gibt die Anzahl der Kontobewegungen zur�ck.
    * 
    * @return Anzahl der Kontobewegungen
    * 
    */
   public int anzahlKontoBewegungen()
   {
      return _bewegungen.size();
   }

   /**
    * Zahlt den angegebenen Betrag vom Konto aus, sofern das Konto gedeckt ist
    * 
    * @param betrag Der auszuzahlende Betrag.
    * 
    * @vorbedingung betrag ist gr��er oder gleich "0"
    * @vorbedingung betrag muss gedeckt sein
    */
   public void auszahlen(Betrag betrag)
   {
      assert betrag != null : "Vorbedingung: betrag != null ";
      assert istBetragGedeckt(betrag) : "Precondition: betrag ist gedeckt";
      _saldo = _saldo.substrahiere(betrag);

      _bewegungen.add(new KontoBewegung(Saldo.valueOf(_saldo.getLongValue()), betrag, "Auszahlung", false));
   }

   /**
    * Zahlt den angegebenen Betrag auf dem Konto ein.
    * 
    * @param betrag Der einzuzahlende Betrag.
    * 
    * @vorbedingung betrag ist gr��er oder gleich "0"
    * @nachbedingung alter Saldo + betrag = neuer saldo
    */
   public void einzahlen(Betrag betrag)
   {
      assert betrag != null : "Vorbedingung: betrag != null ";
      _saldo = _saldo.addiere(betrag);

      _bewegungen.add(new KontoBewegung(Saldo.valueOf(_saldo.getLongValue()), betrag, "Einzahlung", true));
   }

   /**
    * Gibt den Dispositionskredit des Kontos als positiven Wert zur�ck.
    * 
    * @return Der aktuelle Dispokredit des Kontos.
    */
   public Betrag getDispo()
   {
      return _dispo;
   }

   /**
    * Gibt die Kontobewegungen als Array zur�ck.
    * 
    * @return Array der Kontobewegungen
    * 
    */
   public KontoBewegung[] getKontoBewegungen()
   {
      return (KontoBewegung[]) _bewegungen.toArray(new KontoBewegung[0]);
   }

   /**
    * Gibt die Kontonummer des Kontos zur�ck.
    * 
    * @return Die Kontonummer.
    */
   public Kontonummer getKontonummer()
   {
      return _kontonummer;
   }
   
   
   /**
    * Gibt die Kundennummer des kontoinhabenden Kunden zur�ck.
    * 
    * @return Die Kundenummer.
    */
   public Kundennummer getKundennummer() {
	return _kundennummer;
   }

   /**
    * Gibt den Kontostand zur�ck.
    * 
    * @return Das aktuelle Saldo des Kontos.
    */
   public Saldo getSaldo()
   {
      return _saldo;
   }
   
   /**
    * Berechnet den Saldo, wobei der Dispositionskredit gutgeschrieben ist.
    * 
    * @vorbedingung Girokonto ist im Soll
    * @return das Saldo, dem der Dispositionskredit addiert wurde.
    */
   private Saldo getSaldoMitDispo() 
   {
	   return getSaldo().addiere(getDispo());
	}

   /**
	 * Gibt den Sollzins f�r �berziehungszinsen zur�ck.
	 * 
	 * @return Der Sollzins.
	 * 
	 * @nachbedingung ergebnis ist nicht null
	 */
   public Zinssatz getSollzins()
   {
      assert _sollzins != null : "Nachbedingung: Ergebnis ist nicht null";
      return _sollzins;
   }

   /**
    * Ermittelt, ob der angegebene Betrag gedeckt ist und damit eine Auszahlung m�glich.
    * 
    * @param betrag - Der zu �berpr�fende Betrag.
    * @return Ist der Betrag gedeckt?
    * 
    */
   public boolean istBetragGedeckt(Betrag betrag)
   {
      return getSaldoMitDispo().substrahiere(betrag).istPositiv();
   }

   /**
    * @return Ist das Konto im Soll?
    */
   public boolean istImSoll()
   {
      return !getSaldo().istPositiv();
   }

   /**
    * Ziht die Kontof�hrungsgeb�hren vom Konto ab.
    */
   public void macheJahresabschluss()
   {
      _saldo = getSaldo().substrahiere(KONTOFUEHRUNGSGEBUEHREN);
      
      _bewegungen.add(new KontoBewegung(Saldo.valueOf(_saldo.getLongValue()), KONTOFUEHRUNGSGEBUEHREN, "Kontf�hrungsgeb�hr", false));
   }

   /**
    * Setzt den Betrag des Dispositionskredites des Kontos. Der Dispo hat jetzt noch keine
    * Wirkung.
    * 
    * @param dispo - der Betrag des Dispositionskredites.
    * 
    */
   public void setDispo(Betrag dispo)
   {
      _dispo = dispo;
   }

   /**
    * Gibt eine Beschreibung des Kontos zur�ck.
    * 
    * @return Ein String mit der Beschreibung.
    */
   public String toString()
   {
      // muss ich nicht explizit pr�fen, geht aus der Implementierung unmittelbar hervor
      return "Girokonto (" + getKontonummer() + ") mit Saldo " + getSaldo() + " und Dispo " + getDispo();
   }
   
   /**
    * Dem Konto werden die Tageszinsen berechnet, wenn es im Soll ist. Wenn es �ber den Dispo hinaus
    * �berzogen wurde, werden Extrazinsen erhoben.
    * 
    * @vorbedingung Girokonto ist im Soll
    * @nachbedingung saldo ist mindestens um den Sollzins erniedrigt
    */
   public void verrechneZinsen()
   {
      assert istImSoll() : "Girokonto ist im Soll";

      Zinssatz lokalerZinssatz = getSollzins();
      if (!getSaldoMitDispo().istPositiv())
      {
         lokalerZinssatz = lokalerZinssatz.addiere(DISPO_ZINS);
      }

      Betrag zinsBetrag = getSaldo().zinsen(Zinssatz.valueOf(lokalerZinssatz.value() / 360));
      _saldo = _saldo.substrahiere(zinsBetrag);
      _bewegungen.add(new KontoBewegung(Saldo.valueOf(_saldo.getLongValue()), zinsBetrag , "Zinsen", false));
   }

}
