package com.itwillbs.domain;

public class SampleVO {
	
	private String name;
	private String tel;
	private int age;
	
	// getter, setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	@Override
	public String toString() {
		return "SampleVO [name=" + name + ", tel=" + tel + ", age=" + age + "]";
	}
	
	
}
