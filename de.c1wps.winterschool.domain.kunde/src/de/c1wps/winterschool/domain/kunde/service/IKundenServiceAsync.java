package de.c1wps.winterschool.domain.kunde.service;

import org.eclipse.ecf.remoteservice.IAsyncCallback;
import org.eclipse.ecf.remoteservice.IAsyncRemoteServiceProxy;
import org.eclipse.equinox.concurrent.future.IFuture;

import de.c1wps.winterschool.domain.kunde.material.Kunde;

@SuppressWarnings("restriction")
public interface IKundenServiceAsync extends IAsyncRemoteServiceProxy {

	void listKunden(IAsyncCallback<Kunde[]> callback);

	IFuture listKundenAsync();
}