package com.revature.g2g.repositories;

import java.util.Collections;
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

import com.revature.g2g.models.Skill;
import com.revature.g2g.services.helpers.HibernateUtil;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SkillDAO implements ISkillDAO {

	@Override
	public void insert(Skill s) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		ses.save(s);

		tx.commit();
		HibernateUtil.closeSession();
	}

	@Override
	public Skill findById(int id) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		Skill skill = ses.get(Skill.class, id);

		tx.commit();
		HibernateUtil.closeSession();
		
		return skill;
	}

	@Override
	public Skill findByName(String name) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<Skill> query = builder.createQuery(Skill.class);
		
		Root<Skill> root = query.from(Skill.class);
		
		query.select(root).where(builder.equal(root.get("name"), name));
	
		Query<Skill> skill = ses.createQuery(query);
		
		try {
			
			return skill.getSingleResult();
			
		} catch (javax.persistence.NoResultException e) {
			
			return null;	
			
		} finally {
			
			tx.commit();
			HibernateUtil.closeSession();
			
		}
	}

	@Override
	public Set<Skill> findAll() {
		Set<Skill> set = null;
		
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<Skill> query = builder.createQuery(Skill.class);
		
		Root<Skill> root = query.from(Skill.class);
		query.select(root);
		
		Query<Skill> skill = ses.createQuery(query);
		
		
		set = skill.getResultStream()
				.collect(Collectors.toSet());
		
		tx.commit();
		HibernateUtil.closeSession();
		
		return set;
	}

	@Override
	public Set<Skill> findByParent(Skill skill) {
		Set<Skill> set = null;
		
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<Skill> query = builder.createQuery(Skill.class);
		
		Root<Skill> root = query.from(Skill.class);
		
		query.select(root).where(builder.equal(root.get("parentSkill"), skill));
		
		Query<Skill> s = ses.createQuery(query);
		
		try {
			
			set = s.getResultStream()
					.collect(Collectors.toSet());
			return set;
			
		} catch (javax.persistence.NoResultException e) {
			
			return Collections.emptySet();
			
		} finally {
			
			tx.commit();
			HibernateUtil.closeSession();
			
		}
			
	}

	@Override
	public void update(Skill s) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		ses.update(s);

		tx.commit();
		HibernateUtil.closeSession();
	}

	@Override
	public void delete(Skill s) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		ses.delete(s);

		tx.commit();
		HibernateUtil.closeSession();
	}

}
