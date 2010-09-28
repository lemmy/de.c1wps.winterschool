package de.c1wps.winterschool.domain.common;

public interface IGenericServiceListener<S> {

	public void bindService(S service);
	
	public void unbindService(S service);
	
}
