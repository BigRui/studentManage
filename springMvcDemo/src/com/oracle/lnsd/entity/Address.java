package com.oracle.lnsd.entity;

import java.io.Serializable;

public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6717075711269240280L;
	
	public Address() {
	}
	
	private String name;
	private String postCode;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	@Override
	public String toString() {
		return "Address [name=" + name + ", postCode=" + postCode + "]";
	}
}
