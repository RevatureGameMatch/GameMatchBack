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
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.g2g.models.Game;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillGameJT;

@Transactional
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SkillGameJTDAO {
	private SessionFactory sf;
	@PersistenceContext
	private EntityManager entityManager;
	public SkillGameJTDAO() {
		super();
		Session session = entityManager.unwrap(Session.class);
		this.sf = session.getSessionFactory();
	}

	public void insert(SkillGameJT sg) {
		
		Session ses = sf.getCurrentSession();
		ses.save(sg);
	
	}

	public SkillGameJT findById(int id) {
		
		Session ses = sf.getCurrentSession();	
		return ses.get(SkillGameJT.class, id);
		
	}

	public Set<SkillGameJT> findAll() {
	
		Session ses = sf.getCurrentSession();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<SkillGameJT> query = builder.createQuery(SkillGameJT.class);
		
		query.from(SkillGameJT.class);
		
		Query<SkillGameJT> sg = ses.createQuery(query);
		
		try {
			
			return sg.getResultStream()
					.collect(Collectors.toSet());
			
		} catch (javax.persistence.NoResultException e) {
			
			return Collections.emptySet();
			
		}

	}

	public Set<Game> findBySkill(Skill skill) {
		
		Set<Game> set = new HashSet<>();
		
		Session ses = sf.getCurrentSession();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<SkillGameJT> query = builder.createQuery(SkillGameJT.class);
		
		Root<SkillGameJT> root = query.from(SkillGameJT.class);
		
		query.where(builder.equal(root.get("skill"), skill));
		
		Query<SkillGameJT> sg = ses.createQuery(query);
		
		try {
			
			List<SkillGameJT> list = sg.getResultList();
			
			if(!list.isEmpty()) {
				
				for (int i = 0; i < list.size(); i++) {
					
					SkillGameJT sgJT = list.get(i);
					Game game = sgJT.getGame();
					
					set.add(game);	
				}
				
			}
			
			return set;
			
		} catch (javax.persistence.NoResultException e) {
			
			return Collections.emptySet();
			
		}
		
	}

	public Skill findTopSkill(Game game) {
		
		Session ses = sf.getCurrentSession();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<SkillGameJT> query = builder.createQuery(SkillGameJT.class);
		
		Root<SkillGameJT> root = query.from(SkillGameJT.class);
		
		query.where(builder.equal(root.get("game"), game));
		query.orderBy(builder.desc(root.get("relevance")));
		
		Query<SkillGameJT> sg = ses.createQuery(query);
		
		try {
			
			List<SkillGameJT> list = sg.getResultList();
			return list.get(0).getSkill();
			
		} catch (javax.persistence.NoResultException e) {
			
			return null;
			
		}
		
	}

	public Set<Skill> findByGame(Game game) {
		
		Set<Skill> set = new HashSet<>();
		
		Session ses = sf.getCurrentSession();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<SkillGameJT> query = builder.createQuery(SkillGameJT.class);
		
		Root<SkillGameJT> root = query.from(SkillGameJT.class);
		
		query.where(builder.equal(root.get("game"), game));
		
		Query<SkillGameJT> sg = ses.createQuery(query);
		
		try {
			
			List<SkillGameJT> list = sg.getResultList();
			
			for (int i = 0; i < list.size(); i++) {
				
				SkillGameJT sgJT = list.get(i);
				Skill skill = sgJT.getSkill();
				
				set.add(skill);	
			}
			
			return set;
			
		} catch (javax.persistence.NoResultException e) {
			
			return Collections.emptySet();
			
		}
		
	}
	
	public SkillGameJT findBySkillGame(Skill skill, Game game) {
		Set<SkillGameJT> set = null;
		
		Session ses = sf.getCurrentSession();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<SkillGameJT> query = builder.createQuery(SkillGameJT.class);
		
		Root<SkillGameJT> root = query.from(SkillGameJT.class);
		Path<Object> skillPath = root.get("skill");
		Path<Object> gamePath = root.get("game");
		
		Predicate skillPredicate = builder.equal(skillPath, skill);
		Predicate gamePredicate = builder.equal(gamePath, game);
		Predicate skillAndGamePredciate = builder.and(skillPredicate, gamePredicate);
		
		query.select(root).where(skillAndGamePredciate);
		
		Query<SkillGameJT> sg = ses.createQuery(query);
		
		try {
			
			set = sg.getResultStream()
					.collect(Collectors.toSet());
			
			if(set.size() == 1) {
				return sg.getSingleResult();
			} else {
				return set.iterator().next();
			}
			
		} catch (javax.persistence.NoResultException|NoSuchElementException e) {
			
			return null;
			
		}
		
	}

	public void update(SkillGameJT sg) {
		
		Session ses = sf.getCurrentSession();
		ses.update(sg);
		
	}

	public void delete(SkillGameJT sg) {
		
		Session ses = sf.getCurrentSession();
		ses.delete(sg);
		
	}

}
