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

import com.revature.g2g.models.DiscordInvite;
import com.revature.g2g.models.DiscordInviteStatus;
import com.revature.g2g.models.Player;

@Transactional
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class DiscordInviteDAO implements IDiscordInviteDAO{
	private SessionFactory sf;
	@Autowired
	public DiscordInviteDAO(SessionFactory sf) {
		super();
		this.sf = sf;
	}
	@Override
	public void insert(DiscordInvite discordInvite) {
		Session s = sf.getCurrentSession();
		s.save(discordInvite);
	}
	@Override
	public DiscordInvite findById(int id) {
		Session s = sf.getCurrentSession();
		return s.get(DiscordInvite.class, id);
	}
	@Override
	public DiscordInvite findByDiscordCode(String discordCode) {
		Session ses = sf.getCurrentSession();
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<DiscordInvite> query = builder.createQuery(DiscordInvite.class);
		Root<DiscordInvite> root = query.from(DiscordInvite.class);
		query.select(root).where(builder.equal(root.get("discordCode"), discordCode));
		Query<DiscordInvite> invite = ses.createQuery(query);
		try {
			return invite.getSingleResult();
		} catch (javax.persistence.NoResultException e) {
			return null;	
		} 
	}
	@Override
	public Set<DiscordInvite> findAll() {
		Session ses = sf.getCurrentSession();
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<DiscordInvite> query = builder.createQuery(DiscordInvite.class);
		query.from(DiscordInvite.class);
		Query<DiscordInvite> invite = ses.createQuery(query);
		try {
			return invite.getResultStream()
					.collect(Collectors.toSet());
		} catch (javax.persistence.NoResultException e) {
			return Collections.emptySet();	
		} 
	}
	@Override
	public Set<DiscordInvite> findByStatus(DiscordInviteStatus status) {
		Session ses = sf.getCurrentSession();
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<DiscordInvite> query = builder.createQuery(DiscordInvite.class);
		Root<DiscordInvite> root = query.from(DiscordInvite.class);
		query.select(root).where(builder.equal(root.get("status"), status));
		Query<DiscordInvite> invite = ses.createQuery(query);
		try {
			return invite.getResultStream()
					.collect(Collectors.toSet());
		} catch (javax.persistence.NoResultException e) {
			return Collections.emptySet();	
		} 
	}
	@Override
	public Set<DiscordInvite> findByPlayer(Player player) {
		Session ses = sf.getCurrentSession();
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<DiscordInvite> query = builder.createQuery(DiscordInvite.class);
		Root<DiscordInvite> root = query.from(DiscordInvite.class);
		query.select(root).where(builder.equal(root.get("player"), player));
		Query<DiscordInvite> invite = ses.createQuery(query);
		try {
			return invite.getResultStream()
					.collect(Collectors.toSet());
		} catch (javax.persistence.NoResultException e) {
			return Collections.emptySet();	
		} 
	}
	@Override
	public void update(DiscordInvite discordInvite) {
		Session s = sf.getCurrentSession();
		s.update(discordInvite);
	}
	@Override
	public void delete(DiscordInvite discordInvite) {
		Session s = sf.getCurrentSession();
		s.delete(discordInvite);
	}
}