package com.revature.g2g.repositories;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.revature.g2g.models.Room;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillPlayerChangeJT;

@Transactional
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SkillPlayerChangeJTDAO implements ISkillPlayerChangeJTDAO{
	@Autowired
	private SessionFactory sf;

	@Override
	public void insert(SkillPlayerChangeJT spc) {
	
		Session ses = sf.getCurrentSession();
		ses.save(spc);
		
	}

	public Set<SkillPlayerChangeJT> findBy(Room room, Player modifiedBy){
		Set<SkillPlayerChangeJT> set = new HashSet<>();
		Session ses = sf.getCurrentSession();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<SkillPlayerChangeJT> query = builder.createQuery(SkillPlayerChangeJT.class);
		
		Root<SkillPlayerChangeJT> root = query.from(SkillPlayerChangeJT.class);
		Path<Object> roomPath = root.get("room");
		Path<Object> playerPath = root.get("modifiedBy");
		
		Predicate roomPredicate = builder.equal(roomPath, room);
		Predicate playerPredicate = builder.equal(playerPath, modifiedBy);
		Predicate roomAndPlayerPredciate = builder.and(roomPredicate, playerPredicate);
		
		query.select(root).where(roomAndPlayerPredciate);
		
		Query<SkillPlayerChangeJT> sg = ses.createQuery(query);
		try {
			List<SkillPlayerChangeJT> list = sg.getResultList();
			set.addAll(list);
			return set;
		} catch (javax.persistence.NoResultException e) {
			
			return Collections.emptySet();
			
		}
	}

	@Override
	public SkillPlayerChangeJT findBy(Player modifiedBy, Player player, Room room, Skill skill) {
		Session ses = sf.getCurrentSession();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<SkillPlayerChangeJT> query = builder.createQuery(SkillPlayerChangeJT.class);
		
		Root<SkillPlayerChangeJT> root = query.from(SkillPlayerChangeJT.class);
		Path<Object> modifiedPath = root.get("modifiedBy");
		Path<Object> playerPath = root.get("player");
		Path<Object> roomPath = root.get("room");
		
		Predicate modifiedPredicate = builder.equal(modifiedPath, modifiedBy);
		Predicate playerPredicate = builder.equal(playerPath, player);
		Predicate roomPredicate = builder.equal(roomPath, room);
		Predicate compositePredciate = builder.and(modifiedPredicate, playerPredicate, roomPredicate);
		
		query.select(root).where(compositePredciate);
		
		Query<SkillPlayerChangeJT> sg = ses.createQuery(query);
		try {
			List<SkillPlayerChangeJT> list = sg.getResultList();
			for (SkillPlayerChangeJT skillPlayerChangeJT : list) {
				if(skill.equals(skillPlayerChangeJT.getSkillPlayerJT().getSkill())) {
					return skillPlayerChangeJT;
				}
			}
			return null;
		} catch (javax.persistence.NoResultException e) {
			
			return null;
			
		}
	}

	@Override
	public void update(SkillPlayerChangeJT spc) {
		
		Session ses = sf.getCurrentSession();
		ses.update(spc);
		
	}

	@Override
	public void delete(SkillPlayerChangeJT spc) {
		
		Session ses = sf.getCurrentSession();
		ses.delete(spc);
		
	}

}
