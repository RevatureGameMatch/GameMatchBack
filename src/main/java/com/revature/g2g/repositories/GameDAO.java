package com.revature.g2g.repositories;

import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.g2g.models.Game;
import com.revature.g2g.models.Skill;
import com.revature.g2g.services.helpers.HibernateUtil;

public class GameDAO implements IGameDAO {

	public void insert(Game g) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		ses.save(g);

		tx.commit();
		HibernateUtil.closeSession();
	}

	public Game findById(int id) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		Game g = ses.get(Game.class, id);
		
		tx.commit();
		HibernateUtil.closeSession();
		return g;
	}

	public Game findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<Game> findAll() {
		Set<Game> set = null;
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<Game> query = builder.createQuery(Game.class);
		Root<Game> root = query.from(Game.class);
		query.select(root);
		Query<Game> q = ses.createQuery(query);
		//TODO fix this
//		set = q.getResultStream()
//				.collect(Collectors.toSet());
		tx.commit();
		HibernateUtil.closeSession();
		return set;
	}

	public Set<Game> findBySkill(Skill skill) {
		Set<Game> set = null;
		
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<Game> query = builder.createQuery(Game.class);
		Root<Game> root = query.from(Game.class);
		//TODO query.select
		Query<Game> q = ses.createQuery(query);
		//TODO fix this
//		set = q.getResultStream()
//				.collect(Collectors.toSet());
		tx.commit();
		HibernateUtil.closeSession();
		return set;
	}

	public void update(Game g) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.update(g);
		
		tx.commit();
		HibernateUtil.closeSession();
	}

	public void delete(Game g) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.delete(g);
		
		tx.commit();
		HibernateUtil.closeSession();
	}
}
