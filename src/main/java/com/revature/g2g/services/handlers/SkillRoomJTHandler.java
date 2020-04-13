package com.revature.g2g.services.handlers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.revature.g2g.models.Room;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillRoomJT;
import com.revature.g2g.repositories.ISkillRoomJTDAO;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SkillRoomJTHandler {
	private ISkillRoomJTDAO repository;
	public SkillRoomJTHandler() {
		super();
	}
	@Autowired
	public SkillRoomJTHandler(ISkillRoomJTDAO repository) {
		super();
		this.repository = repository;
	}
	public void insert(SkillRoomJT sr) {
		this.repository.insert(sr);
	}
	public SkillRoomJT findById(int id) {
		return this.repository.findById(id);
	}
	public Set<SkillRoomJT> findAll(){
		return this.repository.findAll();
	}
	public Set<SkillRoomJT> findBySkill(Skill skill){
		return this.repository.findBySkill(skill);
	}
	public Set<SkillRoomJT> findByRoom(Room room){
		return this.repository.findByRoom(room);
	}
	public Set<Skill> findSkillsByRoom(Room room){
		return this.repository.findSkillsByRoom(room);
	}
	public void update(SkillRoomJT sr) {
		this.repository.update(sr);
	}
	public void delete(SkillRoomJT sr) {
		this.repository.delete(sr);
	}
}