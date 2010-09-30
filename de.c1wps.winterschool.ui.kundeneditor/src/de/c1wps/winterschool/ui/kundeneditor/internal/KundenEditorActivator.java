/*******************************************************************************
 * Copyright (c) 2010 C1WPS GmbH. All rights reserved.
 *******************************************************************************/
package de.c1wps.winterschool.ui.kundeneditor.internal;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import de.c1wps.winterschool.domain.common.GenericServiceTracker;
import de.c1wps.winterschool.domain.common.IGenericServiceListener;
import de.c1wps.winterschool.domain.kunde.listener.IKundeChangedListener;
import de.c1wps.winterschool.domain.kunde.service.IKundenService;

/**
 * The activator class controls the plug-in life cycle
 */
public class KundenEditorActivator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "de.c1wps.winterschool.ui.KundenEditor";

	// The shared instance
	private static KundenEditorActivator plugin;

	private GenericServiceTracker<IKundenService> kundenServiceTracker;

	private BundleContext bundleContext;

	/**
	 * The constructor
	 */
	public KundenEditorActivator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		this.bundleContext = context;
		super.start(context);
		plugin = this;

		kundenServiceTracker = new GenericServiceTracker<IKundenService>(
				context, IKundenService.class);
		kundenServiceTracker.open();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		kundenServiceTracker.close();

		super.stop(context);
	}

	public void registerKundenServiceListener(
			IGenericServiceListener<IKundenService> listener) {
		kundenServiceTracker.addServiceListener(listener);
	}

	public void registerKundeChangedListener(IKundeChangedListener listener) {
		bundleContext.registerService(IKundeChangedListener.class.getName(),
				listener, null);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static KundenEditorActivator getDefault() {
		return plugin;
	}

}
