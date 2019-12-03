package com.disneyland.attraction.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.disneyland.attraction.model.Attraction;
import com.disneyland.attraction.utils.PrimaryKey;

@Repository
public class AttractionDAOImpl implements AttractionDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public long nextPrimaryKey() {
		return PrimaryKey.nextLong("attraction", "attraction_id", sessionFactory);
	}
	
	@Override
	public void create(Attraction attraction) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(attraction);
//		session.save(attraction);
//		session.flush();
	}

	@Override
	public void modify(Attraction attraction) {
		Session session = sessionFactory.getCurrentSession();
		session.update(attraction);
	}
	
	@Override
	public void remove(Long attractionId) {
		Session session = sessionFactory.getCurrentSession();
		Attraction attraction = (Attraction)session.load(Attraction.class, new Long(attractionId));
		if (attraction != null) {
			session.delete(attraction);
		}
	}
	
	@Override
	public Attraction get(Long attractionId) {
		return sessionFactory.getCurrentSession().get(Attraction.class, attractionId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Attraction> get() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Attraction> criteriaQuery = criteriaBuilder.createQuery(Attraction.class);
		Root<Attraction> root = criteriaQuery.from(Attraction.class);
		criteriaQuery.select(root);
		return session.createQuery("from Attraction").list();
	}
}
