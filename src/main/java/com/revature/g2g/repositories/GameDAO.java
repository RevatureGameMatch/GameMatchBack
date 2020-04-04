package com.revature.g2g.repositories;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.revature.g2g.models.Game;
import com.revature.g2g.services.helpers.HibernateUtil;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
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
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<Game> query = builder.createQuery(Game.class);
		
		Root<Game> root = query.from(Game.class);
		
		query.select(root).where(builder.equal(root.get("name"), name));
		
		Query<Game> game = ses.createQuery(query);
		
		Game g = game.getSingleResult();
		
		tx.commit();
		HibernateUtil.closeSession();
		return g;
	}

	public Set<Game> findAll() {
		Set<Game> set = null;
		
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<Game> query = builder.createQuery(Game.class);
		
		Query<Game> game = ses.createQuery(query);
		
		set = game.getResultStream()
				.collect(Collectors.toSet());

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
