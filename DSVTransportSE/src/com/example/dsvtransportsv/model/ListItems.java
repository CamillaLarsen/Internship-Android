package com.example.dsvtransportsv.model;


/**
 * 
 * @author Camilla
 * 
 * Modelclass for the listitems
 * with truckdriversname and registrationnumber.
 * 
 */
public class ListItems {

	String registationNo;
	String truckdriverName;

	public ListItems(String registationNo, String truckdriverName) {
		this.registationNo = registationNo;
		this.truckdriverName = truckdriverName;

	}

	public String getRegistationNo() {
		return registationNo;
	}

	public String getTruckdriverName() {
		return truckdriverName;
	}

	public void setRegistationNo(String registationNo) {
		this.registationNo = registationNo;
	}

	public void setTruckdriverName(String truckdriverName) {
		this.truckdriverName = truckdriverName;
	}

	@Override
	public String toString() {
		return registationNo + truckdriverName;
	}

}
