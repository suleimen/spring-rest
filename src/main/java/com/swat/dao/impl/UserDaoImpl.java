package com.swat.dao.impl;

import com.swat.dao.UserDao;
import com.swat.model.Status;
import com.swat.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.transaction.Transactional;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public User getUserById(int id) {
		return (User) getSession().get(User.class, id);
	}

	public User getUserByName() {
		return null;
	}

	@SuppressWarnings({ "unchecked" })
	public List<User> getUserList(Status status) {
		Criteria criteria = getSession().createCriteria(User.class);

		if (status != null) {
			criteria.add(Restrictions.eq("status", status));
		}
		criteria.addOrder(Order.asc("id"));

		return criteria.list();

	}

	public User saveUser(User user) {

		getSession().save(user);

		return user;
	}

	public void updateUser(User user) {

		getSession().update(user);

	}

}
