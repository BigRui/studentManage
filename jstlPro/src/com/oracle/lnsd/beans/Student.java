package com.oracle.lnsd.beans;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7409640705086537952L;

	private String name;
	private Integer age;
	private String sex;
	private Date birthday;
	private Double salary;
	
	public Student() {
	}



	public Student(String name, Integer age, String sex, Date birthday,
			Double salary) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.birthday = birthday;
		this.salary = salary;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}


	public Date getBirthday() {
		return birthday;
	}


	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}


	public Double getSalary() {
		return salary;
	}


	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	
}
