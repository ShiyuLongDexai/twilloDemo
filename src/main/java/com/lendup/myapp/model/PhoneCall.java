package com.lendup.myapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PhoneCall {
	@Id
	@GeneratedValue
	private int callid;
	@Column(length = 30)
	private String phonenumber;
	@Column(length = 30)
	private String sleepTime;
	private Date calltime;
	@Column(length = 30)
	private String input;
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getSleepTime() {
		return sleepTime;
	}
	public void setSleepTime(String sleepTime) {
		this.sleepTime = sleepTime;
	}
	public Date getCalltime() {
		return calltime;
	}
	public void setCalltime(Date calltime) {
		this.calltime = calltime;
	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	
}
