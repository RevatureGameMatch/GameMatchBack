package com.revature.g2g.services.handlers;

import java.util.List;
import java.util.Optional;

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
	public void save(SkillRoomJT skillRoomJT) {
		this.repository.save(skillRoomJT);
	}
	public Optional<SkillRoomJT> findById(long id) {
		return this.repository.findById(id);
	}
	public List<SkillRoomJT> findAll(){
		return this.repository.findAll();
	}
	public List<SkillRoomJT> findBySkill(Skill skill){
		return this.repository.findBySkill(skill);
	}
	public List<SkillRoomJT> findByRoom(Room room){
		return this.repository.findByRoom(room);
	}
	public List<Skill> findSkillsByRoom(Room room){
		return this.repository.findSkillsByRoom(room);
	}
	public void delete(SkillRoomJT sr) {
		this.repository.delete(sr);
	}
}