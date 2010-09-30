/*******************************************************************************
 * Copyright (c) 2010 C1WPS GmbH. All rights reserved.
 *******************************************************************************/
package de.c1wps.winterschool.kundekontoverwalter;

import org.eclipse.osgi.framework.console.CommandInterpreter;
import org.eclipse.osgi.framework.console.CommandProvider;

import de.c1wps.winterschool.domain.konto.fachwerte.Betrag;
import de.c1wps.winterschool.domain.konto.fachwerte.Saldo;
import de.c1wps.winterschool.domain.konto.fachwerte.Zinssatz;
import de.c1wps.winterschool.domain.konto.material.Konto;
import de.c1wps.winterschool.domain.konto.service.IKontoService;
import de.c1wps.winterschool.domain.kunde.fachwerte.Kundennummer;

public class KontoVerwalter implements CommandProvider{

	private IKontoService _kontoService;

	public KontoVerwalter() {
	}
	
	public KontoVerwalter(IKontoService kontoService) {
		_kontoService = kontoService;
	}

	public void _erstelleKonto(CommandInterpreter ci) {
		if (getKontoService() != null) {
			Konto konto = _kontoService.erzeugeKonto(
					Kundennummer.valueOf(1234), Saldo.valueOf(100), Zinssatz
							.valueOf(5), Betrag.valueOf(500));
			System.out.println("Kontoverwalter erzeugeKonto: "
					+ konto.toString());
		} else {
			System.err.println("Kontoservice ist nicht verfügbar");
			// do something usefull here
		}
	}

	private IKontoService getKontoService() {
		return _kontoService;
	}

	/**
	 * 2 Aufgabe: dynamische Services mit binde-Methoden. Die Übergabe einer
	 * Service-Referenz im Konstruktor sollte entfernt werden und stattdessen
	 * diese Methoden verwendet werden.
	 * 
	 * @param kontoService
	 */
	public void bindKontoService(IKontoService kontoService) {
		System.out.println("KontoVerwalter.bindKontoService()");
		_kontoService = kontoService;
	}

	public void unbindKontoService(IKontoService kontoService) {
		System.out.println("KontoVerwalter.unbindKontoService()");
		_kontoService = null;
	}

	public String getHelp() {
		StringBuilder help = new StringBuilder();
		help.append("\n--- Kontoverwalter ---");
		help.append("\n erstelleKonto - erzeugt ein neues Konto");
		help.append("\n");

		return help.toString();
	}

}
