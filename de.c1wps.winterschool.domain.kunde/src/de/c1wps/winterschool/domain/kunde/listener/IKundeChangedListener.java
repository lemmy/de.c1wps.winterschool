package de.c1wps.winterschool.domain.kunde.listener;

import de.c1wps.winterschool.domain.kunde.fachwerte.Kundennummer;

public interface IKundeChangedListener {

	void kundeGeandert(Kundennummer kundeNummer);
}
