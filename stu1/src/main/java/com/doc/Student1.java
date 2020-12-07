package com.doc;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "student1")
public class Student1 {

  @Id
  private String id;
  private String dept;
  private int age;
  private String firstName;
  private String lastName;
  public Student1(String id,String firstName, String lastName,String dept,int age) {
	  this.id=id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dept=dept;
    this.age=age;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	  
  
  @Override
  public String toString() {
    return id+" "+firstName + " " + lastName+" "+dept+" "+age;
  }

}