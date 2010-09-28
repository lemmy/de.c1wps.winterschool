package de.c1wps.winterschool.domain.konto.service;

import de.c1wps.winterschool.domain.konto.fachwerte.Betrag;
import de.c1wps.winterschool.domain.konto.fachwerte.Kontonummer;
import de.c1wps.winterschool.domain.konto.fachwerte.Saldo;
import de.c1wps.winterschool.domain.konto.fachwerte.Zinssatz;
import de.c1wps.winterschool.domain.konto.material.Konto;
import de.c1wps.winterschool.domain.kunde.fachwerte.Kundennummer;

public interface IKontoService {
	Konto[] listeKontenAuf();

	Konto[] listeKontenAuf(Kundennummer kundennummer);

	boolean existiertKonto(Kontonummer kontonummer);

	Konto erzeugeKonto(Kundennummer kundennummer, Saldo saldo,
			Zinssatz sollzins, Betrag dispo);

	boolean aktualisiereKonto(Konto konto);

	Konto getKonto(Kontonummer kontonummer);

	Kontonummer naechsteKontonummer();

	public boolean ueberweisungMoeglich(Kontonummer senderNummer,
			Kontonummer empfaengerNummer, Betrag betrag);

	/**
	 * Erzeugt einen Buchungssatz und fuehrt diesen transaktional durch.
	 * 
	 * @param sender
	 *            - Konto, von dem der betrag abgezogen wird.
	 * @param empfaenger
	 *            - Konto, auf den der betrag gutgeschrieben wird.
	 * @param betrag
	 *            - Betrag der Ueberweisung
	 * @throws ServiceException
	 * @vorbedingung ueberweiungMoeglich(sender,empfaenger,betrag)
	 */
	public void ueberweise(Kontonummer sender, Kontonummer empfaenger,
			Betrag betrag);

	void clear();

}