package com.revature.g2g.repositories;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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
import com.revature.g2g.models.PlayerRoomJT;
import com.revature.g2g.models.Room;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillGameJT;
import com.revature.g2g.services.helpers.HibernateUtil;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SkillGameJTDAO implements ISkillGameJTDAO {

	@Override
	public void insert(SkillGameJT sg) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.save(sg);
		
		tx.commit();
		HibernateUtil.closeSession();
	}

	@Override
	public SkillGameJT findById(int id) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		SkillGameJT sg = ses.get(SkillGameJT.class, id);
		
		tx.commit();
		HibernateUtil.closeSession();
		
		return sg;
	}

	@Override
	public Set<SkillGameJT> findAll() {
		Set<SkillGameJT> set = null;
		
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<SkillGameJT> query = builder.createQuery(SkillGameJT.class);
		
		Query<SkillGameJT> sg = ses.createQuery(query);
		
		set = sg.getResultStream()
				.collect(Collectors.toSet());
		
		tx.commit();
		HibernateUtil.closeSession();
		
		return set;

	}

	@Override
	public Set<Game> findBySkill(Skill skill) {
		Set<Game> set = new HashSet<>();
		
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<SkillGameJT> query = builder.createQuery(SkillGameJT.class);
		
		Root<SkillGameJT> root = query.from(SkillGameJT.class);
		
		query.where(builder.equal(root.get("skill"), skill));
		
		Query<SkillGameJT> sg = ses.createQuery(query);
		
		List<SkillGameJT> list = sg.getResultList();
		
		if(!list.isEmpty()) {
			
			for (int i = 0; i < list.size(); i++) {
				
				SkillGameJT sgJT = list.get(i);
				Game game = sgJT.getGame();
				
				set.add(game);	
			}
			
			tx.commit();
			HibernateUtil.closeSession();
			
			return set;
			
		} else {
			
			tx.commit();
			HibernateUtil.closeSession();
			
			return Collections.emptySet();
			
		}
		
	}

	@Override
	public Skill findTopSkill(Game game) {
		
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<SkillGameJT> query = builder.createQuery(SkillGameJT.class);
		
		Root<SkillGameJT> root = query.from(SkillGameJT.class);
		
		query.where(builder.equal(root.get("game"), game));
		query.orderBy(builder.desc(root.get("relevance")));
		
		Query<SkillGameJT> sg = ses.createQuery(query);
		
		List<SkillGameJT> list = sg.getResultList();
		
		if (!list.isEmpty()) {
			
			tx.commit();
			HibernateUtil.closeSession();
			
			return list.get(0).getSkill();
			
		} else {
			
			tx.commit();
			HibernateUtil.closeSession();
			
			return null;
		}
	}

	@Override
	public Set<Skill> findByGame(Game game) {
		Set<Skill> set = new HashSet<>();
		
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<SkillGameJT> query = builder.createQuery(SkillGameJT.class);
		
		Root<SkillGameJT> root = query.from(SkillGameJT.class);
		
		query.where(builder.equal(root.get("game"), game));
		
		Query<SkillGameJT> sg = ses.createQuery(query);
		
		List<SkillGameJT> list = sg.getResultList();
		
		if(!list.isEmpty()) {
			
			for (int i = 0; i < list.size(); i++) {
				
				SkillGameJT sgJT = list.get(i);
				Skill skill = sgJT.getSkill();
				
				set.add(skill);	
			}
			
			tx.commit();
			HibernateUtil.closeSession();
			
			return set;
			
		} else {
			
			tx.commit();
			HibernateUtil.closeSession();
			
			return Collections.emptySet();
			
		}
	}

	@Override
	public void update(SkillGameJT sg) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.update(sg);
		
		tx.commit();
		HibernateUtil.closeSession();
	}

	@Override
	public void delete(SkillGameJT sg) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.delete(sg);
		
		tx.commit();
		HibernateUtil.closeSession();
	}

}
