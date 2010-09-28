package de.c1wps.winterschool.domain.konto.material;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.c1wps.winterschool.domain.konto.fachwerte.Betrag;
import de.c1wps.winterschool.domain.konto.fachwerte.Saldo;

public class KontoBewegung implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6963726786227678650L;
	private Saldo _neuesSaldo;
	private String _kommentar;
	private Betrag _bewegung;
	private Date _datum;
	private boolean _positiv; // false == negative bewegung
	
	
	public KontoBewegung(Saldo saldo, 
			Betrag bewegung, String kommentar, boolean positiv, Date datum) 
	{
		super();
		_neuesSaldo = saldo;
		_kommentar = kommentar;
		_bewegung = bewegung;
		_positiv = positiv;
		_datum = datum;
	}
	
	public KontoBewegung(Saldo saldo, 
			Betrag bewegung, String kommentar, boolean positiv) {
		this(saldo, bewegung, kommentar, positiv,new Date());
	}
	
	public String getKommentar() {
		return _kommentar;
	}
	public void setKommentar(String kommentar) {
		this._kommentar = kommentar;
	}
	public Saldo getNeuesSaldo() {
		return _neuesSaldo;
	}
	public void setNeuesSaldo(Saldo saldo) {
		_neuesSaldo = saldo;
	}
	public Betrag getBewegung() {
		return _bewegung;
	}
	public void setBewegung(Betrag bewegung) {
		_bewegung = bewegung;
	}
	public boolean isPositiv() {
		return _positiv;
	}
	public void setPositiv(boolean positiv) {
		_positiv = positiv;
	}
	
	public Date getDatum() {
		return _datum;
	}

	public String toString() {
		DateFormat format = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
	     return (_positiv?"->":"<-") + " " + format.format(_datum) + " | Neuer Saldo: "  + _neuesSaldo + " Bewegung: " + _bewegung + " " + _kommentar ;
	}
	
}
