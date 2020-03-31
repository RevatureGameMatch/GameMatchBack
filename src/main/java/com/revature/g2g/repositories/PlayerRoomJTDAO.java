package com.revature.g2g.repositories;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.PlayerRoomJT;
import com.revature.g2g.models.Room;
import com.revature.g2g.services.helpers.HibernateUtil;

public class PlayerRoomJTDAO implements IPlayerRoomJTDAO {

	@Override
	public void insert(PlayerRoomJT pr) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		ses.save(pr);

		tx.commit();
		HibernateUtil.closeSession();
	}

	@Override
	public PlayerRoomJT findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countCurrentPlayers() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countCurrentPlayers(Room room) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<PlayerRoomJT> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Player> findPlayers(Room room) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Room> findRooms(Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(PlayerRoomJT pr) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		ses.update(pr);

		tx.commit();
		HibernateUtil.closeSession();
	}

	@Override
	public void delete(PlayerRoomJT pr) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		ses.delete(pr);

		tx.commit();
		HibernateUtil.closeSession();
	}

}
