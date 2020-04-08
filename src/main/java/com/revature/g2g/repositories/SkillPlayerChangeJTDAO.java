package com.revature.g2g.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
