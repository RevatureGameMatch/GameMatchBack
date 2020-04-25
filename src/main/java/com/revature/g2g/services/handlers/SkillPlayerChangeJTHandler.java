package com.revature.g2g.services.handlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.Room;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillPlayerChangeJT;
import com.revature.g2g.models.SkillPlayerJT;
import com.revature.g2g.repositories.ISkillPlayerChangeJTDAO;
import com.revature.g2g.repositories.ISkillPlayerJTDAO;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SkillPlayerChangeJTHandler {
	private ISkillPlayerChangeJTDAO repository;
	private ISkillPlayerJTDAO skillDao;
	@Autowired
	public SkillPlayerChangeJTHandler(ISkillPlayerChangeJTDAO repository, ISkillPlayerJTDAO skillDao) {
		super();
		this.repository = repository;
		this.skillDao = skillDao;
	}
	public void save(SkillPlayerChangeJT spc) {
		this.repository.save(spc);
	}
	public SkillPlayerChangeJT findBy(Player modifiedBy, Player player, Room room, Skill skill) {
		SkillPlayerJT skillPlayerJT = skillDao.findBySkillAndPlayer(skill, player);
		return this.repository.findByModifiedByAndPlayerAndRoomAndSkillPlayerJT(modifiedBy, player, room, skillPlayerJT);
	}
	public List<SkillPlayerChangeJT> findBy(Room room, Player modifiedBy){
		return this.repository.findByRoomAndPlayer(room, modifiedBy);
	}
	public void delete(SkillPlayerChangeJT spc) {
		this.repository.delete(spc);
	}
}
