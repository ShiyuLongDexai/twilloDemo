package com.lendup.myapp.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lendup.myapp.DAO.PhoneDao;
import com.lendup.myapp.model.PhoneCall;


@Service
public class CallServiceImpl implements CallService {
	@Resource
	private PhoneDao phoneDao;

	@Override
	@Transactional
	public void addCall(PhoneCall p) {
		phoneDao.add(p);

	}

	@Override
	@Transactional
	public List<PhoneCall> getPhoneCalls() {
		// TODO Auto-generated method stub
		return phoneDao.getPhoneCall();
	}

}
