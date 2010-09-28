package de.c1wps.winterschool.domain.konto.fachwerte;

import java.io.Serializable;

public class Saldo implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6842843763666307667L;

	private Betrag _betrag;

	private boolean _positiv;

	private Saldo(Betrag value, boolean positiv)
	{
		_betrag = value;
		_positiv = positiv;
	}

	public String toString()
	{
		return (_positiv ? "" : "-") + _betrag.toString();
	}

	public Saldo addiere(Betrag betrag)
	{
		long neuBetrag = 0;
		
		int vorzeichen = _positiv?1:-1;

		neuBetrag = vorzeichen * _betrag.value() + betrag.value(); 
		int neuesVorzeichen = neuBetrag >=0?1:-1;			
		
		return new Saldo(Betrag.valueOf(neuesVorzeichen * neuBetrag), neuesVorzeichen == 1);
	}

	public Saldo substrahiere(Betrag betrag)
	{
		long neuBetrag = 0;
		
		int vorzeichen = _positiv?1:-1;

		neuBetrag = vorzeichen * _betrag.value() - betrag.value();
		int neuesVorzeichen = neuBetrag >=0?1:-1;			
		
		return new Saldo(Betrag.valueOf(neuesVorzeichen * neuBetrag), neuesVorzeichen == 1);
	}
	
	public Betrag zinsen(Zinssatz zinssatz)
	{
		assert zinssatz != null : "Vorbedingung zinssatz != null";
		
		int vorzeichen = _positiv?1:-1;
		long neuBetrag = Math.round(vorzeichen * _betrag.value() * zinssatz.value());
		int neuesVorzeichen = neuBetrag >=0?1:-1;			
		
		return Betrag.valueOf(neuesVorzeichen * neuBetrag);
	}
	
	public static Saldo valueOf(String betrag, boolean positiv)
	{
		assert isValid(betrag, positiv) : "Precondition isValid(betrag)";
		return new Saldo(Betrag.valueOf(betrag), positiv);
	}
	
	public static Saldo valueOf(long saldo)
	{
		boolean positiv = saldo >= 0;
		long betrag = positiv ? saldo:-saldo;
		assert Betrag.isValid(betrag) : "Precondition Betrag.isValid(betrag)";
		return new Saldo(Betrag.valueOf(betrag), positiv);
	}

	public static Saldo valueOf(Betrag betrag, boolean positiv)
	{
		assert isValid(betrag, positiv) : "Precondition isValid(betrag)";
		return new Saldo(betrag, positiv);
	}

	public static boolean isValid(String betrag, boolean positiv)
	{
		return Betrag.isValid(betrag);
	}

	public static boolean isValid(Betrag betrag, boolean positiv)
	{
		return betrag != null;
	}

	public boolean istPositiv()
	{
		return _positiv;
	}

	public long getLongValue()
	{
		long value = _betrag.value();
		return _positiv? value :(-value);
	}
	
	public boolean equals(Object obj) {
		Saldo saldo = (Saldo) obj;
		return saldo._betrag.equals(_betrag) && saldo._positiv == _positiv;
	}
	
	public int hashCode() {
		return 3 * _betrag.hashCode() + (_positiv? 1:2); 
	}
}
