/*******************************************************************************
 * Copyright (c) 2010 C1WPS GmbH. All rights reserved.
 *******************************************************************************/
package de.c1wps.winterschool.ui.kundenlister;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import de.c1wps.winterschool.domain.kunde.material.Kunde;

public class KundenLabelProvider extends LabelProvider implements ITableLabelProvider {
	
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	public String getColumnText(Object element, int columnIndex) {
		if (element instanceof Kunde) {
			Kunde kunde = (Kunde) element;
			return kunde.getNachname() + ", " +kunde.getVorname();
		}
		return super.getText(element);
	}

}
