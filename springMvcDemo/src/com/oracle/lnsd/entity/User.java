package com.oracle.lnsd.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6625341115106189402L;

	public User() {}
	@Pattern(message="用户名必须是字母数字下划线，5到10位", regexp="\\w{5,10}")
	private String name;
	@Past(message="生日必须是过去时")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date birth;
	@NumberFormat(pattern="#,##0.00")
	@Min(message="那点破工资", value=1000)
	private Double salary;
	private Teacher teacher;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", birth=" + birth + ", salary=" + salary
				+ ", teacher=" + teacher + "]";
	}
}
