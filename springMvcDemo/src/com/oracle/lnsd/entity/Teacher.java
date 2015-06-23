package com.oracle.lnsd.entity;

import java.io.Serializable;

public class Teacher implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8163001206337931413L;

	public Teacher() {}

	private String name;
	private Address address;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Teacher [name=" + name + ", address=" + address + "]";
	}
}
