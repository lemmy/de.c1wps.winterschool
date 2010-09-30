/*******************************************************************************
 * Copyright (c) 2010 C1WPS GmbH. All rights reserved.
 *******************************************************************************/
package de.c1wps.winterschool.ui.kundenlister;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.ServiceException;

import de.c1wps.winterschool.domain.common.IGenericServiceListener;
import de.c1wps.winterschool.domain.kunde.fachwerte.Kundennummer;
import de.c1wps.winterschool.domain.kunde.listener.IKundeChangedListener;
import de.c1wps.winterschool.domain.kunde.material.Kunde;
import de.c1wps.winterschool.domain.kunde.service.IKundenService;
import de.c1wps.winterschool.ui.kundenlister.internal.KundenListerActivator;

public class KundenListerView extends ViewPart implements
		IGenericServiceListener<IKundenService> {

	private TableViewer viewer;
	private IKundenService service;
	private IKundeChangedListener kundeChangedListener;

	public KundenListerView() {
	}

	@Override
	public void createPartControl(Composite parent) {
		viewer = new TableViewer(parent, SWT.SINGLE | SWT.V_SCROLL
				| SWT.H_SCROLL | SWT.FULL_SELECTION);
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new KundenLabelProvider());
		viewer.setInput(new Kunde[0]);

		getSite().setSelectionProvider(viewer);

		hookContextMenu();

		KundenListerActivator.getDefault().registerKundenServiceListener(this);
		kundeChangedListener = createListener();
		KundenListerActivator.getDefault().registerKundeChangedListener(
				kundeChangedListener);
	}

	private IKundeChangedListener createListener() {
		IKundeChangedListener result = new IKundeChangedListener() {
			public void kundeGeandert(Kundennummer kundenNummer) {
				viewer.getTable().getDisplay().asyncExec(new Runnable() {
					public void run() {
						refreshInput();
					}
				});
			}
		};
		return result;
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				// Other plug-ins can contribute there actions here
				manager.add(new Separator(
						IWorkbenchActionConstants.MB_ADDITIONS));
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void refreshInput() {
		if (service != null) {
			try {
				final Kunde[] kunden = service.listKunden();
				viewer.getTable().getDisplay().asyncExec(new Runnable() {
					public void run() {
						viewer.setInput(kunden);
					}
				});				
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	public void bindService(IKundenService service) {
		this.service = service;
		refreshInput();
	}

	public class ViewContentProvider implements IStructuredContentProvider {
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}

		public void dispose() {
		}

		public Object[] getElements(Object input) {
			if (input instanceof Kunde[]) {
				return (Kunde[]) input;
			} else {
				return new String[] { "Fehler" };
			}
		}
	}

	public class ViewLabelProvider extends LabelProvider implements
			ITableLabelProvider {
		public String getColumnText(Object obj, int index) {
			return getText(obj);
		}

		public Image getColumnImage(Object obj, int index) {
			return getImage(obj);
		}

		public Image getImage(Object obj) {
			return PlatformUI.getWorkbench().getSharedImages()
					.getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}
	}

	public void unbindService(IKundenService service) {
		this.service = null;
	}

}
