package de.c1wps.winterschool.ecf.serviceprovider.listener;

import org.eclipse.ecf.discovery.IServiceInfo;
import org.eclipse.ecf.osgi.services.discovery.AbstractDiscoveryListener;
import org.eclipse.ecf.osgi.services.discovery.IHostDiscoveryListener;
import org.osgi.framework.ServiceReference;

@SuppressWarnings("restriction")
public class MyHostDiscoveryListener extends AbstractDiscoveryListener implements IHostDiscoveryListener {

	/* (non-Javadoc)
	 * @see org.eclipse.ecf.osgi.services.discovery.IHostDiscoveryListener#publish(org.osgi.framework.ServiceReference, org.eclipse.ecf.discovery.IServiceInfo)
	 */
	public void publish(ServiceReference publicationServiceReference, IServiceInfo serviceInfo) {
		if (publicationServiceReference == null || serviceInfo == null)
			return;
		StringBuffer sb = new StringBuffer(
				"OSGi ECF service discovery: publish").append("\n"); //$NON-NLS-1$ //$NON-NLS-2$
		sb.append(createTabs(1)).append("serviceReference=") //$NON-NLS-1$
				.append(publicationServiceReference).append("\n") //$NON-NLS-1$
				.append(printServiceInfo(1, serviceInfo));
		log(publicationServiceReference, sb.toString(), null);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ecf.osgi.services.discovery.IHostDiscoveryListener#unpublish(org.osgi.framework.ServiceReference, org.eclipse.ecf.discovery.IServiceInfo)
	 */
	public void unpublish(ServiceReference publicationServiceReference, IServiceInfo serviceInfo) {
		if (publicationServiceReference == null || serviceInfo == null)
			return;
		StringBuffer sb = new StringBuffer(
				"OSGi ECF service discovery: unpublish").append("\n"); //$NON-NLS-1$ //$NON-NLS-2$
		sb.append(createTabs(1)).append("serviceReference=") //$NON-NLS-1$
				.append(publicationServiceReference).append("\n") //$NON-NLS-1$
				.append(printServiceInfo(1, serviceInfo));
		log(publicationServiceReference, sb.toString(), null);
	}
}
