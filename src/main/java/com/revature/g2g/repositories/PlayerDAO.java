package com.revature.g2g.repositories;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.PlayerRole;
import com.revature.g2g.services.helpers.HibernateUtil;

public class PlayerDAO implements IPlayerDAO {

	@Override
	public void insert(Player p) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		ses.save(p);
		
		tx.commit();
		HibernateUtil.closeSession();
	}

	@Override
	public Player findById(int id) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		Player p = ses.get(Player.class, id);
		
		tx.commit();
		HibernateUtil.closeSession();
		return p;
	}

	@Override
	public Player findByUsername(String username) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<Player> query = builder.createQuery(Player.class);
		
		Root<Player> root = query.from(Player.class);
		
		query.select(root).where(builder.equal(root.get("playerUsername"), username));
		
		Query<Player> player = ses.createQuery(query);
		
		Player p = player.getSingleResult();
		
		tx.commit();
		HibernateUtil.closeSession();
		return p;
	}

	@Override
	public Player findByEmail(String email) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<Player> query = builder.createQuery(Player.class);
		
		Root<Player> root = query.from(Player.class);
		
		query.select(root).where(builder.equal(root.get("playerEmail"), email));
		
		Query<Player> player = ses.createQuery(query);
		
		Player p = player.getSingleResult();
		
		
		tx.commit();
		HibernateUtil.closeSession();
		return p;
	}

	@Override
	public Set<Player> findAll() {
		Set<Player> set = null;
		
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<Player> query = builder.createQuery(Player.class);
		
		
		Query<Player> player = ses.createQuery(query);
		
		set = player.getResultStream()
				.collect(Collectors.toSet());
		
		tx.commit();
		
		HibernateUtil.closeSession();
		return set;
	}

	@Override
	public Set<Player> findByRole(PlayerRole role) {
		Set<Player> set = null;
		
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<Player> query = builder.createQuery(Player.class);
		
		Root<Player> root = query.from(Player.class);
		
		query.select(root).where(builder.equal(root.get("playerRole"), role));
		
		Query<Player> player = ses.createQuery(query);
		
		set = player.getResultStream()
				.collect(Collectors.toSet());
		
		
		tx.commit();
		
		
		HibernateUtil.closeSession();
		return set;
	}

	@Override
	public void update(Player p) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		ses.update(p);

		tx.commit();
		HibernateUtil.closeSession();
	}

	@Override
	public void delete(Player p) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.delete(p);

		tx.commit();
		HibernateUtil.closeSession();
	}

}
