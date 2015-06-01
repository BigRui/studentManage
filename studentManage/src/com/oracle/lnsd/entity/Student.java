package com.oracle.lnsd.entity;

import java.io.Serializable;

public class Student implements Serializable {
	/**
	 * 
	 */
    private static final long serialVersionUID = 5846544187959830272L;
    
    private String name;
    private int age;
    private String email;
    
    
	public Student(String name, int age, String email) {
	    super();
	    this.name = name;
	    this.age = age;
	    this.email = email;
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    
}
