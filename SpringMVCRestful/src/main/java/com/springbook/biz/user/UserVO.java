package com.springbook.biz.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USERS2")
public class UserVO {
  @Id
  private String userId;
  private String name;
  private String gender;
  private String city;
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
@Override
public String toString() {
	return "UserVO [userId=" + userId + ", name=" + name + ", gender=" + gender + ", city=" + city + "]";
}
  
  
  

  
  

}
