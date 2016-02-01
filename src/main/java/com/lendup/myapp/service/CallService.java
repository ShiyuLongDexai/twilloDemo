package com.lendup.myapp.service;

import java.util.ArrayList;
import java.util.List;

import com.lendup.myapp.model.PhoneCall;


public interface CallService {
	public void addCall(PhoneCall p);
	public List<PhoneCall> getPhoneCalls();
	}
