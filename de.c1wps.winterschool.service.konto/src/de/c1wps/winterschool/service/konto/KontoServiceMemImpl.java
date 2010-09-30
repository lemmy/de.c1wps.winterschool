/*******************************************************************************
 * Copyright (c) 2010 C1WPS GmbH. All rights reserved.
 *******************************************************************************/
package de.c1wps.winterschool.service.konto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import de.c1wps.winterschool.domain.konto.fachwerte.Betrag;
import de.c1wps.winterschool.domain.konto.fachwerte.Kontonummer;
import de.c1wps.winterschool.domain.konto.fachwerte.Saldo;
import de.c1wps.winterschool.domain.konto.fachwerte.Zinssatz;
import de.c1wps.winterschool.domain.konto.listener.IKontoChangeListener;
import de.c1wps.winterschool.domain.konto.material.Konto;
import de.c1wps.winterschool.domain.konto.material.KontoBewegung;
import de.c1wps.winterschool.domain.konto.material.KontonummerComparator;
import de.c1wps.winterschool.domain.konto.service.IKontoService;
import de.c1wps.winterschool.domain.kunde.fachwerte.Kundennummer;

public class KontoServiceMemImpl implements IKontoService {

	private Map<Kontonummer, Konto> _kontenMap;
	private static int _letzteKontonummer = 1001;
	private Set<IKontoChangeListener> _kontoChangeListener;

	public KontoServiceMemImpl() {
		_kontenMap = new TreeMap<Kontonummer, Konto>(
				new KontonummerComparator());

		_kontoChangeListener = Collections
				.synchronizedSet(new HashSet<IKontoChangeListener>());
	}

	// -------------------------------------------------
	/**
	 * Comment for Method containsKonto()
	 * 
	 * @param kontonummer
	 * @return
	 * 
	 * @see de.c1wps.beispiel.service.IKontoService#containsKonto(java.lang.Integer)
	 */
	public synchronized boolean existiertKonto(Kontonummer kontonummer) {
		return _kontenMap.containsKey(kontonummer);
	}

	public synchronized void clear() {
		_kontenMap.clear();
		_letzteKontonummer = 1001;
	}

	public synchronized Konto getKonto(Kontonummer kontonummer) {
		return dupliziereKonto(_kontenMap.get(kontonummer));
	}

	public synchronized Konto[] listeKontenAuf() {
		ArrayList<Konto> konten = new ArrayList<Konto>();
		for (Iterator<Konto> iter = _kontenMap.values().iterator(); iter
				.hasNext();) {
			Konto konto = (Konto) iter.next();
			konten.add(dupliziereKonto(konto));
		}
		return konten.toArray(new Konto[0]);
	}

	public synchronized Konto[] listeKontenAuf(Kundennummer kundennummer) {
		ArrayList<Konto> kontenDesKunden = new ArrayList<Konto>();
		for (Iterator<Konto> iter = _kontenMap.values().iterator(); iter
				.hasNext();) {
			Konto konto = (Konto) iter.next();
			if (konto.getKundennummer().equals(kundennummer))
				kontenDesKunden.add(dupliziereKonto(konto));
		}
		return kontenDesKunden.toArray(new Konto[0]);
	}

	public Kontonummer naechsteKontonummer() {
		// TODO Auto-generated method stub
		return Kontonummer.valueOf(_letzteKontonummer);
	}

	private void saveKonto(Konto konto) {
		assert konto != null : "Vorbedingung  konto != null";
		assert !existiertKonto(konto.getKontonummer()) : "Vorbedingung !existiertKonto(konto.getKontonummer())";
		_kontenMap.put(konto.getKontonummer(), konto);

		fireKontoGeaendert(konto);
	}

	public synchronized boolean aktualisiereKonto(Konto konto) {
		assert konto != null : "Vorbedingung  konto != null";
		assert existiertKonto(konto.getKontonummer()) : "Vorbedingung existiertKonto(konto.getKontonummer())";

		int version = konto.getVersion();

		Konto oldKonto = null;
		if (_kontenMap.containsKey(konto.getKontonummer())) {
			oldKonto = _kontenMap.get(konto.getKontonummer());
		}

		if (oldKonto.getVersion() <= version) {
			_kontenMap.put(konto.getKontonummer(), dupliziereKonto(konto));
			konto.incrementVersion();

			fireKontoGeaendert(konto);
			return true;
		} else
			return false;
	}

	public synchronized Konto erzeugeKonto(Kundennummer kundennummer,
			Saldo saldo, Zinssatz sollzins, Betrag dispo) {

		assert saldo != null : "Vorbedingung  saldo != null";
		assert sollzins != null : "Vorbedingung  sollzins != null";
		assert dispo != null : "Vorbedingung  dispo != null";

		Konto konto = new Konto(kundennummer,
				Kontonummer.valueOf(nextKontonummer()), saldo, sollzins, dispo);

		saveKonto(konto);
		return dupliziereKonto(konto);
	}

	public synchronized boolean ueberweisungMoeglich(Kontonummer senderNummer,
			Kontonummer empfaengerNummer, Betrag betrag) {
		if (existiertKonto(senderNummer) && existiertKonto(empfaengerNummer)) {
			Konto konto1 = getKonto(senderNummer);
			return konto1.istBetragGedeckt(betrag);
		}
		return false;
	}

	public synchronized void ueberweise(Kontonummer senderNummer,
			Kontonummer empfaengerNummer, Betrag betrag) {
		assert ueberweisungMoeglich(senderNummer, empfaengerNummer, betrag) : "Vorbedingung  ueberweisungMoeglich(sender, empfaenger, betrag)";

		Konto sender = _kontenMap.get(senderNummer);
		Konto empfaenger = _kontenMap.get(empfaengerNummer);

		sender.auszahlen(betrag);
		empfaenger.einzahlen(betrag);
	}

	private static int nextKontonummer() {
		int result = _letzteKontonummer;
		_letzteKontonummer++;
		return result;
	}

	private Konto dupliziereKonto(Konto original) {
		// kopiere Kontobewegungen
		KontoBewegung[] kontoBewegungen = original.getKontoBewegungen();
		Collection<KontoBewegung> kontoBewegungenKopie = new ArrayList<KontoBewegung>();
		for (int idx = 0; idx < kontoBewegungen.length; idx++) {
			KontoBewegung aktuelleBewegung = kontoBewegungen[idx];
			KontoBewegung kopie = new KontoBewegung(
					aktuelleBewegung.getNeuesSaldo(),
					aktuelleBewegung.getBewegung(),
					aktuelleBewegung.getKommentar(),
					aktuelleBewegung.isPositiv(), aktuelleBewegung.getDatum());
			kontoBewegungenKopie.add(kopie);
		}
		// erzeuge Kopie des Kontos
		return new Konto(original.getKundennummer(), original.getKontonummer(),
				original.getSaldo(), original.getSollzins(),
				original.getDispo(), original.getVersion(),
				kontoBewegungenKopie);
	}

	private void fireKontoGeaendert(Konto konto) {
		for (IKontoChangeListener listener : _kontoChangeListener) {
			listener.kontoGeaendert(konto);
		}
	}

	public void bindKontoChangeListener(IKontoChangeListener listener) {
		_kontoChangeListener.add(listener);
	}

	public void unbindKontoChangeListener(IKontoChangeListener listener) {
		_kontoChangeListener.remove(listener);
	}

}
