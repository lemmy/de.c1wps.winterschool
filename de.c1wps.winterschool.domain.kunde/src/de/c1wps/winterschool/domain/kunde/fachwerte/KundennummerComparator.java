package de.c1wps.winterschool.domain.kunde.fachwerte;

import java.util.Comparator;

public class KundennummerComparator implements Comparator<Kundennummer> {
	public int compare(Kundennummer kundennummer1, Kundennummer kundennummer2) {
		return kundennummer1.value() - kundennummer2.value();
	}
}