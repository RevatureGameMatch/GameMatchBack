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
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.g2g.models.Room;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillRoomJT;

@Transactional
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SkillRoomJTDAO implements ISkillRoomJTDAO {
	@Autowired
	private SessionFactory sf;

	@Override
	public void insert(SkillRoomJT sr) {
	
		Session ses = sf.getCurrentSession();
		ses.save(sr);
	
	}

	@Override
	public SkillRoomJT findById(int id) {
		
		Session ses = sf.getCurrentSession();
		return ses.get(SkillRoomJT.class, id);
		
	}

//	@Override //find by game name or skill name
//	public SkillRoomJT findBySkillName(String name) {
//		Set<SkillRoomJT> set = findAll();
//		
//		for (Iterator<SkillRoomJT> it = set.iterator(); it.hasNext(); ) {
//			
//			SkillRoomJT sr = it.next();
//			String skillName = sr.getSkill().getName();
//			
//			if (skillName.equals(name)) {
//				
//				return sr;
//				
//			}
//			
//		}
//		
//		return null;
//	}
	

	@Override
	public Set<SkillRoomJT> findAll() {
	
		Session ses = sf.getCurrentSession();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<SkillRoomJT> query = builder.createQuery(SkillRoomJT.class);
		
		query.from(SkillRoomJT.class);
		
		Query<SkillRoomJT> sr = ses.createQuery(query);
		
		try {
			
			return sr.getResultStream()
					.collect(Collectors.toSet());
			
		} catch (javax.persistence.NoResultException e) {
			
			return Collections.emptySet();
			
		}
	}

	@Override
	public Set<SkillRoomJT> findBySkill(Skill skill) {
		
		Session ses = sf.getCurrentSession();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<SkillRoomJT> query = builder.createQuery(SkillRoomJT.class);
		
		Root<SkillRoomJT> root = query.from(SkillRoomJT.class);
		
		query.select(root).where(builder.equal(root.get("skill"), skill));
		
		Query<SkillRoomJT> sr = ses.createQuery(query);
		
		try {

			return sr.getResultStream()
					.collect(Collectors.toSet());
			
		} catch (javax.persistence.NoResultException e) {
			
			return Collections.emptySet();
		}
	}

	@Override
	public Set<SkillRoomJT> findByRoom(Room room) {
		
		Session ses = sf.getCurrentSession();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<SkillRoomJT> query = builder.createQuery(SkillRoomJT.class);
		
		Root<SkillRoomJT> root = query.from(SkillRoomJT.class);
		
		query.select(root).where(builder.equal(root.get("room"), room));
		
		Query<SkillRoomJT> sr = ses.createQuery(query);
		
		try {
			
			return sr.getResultStream()
					.collect(Collectors.toSet());
			
		} catch (javax.persistence.NoResultException e) {
			
			return Collections.emptySet();
			
		}
	}
	
	@Override
	public Set<Skill> findSkillsByRoom(Room room) {
		Set<Skill> set = new HashSet<>();
		
		Session ses = sf.getCurrentSession();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<SkillRoomJT> query = builder.createQuery(SkillRoomJT.class);
		
		Root<SkillRoomJT> root = query.from(SkillRoomJT.class);
		
		query.select(root).where(builder.equal(root.get("room"), room));
		
		Query<SkillRoomJT> sr = ses.createQuery(query);
		
		try {
			
			List<SkillRoomJT> list = sr.getResultList();
			
			for (int i = 0; i < list.size(); i++) {
				
				SkillRoomJT srJT = list.get(i);
				Skill skill = srJT.getSkill();
				
				set.add(skill);
			}
			
			return set;
			
		} catch (javax.persistence.NoResultException e) {
			
			return Collections.emptySet();
			
		}
		
	}
	
	@Override
	public void update(SkillRoomJT sr) {
		
		Session ses = sf.getCurrentSession();
		ses.update(sr);
		
	}

	@Override
	public void delete(SkillRoomJT sr) {
		
		Session ses = sf.getCurrentSession();
		ses.delete(sr);
		
	}

}
