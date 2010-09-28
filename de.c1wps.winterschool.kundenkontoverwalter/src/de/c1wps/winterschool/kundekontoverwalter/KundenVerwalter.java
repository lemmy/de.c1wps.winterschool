package de.c1wps.winterschool.kundekontoverwalter;

import org.eclipse.osgi.framework.console.CommandInterpreter;
import org.eclipse.osgi.framework.console.CommandProvider;

import de.c1wps.winterschool.domain.kunde.material.Adresse;
import de.c1wps.winterschool.domain.kunde.material.Kunde;
import de.c1wps.winterschool.domain.kunde.service.IKundenService;

public class KundenVerwalter implements CommandProvider {
	private IKundenService _kundenService;
	
	public KundenVerwalter() {
	}

	public KundenVerwalter(IKundenService kundenService) {
		_kundenService = kundenService;
		
	}

	public void _erstelleKunde(CommandInterpreter ci) {
		if (getKundenService() != null) {
			Adresse adresse = new Adresse("Sonnenallee", "10a", 22760,
					"Hamburg");
			Kunde kunde = _kundenService.erzeugeKunde("Susi", "Sonnenschein",
					adresse);
			System.out.println("Kundenverwalter erzeugeKunde: "
					+ kunde.toString());
		} else {
			System.err.println("Kundenservice ist nicht verfügbar");
			// do something usefull here
		}
	}

	private IKundenService getKundenService() {
		return _kundenService;
	}

	/**
	 * dynamische Services mit binde-Methoden. Die Übergabe einer
	 * Service-Referenz im Konstruktor sollte entfernt werden und stattdessen
	 * diese Methoden verwendet werden.
	 * 
	 * @param kundenService
	 */
	public void bindKundenService(IKundenService kundenService) {
		System.out.println("KundenVerwalter.bindKundenService()");
		_kundenService = kundenService;
	}

	public void unbindKundenService(IKundenService kundenService) {
		System.out.println("KundenVerwalter.unbindKundenService()");
		_kundenService = null;
	}

	public String getHelp() {
		StringBuilder help = new StringBuilder();
		help.append("\n--- Kundenverwalter ---");
		help.append("\n erstelleKunde - erzeugt einen neuen Kunden");
		help.append("\n\n");

		return help.toString();
	}
}
