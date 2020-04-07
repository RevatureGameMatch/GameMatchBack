package com.revature.g2g.repositories;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.g2g.models.Game;

@Transactional
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class GameDAO implements IGameDAO {
	
	@Autowired
	private SessionFactory sf;

	public void insert(Game g) {
		
		Session s = sf.getCurrentSession();
		s.save(g);
		
	}

	public Game findById(int id) {
		
		Session s = sf.getCurrentSession();
		return s.get(Game.class, id);
		
	}

	public Game findByName(String name) {
		
		Session ses = sf.getCurrentSession();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<Game> query = builder.createQuery(Game.class);
		
		Root<Game> root = query.from(Game.class);
		
		query.select(root).where(builder.equal(root.get("name"), name));
		
		Query<Game> game = ses.createQuery(query);
		
		try {
			
			return game.getSingleResult();
			
		} catch (javax.persistence.NoResultException e) {
			
			return null;	
			
		} 
		
	}

	public Set<Game> findAll() {
		
		Session ses = sf.getCurrentSession();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<Game> query = builder.createQuery(Game.class);
		
		query.from(Game.class);
		
		Query<Game> game = ses.createQuery(query);
		
		try {
			
			return game.getResultStream()
					.collect(Collectors.toSet());
			
		} catch (javax.persistence.NoResultException e) {
			
			return Collections.emptySet();	
			
		} 
	
	}

	public void update(Game g) {
		
		Session s = sf.getCurrentSession();
		s.update(g);
		
	}

	public void delete(Game g) {
		
		Session s = sf.getCurrentSession();
		s.delete(g);
		
	}
}
