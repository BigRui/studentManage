package com.oracle.lnsd.beans;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class BeansCollection {
	private String name;
	private int age;
	
	private List<BeanInterface> beanList;
	private BeanInterface[] beanArray;
	private Set<BeanInterface> beanSet;
	private Map<String, BeanInterface> beanMap;
	public List<BeanInterface> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<BeanInterface> beanList) {
		this.beanList = beanList;
	}
	public BeanInterface[] getBeanArray() {
		return beanArray;
	}
	public void setBeanArray(BeanInterface[] beanArray) {
		this.beanArray = beanArray;
	}
	public Set<BeanInterface> getBeanSet() {
		return beanSet;
	}
	public void setBeanSet(Set<BeanInterface> beanSet) {
		this.beanSet = beanSet;
	}
	public Map<String, BeanInterface> getBeanMap() {
		return beanMap;
	}
	public void setBeanMap(Map<String, BeanInterface> beanMap) {
		this.beanMap = beanMap;
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
	
	
}
