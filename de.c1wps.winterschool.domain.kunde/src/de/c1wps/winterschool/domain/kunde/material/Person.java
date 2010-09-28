package de.c1wps.winterschool.domain.kunde.material;

public class Person extends BasisMaterial {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7464000389998671066L;

	/**
	 * Konstruktor
	 * 
	 * @param vorname
	 *            - der Vorname der Person
	 * @param nachname
	 *            - der Nachname der Person
	 * @param adresse
	 *            - die Adresse der Person
	 */
	public Person(String vorname, String nachname, Adresse adresse) {
		assert vorname != null : "Vorbedingung  vorname != null";
		assert nachname != null : "Vorbedingung  nachname != null";
		assert adresse != null : "Vorbedingung  adresse != null";

		_vorname = vorname;
		_nachname = nachname;
		_adresse = adresse;
	}

	/**
	 * Gibt die Adresse der Person zur�ck.
	 * 
	 * @return Die aktuelle Adresse der Person.
	 */
	public Adresse getAdresse() {
		return _adresse;
	}

	/**
	 * Setzt die Adresse der Person.
	 * 
	 * @param adresse
	 *            - die Adresse der Person.
	 */
	public void setAdresse(Adresse adresse) {
		_adresse = adresse;
	}

	/**
	 * Gibt den Nachnamen der Person zur�ck.
	 * 
	 * @return Der Nachname der Person.
	 */
	public String getNachname() {
		return _nachname;
	}

	/**
	 * Setzt den Nachnamen der Person.
	 * 
	 * @param nachname
	 *            - der Nachname der Person.
	 */
	public void setNachname(String nachname) {
		_nachname = nachname;
	}

	/**
	 * Gibt den Vornamen der Person zur�ck.
	 * 
	 * @return Der Vorname der Person.
	 */
	public String getVorname() {
		return _vorname;
	}

	public void setVorname(String vorname) {
		_vorname = vorname;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((_adresse == null) ? 0 : _adresse.hashCode());
		result = prime * result
				+ ((_nachname == null) ? 0 : _nachname.hashCode());
		result = prime * result
				+ ((_vorname == null) ? 0 : _vorname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj != null && (obj instanceof Person)) {
			Person otherPerson = (Person) obj;

			if (otherPerson.getVorname().equals(this.getVorname())
					&& otherPerson.getNachname().equals(this.getNachname())
					&& otherPerson.getAdresse().equals(this.getAdresse())) {
				result = true;
			}
		}

		return result;
	}

	protected String _vorname;
	protected String _nachname;
	protected Adresse _adresse;
}
