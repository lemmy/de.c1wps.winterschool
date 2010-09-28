package de.c1wps.winterschool.ui.kundenlister.internal;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import de.c1wps.winterschool.domain.common.GenericServiceTracker;
import de.c1wps.winterschool.domain.common.IGenericServiceListener;
import de.c1wps.winterschool.domain.kunde.listener.IKundeChangedListener;
import de.c1wps.winterschool.domain.kunde.service.IKundenService;

/**
 * The activator class controls the plug-in life cycle
 */
public class KundenListerActivator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "KundenLister";

	// The shared instance
	private static KundenListerActivator plugin;

	private GenericServiceTracker<IKundenService> kundenServiceTracker;

	private BundleContext context;

	/**
	 * The constructor
	 */
	public KundenListerActivator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		this.context = context;

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

	public void registerKundeChangedListener(
			IKundeChangedListener kundenServiceListener) {
		context.registerService(IKundeChangedListener.class.getName(),
				kundenServiceListener, null);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static KundenListerActivator getDefault() {
		return plugin;
	}

}
