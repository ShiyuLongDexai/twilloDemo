package com.lendup.myapp.DAO;

import java.util.List;




import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lendup.myapp.model.PhoneCall;
@Repository
public class PhoneDapImpl implements PhoneDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<PhoneCall> getPhoneCall() {
		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery("from PhoneCall");
		List list = query.list();


		return list;
	}

	@Override
	public void add(PhoneCall p) {
		Session s = sessionFactory.getCurrentSession();
		s.save(p);
		s.flush();
		
	}
	
	
}
