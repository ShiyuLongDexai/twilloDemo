package com.lendup.myapp.DAO;

import java.util.List;

import com.lendup.myapp.model.PhoneCall;


public interface PhoneDao {
	public void add(PhoneCall p);
	public List<PhoneCall> getPhoneCall();
}
