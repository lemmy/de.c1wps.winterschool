package de.c1wps.winterschool.domain.kunde.material;

public class BasisMaterial implements java.io.Serializable{

	private static final long serialVersionUID = -1306175460546170879L;


	public BasisMaterial() {
	      _version = 1;
	}

	public BasisMaterial(int version) {
	      _version = version;
	}
	
	public int getVersion() {
		return _version;
	}
	
	public void incrementVersion() {
		_version++;
	}

	
	private int _version;
}
