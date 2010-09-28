package de.c1wps.winterschool.ui.kundeneditor;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import de.c1wps.winterschool.domain.kunde.material.Kunde;

public class OpenKundenEditorAction implements IActionDelegate {

	private Kunde currentKunde;

	public void run(IAction action) {
		if (currentKunde != null) {
			IWorkbenchPage page = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage();
			KundenEditorInput input = new KundenEditorInput(currentKunde);
			try {
				page.openEditor(input, KundenEditor.ID);
			} catch (PartInitException e) {
				MessageDialog.openError(new Shell(), "Error",
						"Could not open editor");
			}
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		IStructuredSelection sel = (IStructuredSelection) selection;
		Object element = sel.getFirstElement();
		if (element instanceof Kunde) {
			currentKunde = (Kunde) element;
		}
	}

}
