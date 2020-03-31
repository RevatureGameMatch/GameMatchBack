package com.revature.g2g.repositories;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Player> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Player> findByRole(PlayerRole role) {
		// TODO Auto-generated method stub
		return null;
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
