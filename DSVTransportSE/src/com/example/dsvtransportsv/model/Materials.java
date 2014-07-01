package com.example.dsvtransportsv.model;

/**
 * 
 * @author Camilla
 * 
 * Modelclass for the listitems in the dialog
 * with materialname and materialnumber.
 *
 */

public class Materials {
	public String materialName;
	public String materialNo;

	public Materials(String materialName, String materialNo) {
		this.materialName = materialName;
		this.materialNo = materialNo;

	}

	public String getMaterialNo() {
		return materialNo;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialNo(String materialNo) {
		this.materialNo = materialNo;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	@Override
	public String toString() {
		return materialName +  materialNo;
	}

}
