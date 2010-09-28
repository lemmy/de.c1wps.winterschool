package de.c1wps.winterschool.domain.kunde.service;

import de.c1wps.winterschool.domain.kunde.fachwerte.Kundennummer;
import de.c1wps.winterschool.domain.kunde.material.Adresse;
import de.c1wps.winterschool.domain.kunde.material.Kunde;

public interface IKundenService {

	Kunde[] listKunden();

	boolean existiertKunde(Kundennummer kundennummer);

	Kunde erzeugeKunde(String vorname, String nachname, Adresse adresse);

	boolean aktualisiereKunde(Kunde kunde);

	Kunde getKunde(Kundennummer kundennummer);

	Kundennummer naechsteKundennummer();

	void clear();
}