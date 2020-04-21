package com.revature.g2g.repositories;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.g2g.models.Skill;

@Transactional
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SkillDAO {
	private SessionFactory sf;
	@PersistenceContext
	private EntityManager entityManager;
	public SkillDAO() {
		super();
		Session session = entityManager.unwrap(Session.class);
		this.sf = session.getSessionFactory();
	}

	public void insert(Skill s) {
		
		Session ses = sf.getCurrentSession();
		ses.save(s);
		
	}

	public Skill findById(int id) {
		
		Session ses = sf.getCurrentSession();
		
		return ses.get(Skill.class, id);
		
	}

	public Skill findByName(String name) {
		
		Session ses = sf.getCurrentSession();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<Skill> query = builder.createQuery(Skill.class);
		
		Root<Skill> root = query.from(Skill.class);
		
		query.select(root).where(builder.equal(root.get("name"), name));
	
		Query<Skill> skill = ses.createQuery(query);
		
		try {
			
			return skill.getSingleResult();
			
		} catch (javax.persistence.NoResultException e) {
			
			return null;	
			
		}
		
	}

	public Set<Skill> findAll() {
		
		Session ses = sf.getCurrentSession();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<Skill> query = builder.createQuery(Skill.class);
		
		query.from(Skill.class);
		
		Query<Skill> skill = ses.createQuery(query);
		
		try {
			
			return skill.getResultStream()
					.collect(Collectors.toSet());
			
		} catch (javax.persistence.NoResultException e) {
			
			return Collections.emptySet();
			
		}
	}

	public Set<Skill> findByParent(Skill skill) {
		
		Session ses = sf.getCurrentSession();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<Skill> query = builder.createQuery(Skill.class);
		
		Root<Skill> root = query.from(Skill.class);
		
		query.select(root).where(builder.equal(root.get("parentSkill"), skill));
		
		Query<Skill> s = ses.createQuery(query);
		
		try {
			
			return s.getResultStream()
					.collect(Collectors.toSet());
			
		} catch (javax.persistence.NoResultException e) {
			
			return Collections.emptySet();
			
		} 
			
	}

	public void update(Skill s) {
		
		Session ses = sf.getCurrentSession();
		ses.update(s);
		
	}

	public void delete(Skill s) {
		
		Session ses = sf.getCurrentSession();
		ses.delete(s);
		
	}

}
