package com.disneyland.attraction.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.StandardBasicTypes;

public class PrimaryKey {

	public static long nextLong(String table, String key, SessionFactory sessionFactory) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT IFNULL(MAX(" + key + "), 0) + 1 AS " + key + " FROM " + table;
		return (long)session.createNativeQuery(sql).addScalar(key, StandardBasicTypes.LONG).uniqueResult();
	}
}
