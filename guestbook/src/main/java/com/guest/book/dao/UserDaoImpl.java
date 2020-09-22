package com.guest.book.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.guest.book.entity.USER;
import com.guest.book.model.UserModel;

public class UserDaoImpl implements UserDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<USER> validateUser(UserModel model) {
		Session session = null;
		List<USER> result = new ArrayList<USER>();
		try {

			session = sessionFactory.openSession();

			Criteria crit = session.createCriteria(USER.class);
			crit.add(Restrictions.like("USERNAME", model.getUserName()));
			crit.add(Restrictions.like("PASSWORD", model.getPassword()));
			crit.add(Restrictions.like("ROLE", model.getRole()));

			result = crit.list();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {

		}

		return result;
	}

}
