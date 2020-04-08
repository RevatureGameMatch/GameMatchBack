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

import com.revature.g2g.models.Skill;

@Transactional
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SkillDAO implements ISkillDAO {
	@Autowired
	private SessionFactory sf;

	@Override
	public void insert(Skill s) {
		
		Session ses = sf.getCurrentSession();
		ses.save(s);
		
	}

	@Override
	public Skill findById(int id) {
		
		Session ses = sf.getCurrentSession();
		
		return ses.get(Skill.class, id);
		
	}

	@Override
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

	@Override
	public Set<Skill> findAll() {
		
		Session ses = sf.getCurrentSession();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<Skill> query = builder.createQuery(Skill.class);
		
		Root<Skill> root = query.from(Skill.class);
		query.select(root);
		
		Query<Skill> skill = ses.createQuery(query);
		
		try {
			
			return skill.getResultStream()
					.collect(Collectors.toSet());
			
		} catch (javax.persistence.NoResultException e) {
			
			return Collections.emptySet();
			
		}
	}

	@Override
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

	@Override
	public void update(Skill s) {
		
		Session ses = sf.getCurrentSession();
		ses.update(s);
		
	}

	@Override
	public void delete(Skill s) {
		
		Session ses = sf.getCurrentSession();
		ses.delete(s);
		
	}

}
