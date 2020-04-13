package com.revature.g2g.repositories;

import java.util.Collections;
import java.util.NoSuchElementException;
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

import com.revature.g2g.models.Player;
import com.revature.g2g.models.PlayerRole;

@Transactional
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PlayerDAO implements IPlayerDAO {
	
	@Autowired
	private SessionFactory sf;

	@Override
	public void insert(Player p) {
		
		Session s = sf.getCurrentSession();
		s.save(p);
		
	}

	@Override
	public Player findById(int id) {
		
		Session s = sf.getCurrentSession();
		return s.get(Player.class, id);
		
	}

	@Override
	public Player findByUsername(String username) {
		
		Session ses = sf.getCurrentSession();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<Player> query = builder.createQuery(Player.class);
		
		Root<Player> root = query.from(Player.class);
		
		query.select(root).where(builder.equal(root.get("playerUsername"), username));
	
		Query<Player> player = ses.createQuery(query);
		
		try {
			
			return player.getSingleResult();
			
		} catch (javax.persistence.NoResultException e) {
			
			return null;	
			
		}
	
	}

	@Override
	public Player findByEmail(String email) {
		
		Session ses = sf.getCurrentSession();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<Player> query = builder.createQuery(Player.class);
		
		Root<Player> root = query.from(Player.class);
		
		query.select(root).where(builder.equal(root.get("playerEmail"), email));
		
		Query<Player> player = ses.createQuery(query);
		
		try {
			
			return player.getSingleResult();
			
		} catch (javax.persistence.NoResultException e) {
			
			return null;
			
		}
		
	}

	@Override
	public Set<Player> findAll() {
		
		Session ses = this.sf.getCurrentSession();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<Player> query = builder.createQuery(Player.class);
		
		query.from(Player.class);
		
		Query<Player> player = ses.createQuery(query);
		
		try {
			
			return player.getResultStream()
					.collect(Collectors.toSet());
			
		} catch (javax.persistence.NoResultException e) {
			
			return Collections.emptySet();
			
		}
		
	}

	@Override
	public Set<Player> findByRole(PlayerRole role) {
		
		Session ses = sf.getCurrentSession();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<Player> query = builder.createQuery(Player.class);
		
		Root<Player> root = query.from(Player.class);
		
		query.select(root).where(builder.equal(root.get("playerRole"), role));
		
		Query<Player> player = ses.createQuery(query);
		
		try {
			
			return player.getResultStream()
					.collect(Collectors.toSet());
			
		} catch (javax.persistence.NoResultException e) {
			
			return Collections.emptySet();
			
		}
		
	}

	@Override
	public void update(Player p) {
		
		Session s = sf.getCurrentSession();
		s.update(p);
		
	}

	@Override
	public void delete(Player p) {
		
		Session s = sf.getCurrentSession();
		s.delete(p);
		
	}

}
