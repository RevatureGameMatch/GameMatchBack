package com.revature.g2g.repositories;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
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
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillPlayerJT;
import com.revature.g2g.services.helpers.LoggerSingleton;

@Transactional
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SkillPlayerJTDAO {
	private SessionFactory sf;
	private LoggerSingleton loggerSingleton;
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	public SkillPlayerJTDAO(LoggerSingleton loggerSingleton) {
		super();
		Session session = entityManager.unwrap(Session.class);
		this.sf = session.getSessionFactory();
		this.loggerSingleton = loggerSingleton;
	}

	public void insert(SkillPlayerJT sp) {
		
		Session ses = sf.getCurrentSession();
		ses.save(sp);
	
	}

	public SkillPlayerJT findById(int id) {
		
		Session ses = sf.getCurrentSession();
		return ses.get(SkillPlayerJT.class, id);
		
	}

	public double findValue(Player player, Skill skill) {
		
		Session ses = sf.getCurrentSession();
		
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
			
			return -1d;
			
		} 
		
	}

	public Set<SkillPlayerJT> findAll() {
		
		Session ses = sf.getCurrentSession();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<SkillPlayerJT> query = builder.createQuery(SkillPlayerJT.class);
		
		query.from(SkillPlayerJT.class);
		
		Query<SkillPlayerJT> sp = ses.createQuery(query);
		
		try {
			
			return sp.getResultStream()
					.collect(Collectors.toSet());
			
		} catch (javax.persistence.NoResultException e) {
			
			return Collections.emptySet();
			
		}
	}

	public Set<SkillPlayerJT> findBySkill(Skill skill) {
	
		Session ses = sf.getCurrentSession();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<SkillPlayerJT> query = builder.createQuery(SkillPlayerJT.class);
		
		Root<SkillPlayerJT> root = query.from(SkillPlayerJT.class);
		
		query.select(root).where(builder.equal(root.get("skill"), skill));
		
		Query<SkillPlayerJT> sp = ses.createQuery(query);
		
		try {
			
			return sp.getResultStream()
					.collect(Collectors.toSet());
			
		} catch (javax.persistence.NoResultException e) {
			
			return Collections.emptySet();
			
		}
	}

	public Set<SkillPlayerJT> findByPlayer(Player player) {
	
		Session ses = sf.getCurrentSession();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<SkillPlayerJT> query = builder.createQuery(SkillPlayerJT.class);
		
		Root<SkillPlayerJT> root = query.from(SkillPlayerJT.class);
		
		query.select(root).where(builder.equal(root.get("player"), player));
		
		Query<SkillPlayerJT> sp = ses.createQuery(query);
		
		try {
			
			return sp.getResultStream()
					.collect(Collectors.toSet());
			
		} catch (javax.persistence.NoResultException e) {
			
			return Collections.emptySet();
			
		}
	}
	
	public Set<Skill> findPlayerSkills(Player player) {
		Set<Skill> set = new HashSet<>();
		
		Session ses = sf.getCurrentSession();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<SkillPlayerJT> query = builder.createQuery(SkillPlayerJT.class);
		
		Root<SkillPlayerJT> root = query.from(SkillPlayerJT.class);
		
		query.select(root).where(builder.equal(root.get("player"), player));
		
		Query<SkillPlayerJT> sp = ses.createQuery(query);
		
		try {
			
			List<SkillPlayerJT> list = sp.getResultList();
			
			for (int i = 0; i < list.size(); i++) {
				
				SkillPlayerJT spJT = list.get(i);
				
				Skill skill = spJT.getSkill();
				
				set.add(skill);
					
			}
		
			return set;
			
		} catch (javax.persistence.NoResultException e) {
			
			return Collections.emptySet();
			
		}
		
	}

	public SkillPlayerJT findBySkillPlayer(Skill skill, Player player) {
		Set<SkillPlayerJT> set = null;
		
		Session ses = sf.getCurrentSession();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<SkillPlayerJT> query = builder.createQuery(SkillPlayerJT.class);
		
		Root<SkillPlayerJT> root = query.from(SkillPlayerJT.class);
		Path<Object> skillPath = root.get("skill");
		Path<Object> playerPath = root.get("player");
		
		Predicate skillPredicate = builder.equal(skillPath, skill);
		Predicate playerPredicate = builder.equal(playerPath, player);
		Predicate skillAndPlayerPredicate = builder.and(skillPredicate,playerPredicate);
		
		query.select(root).where(skillAndPlayerPredicate);
		
		Query<SkillPlayerJT> sg = ses.createQuery(query);
		
		try {
			set = sg.getResultStream()
					.collect(Collectors.toSet());
			
			if(set.size() == 1) {
				return sg.getSingleResult();
			}
			
			else{	
				return set.iterator().next();
			}
		} catch (javax.persistence.NoResultException e) {
		
			return null;
		} catch (NoSuchElementException e) {
			return null;
		} catch (Exception e) {
			loggerSingleton.getExceptionLogger().warn("SkillPlayerJTDAO threw an error in findBySkillPlayer", e);
			return null;
		}
		
	}
	
	
	public void update(SkillPlayerJT sp) {
		
		Session ses = sf.getCurrentSession();
		ses.update(sp);
		
	}

	public void delete(SkillPlayerJT sp) {
		
		Session ses = sf.getCurrentSession();
		ses.delete(sp);
		
	}

}
