package com.disneyland.attraction.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.disneyland.attraction.model.Location;

@Repository
public class LocationDAOImpl implements LocationDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Location get(Long locationId) {
		return sessionFactory.getCurrentSession().get(Location.class, locationId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Location> get() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Location> criteriaQuery = criteriaBuilder.createQuery(Location.class);
		Root<Location> root = criteriaQuery.from(Location.class);
		criteriaQuery.select(root);
		return session.createQuery("from Location").list();
	}
}
