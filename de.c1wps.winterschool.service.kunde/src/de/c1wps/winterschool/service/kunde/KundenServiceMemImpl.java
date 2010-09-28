package de.c1wps.winterschool.service.kunde;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.c1wps.winterschool.domain.kunde.fachwerte.Kundennummer;
import de.c1wps.winterschool.domain.kunde.fachwerte.KundennummerComparator;
import de.c1wps.winterschool.domain.kunde.listener.IKundeChangedListener;
import de.c1wps.winterschool.domain.kunde.material.Adresse;
import de.c1wps.winterschool.domain.kunde.material.Kunde;
import de.c1wps.winterschool.domain.kunde.service.IKundenService;

public class KundenServiceMemImpl implements IKundenService {
	private Map<Kundennummer, Kunde> _kundenMap;

	private Logger _logger = Logger.getAnonymousLogger();

	private Set<IKundeChangedListener> _listener;

	public KundenServiceMemImpl() {
		_kundenMap = new TreeMap<Kundennummer, Kunde>(
				new KundennummerComparator());
		Adresse adresse = new Adresse("Vogt-Koelln-Strasse", "30", 22527,
				"Hamburg");
		Kundennummer nummer = Kundennummer.valueOf(10001);
		Kunde kunde = new Kunde(nummer, "Rainer", "Zufall", adresse);
		_kundenMap.put(nummer, kunde);

		_listener = Collections
				.synchronizedSet(new HashSet<IKundeChangedListener>());
	}

	// -------------------------------------------------
	/**
	 * Comment for Method containsKonto()
	 * 
	 * @param kundennummer
	 * @return
	 * 
	 * @see de.c1wps.beispiel.service.IKontoService#containsKonto(java.lang.Integer)
	 */
	public synchronized boolean existiertKunde(Kundennummer kundennummer) {
		return _kundenMap.containsKey(kundennummer);
	}

	public synchronized void clear() {
		_kundenMap.clear();
		_lastKundennummer = 1001;
	}

	public synchronized Kunde getKunde(Kundennummer kundennummer) {
		return (Kunde) _kundenMap.get(kundennummer);
	}

	public synchronized Kunde[] listKunden() {
		return _kundenMap.values().toArray(new Kunde[0]);
	}

	private void saveKunde(Kunde kunde) {
		assert !existiertKunde(kunde.getKundennummer()) : "Vorbedingung !containsKunde(kunde.getKundennummer())";

		_kundenMap.put(kunde.getKundennummer(), kunde);

		fireKundeChangedEvent(kunde);
	}

	public Kunde erzeugeKunde(String vorname, String nachname, Adresse adresse) {
		int nextKundennummer = nextKundennummer();
		Kunde kunde = new Kunde(Kundennummer.valueOf(nextKundennummer),
				vorname, nachname, adresse);
		saveKunde(kunde);

		_logger.log(Level.INFO, "createKunde:" + kunde);

		fireKundeChangedEvent(kunde);

		return dupliziereKunde(kunde);
	}

	public synchronized boolean aktualisiereKunde(Kunde kunde) {
		assert kunde != null : "Vorbedingung  kunde != null";
		assert existiertKunde(kunde.getKundennummer()) : "Vorbedingung  containsKunde(kunde.getKundennummer())";

		int version = kunde.getVersion();

		Kunde oldKunde = null;
		if (_kundenMap.containsKey(kunde.getKundennummer())) {
			oldKunde = _kundenMap.get(kunde.getKundennummer());
		}

		if (oldKunde.getVersion() <= version) {
			_kundenMap.put(kunde.getKundennummer(), dupliziereKunde(kunde));
			kunde.incrementVersion();

			fireKundeChangedEvent(kunde);

			return true;
		} else {
			return false;
		}
	}

	private void fireKundeChangedEvent(Kunde kunde) {
		for (IKundeChangedListener listener : _listener) {
			listener.kundeGeandert(kunde.getKundennummer());
		}

	}

	public Kundennummer naechsteKundennummer() {
		return Kundennummer.valueOf(nextKundennummer());
	}

	private Kunde dupliziereKunde(Kunde original) {
		return new Kunde(original.getKundennummer(), original.getVorname(),
				original.getNachname(), original.getAdresse());
	}

	private static int nextKundennummer() {
		_lastKundennummer = _lastKundennummer++;
		return _lastKundennummer;
	}

	private static int _lastKundennummer = 1001;

	public void bindKundeChangedListener(IKundeChangedListener listener) {
		this._listener.add(listener);
		System.out.println("KundenServiceMemImpl.bindKundeChangedListener()");
	}

	public void unbindKundeChangedListener(IKundeChangedListener listener) {
		this._listener.remove(listener);
	}
}
