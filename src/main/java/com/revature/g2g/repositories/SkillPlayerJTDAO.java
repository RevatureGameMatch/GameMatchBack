package com.revature.g2g.repositories;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillPlayerJT;
import com.revature.g2g.services.helpers.HibernateUtil;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SkillPlayerJTDAO implements ISkillPlayerJTDAO{

	@Override
	public void insert(SkillPlayerJT sp) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.save(sp);
		
		tx.commit();
		HibernateUtil.closeSession();
	}

	@Override
	public SkillPlayerJT findById(int id) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		SkillPlayerJT sp = ses.get(SkillPlayerJT.class, id);
		
		tx.commit();
		HibernateUtil.closeSession();
		
		return sp;
	}

	@Override
	public double findValue(Player player, Skill skill) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery <SkillPlayerJT> query = builder.createQuery(SkillPlayerJT.class);
		
		Root<SkillPlayerJT> root = query.from(SkillPlayerJT.class);
		Path<Object> playerPath = root.get("player");
		Path<Object> skillPath = root.get("skill");
		
		Predicate playerPredicate = builder.equal(playerPath, player);
		Predicate skillPredicate = builder.equal(skillPath, skill);
		Predicate skillAndPlayerPredicate = builder.and(playerPredicate, skillPredicate);
		
		query.select(root).where(skillAndPlayerPredicate);
		
		Query<SkillPlayerJT> pr = ses.createQuery(query);
		
		try {
			
			List<SkillPlayerJT> listPr = pr.getResultList();
			return listPr.get(0).getValue();
			
		} catch (javax.persistence.NoResultException e) {
			
			return -1;
			
		} finally {
			
			tx.commit();
			HibernateUtil.closeSession();
			
		}
		
	}

	@Override
	public Set<SkillPlayerJT> findAll() {
		Set<SkillPlayerJT> set = null;
		
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<SkillPlayerJT> query = builder.createQuery(SkillPlayerJT.class);
		
		Query<SkillPlayerJT> sp = ses.createQuery(query);
		
		set = sp.getResultStream()
				.collect(Collectors.toSet());
		
		tx.commit();
		HibernateUtil.closeSession();
		
		return set;
	}

	@Override
	public Set<SkillPlayerJT> findBySkill(Skill skill) {
		Set<SkillPlayerJT> set = null;
		
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<SkillPlayerJT> query = builder.createQuery(SkillPlayerJT.class);
		
		Root<SkillPlayerJT> root = query.from(SkillPlayerJT.class);
		
		query.select(root).where(builder.equal(root.get("skill"), skill));
		
		Query<SkillPlayerJT> sp = ses.createQuery(query);
		
		set = sp.getResultStream()
				.collect(Collectors.toSet());
		
		tx.commit();
		HibernateUtil.closeSession();
		
		return set;
	}
	
	@Override
	public Set<Skill> findPlayerSkills(Player player) {
		Set<Skill> set = new HashSet<>();
		
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<SkillPlayerJT> query = builder.createQuery(SkillPlayerJT.class);
		
		Root<SkillPlayerJT> root = query.from(SkillPlayerJT.class);
		
		query.select(root).where(builder.equal(root.get("player"), player));
		
		Query<SkillPlayerJT> sp = ses.createQuery(query);
		
		List<SkillPlayerJT> list = sp.getResultList();
		
		for (int i = 0; i < list.size(); i++) {
			
			SkillPlayerJT spJT = list.get(i);
			
			Skill skill = spJT.getSkill();
			
			set.add(skill);
				
		}
		
		tx.commit();
		HibernateUtil.closeSession();
		return set;
		
	}
	
	
	@Override
	public void update(SkillPlayerJT sp) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.update(sp);
		
		tx.commit();
		HibernateUtil.closeSession();
	}

	@Override
	public void delete(SkillPlayerJT sp) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.delete(sp);
		
		tx.commit();
		HibernateUtil.closeSession();
	}

}
