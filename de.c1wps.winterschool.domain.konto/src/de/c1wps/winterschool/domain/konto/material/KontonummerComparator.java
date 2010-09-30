/*******************************************************************************
 * Copyright (c) 2010 C1WPS GmbH. All rights reserved.
 *******************************************************************************/
package de.c1wps.winterschool.domain.konto.material;

import java.util.Comparator;

import de.c1wps.winterschool.domain.konto.fachwerte.Kontonummer;

public class KontonummerComparator implements
	Comparator<Kontonummer> {
	public int compare(Kontonummer kontonummer1, Kontonummer kontonummer2) {			
		return kontonummer1.value() - kontonummer2.value();
	}
}
