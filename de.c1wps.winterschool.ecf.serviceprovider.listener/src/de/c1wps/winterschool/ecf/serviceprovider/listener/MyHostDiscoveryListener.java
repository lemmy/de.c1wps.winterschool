package de.c1wps.winterschool.ecf.serviceprovider.listener;

import org.eclipse.ecf.discovery.IServiceInfo;
import org.eclipse.ecf.osgi.services.discovery.IHostDiscoveryListener;
import org.osgi.framework.ServiceReference;

@SuppressWarnings("restriction")
public class MyHostDiscoveryListener implements IHostDiscoveryListener {

	/* (non-Javadoc)
	 * @see org.eclipse.ecf.osgi.services.discovery.IHostDiscoveryListener#publish(org.osgi.framework.ServiceReference, org.eclipse.ecf.discovery.IServiceInfo)
	 */
	public void publish(ServiceReference arg0, IServiceInfo arg1) {
		System.out.println("publish");
		System.out.println(arg0);
		System.out.println(arg1);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ecf.osgi.services.discovery.IHostDiscoveryListener#unpublish(org.osgi.framework.ServiceReference, org.eclipse.ecf.discovery.IServiceInfo)
	 */
	public void unpublish(ServiceReference arg0, IServiceInfo arg1) {
		System.out.println("unpublish");
		System.out.println(arg0);
		System.out.println(arg1);
	}
}
