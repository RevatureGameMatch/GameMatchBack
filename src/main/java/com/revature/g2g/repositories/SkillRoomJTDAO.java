package com.revature.g2g.repositories;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.g2g.models.Room;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillRoomJT;
import com.revature.g2g.services.helpers.HibernateUtil;

@Transactional
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SkillRoomJTDAO implements ISkillRoomJTDAO {
	@Autowired
	private SessionFactory sf;

	@Override
	public void insert(SkillRoomJT sr) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.save(sr);
		
		tx.commit();
		HibernateUtil.closeSession();
	}

	@Override
	public SkillRoomJT findById(int id) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		SkillRoomJT sr = ses.get(SkillRoomJT.class, id);
		
		tx.commit();
		HibernateUtil.closeSession();
		
		return sr;
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
		Set<SkillRoomJT> set = null;
		
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<SkillRoomJT> query = builder.createQuery(SkillRoomJT.class);
		
		Query<SkillRoomJT> sr = ses.createQuery(query);
		
		set = sr.getResultStream()
				.collect(Collectors.toSet());
		
		tx.commit();
		HibernateUtil.closeSession();
		
		return set;
	}

	@Override
	public Set<SkillRoomJT> findBySkill(Skill skill) {
		Set<SkillRoomJT> set = null;
		
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<SkillRoomJT> query = builder.createQuery(SkillRoomJT.class);
		
		Root<SkillRoomJT> root = query.from(SkillRoomJT.class);
		
		query.select(root).where(builder.equal(root.get("skill"), skill));
		
		Query<SkillRoomJT> sr = ses.createQuery(query);
		
		set = sr.getResultStream()
				.collect(Collectors.toSet());
		
		tx.commit();
		HibernateUtil.closeSession();
		
		return set;
	}

	@Override
	public Set<SkillRoomJT> findByRoom(Room room) {
		Set<SkillRoomJT> set = null;
		
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<SkillRoomJT> query = builder.createQuery(SkillRoomJT.class);
		
		Root<SkillRoomJT> root = query.from(SkillRoomJT.class);
		
		query.select(root).where(builder.equal(root.get("room"), room));
		
		Query<SkillRoomJT> sr = ses.createQuery(query);
		
		set = sr.getResultStream()
				.collect(Collectors.toSet());
		
		tx.commit();
		HibernateUtil.closeSession();
		
		return set;
	}

	@Override
	public void update(SkillRoomJT sr) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.update(sr);
		
		tx.commit();
		HibernateUtil.closeSession();
	}

	@Override
	public void delete(SkillRoomJT sr) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.delete(sr);
		
		tx.commit();
		HibernateUtil.closeSession();
	}

}
