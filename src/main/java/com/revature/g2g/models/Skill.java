package com.revature.g2g.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component()
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Table(name = "G2G_SKILL")
public class Skill implements Serializable{
	private static final long serialVersionUID = -6477932264861456053L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "skill_id")
	private int skillId;
	
	@Column(name = "skill_name", unique = true)
	private String name;
	
	@ManyToOne()
	@JoinColumn(name="parent_id")
	private Skill parentSkill;
	
	@OneToMany(mappedBy = "parentSkill", fetch = FetchType.LAZY)
	private Set<Skill> childSkills = new HashSet<>();
	
	@OneToMany(mappedBy = "skill", fetch = FetchType.LAZY)
	private Set<SkillGameJT> games = new HashSet<>();

	@OneToMany(mappedBy = "skill", fetch = FetchType.LAZY)
	private Set<SkillPlayerJT> players = new HashSet<>();

	@OneToMany(mappedBy = "skill", fetch = FetchType.LAZY)
	private Set<SkillRoomJT> rooms = new HashSet<>();

	public Skill() {
		super();
	}
	public Skill(int skillId, String name, Skill parentSkill) {
		super();
		this.skillId = skillId;
		this.name = name;
		this.parentSkill = parentSkill;
	}
	public int getSkillId() {
		return skillId;
	}
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Skill getParentSkill() {
		return parentSkill;
	}
	public void setParentSkill(Skill parentSkill) {
		this.parentSkill = parentSkill;
	}
	public Set<Skill> getChildSkills() {
		return childSkills;
	}
	public void setChildSkills(Set<Skill> childSkills) {
		this.childSkills = childSkills;
	}
	public Set<SkillGameJT> getGames() {
		return games;
	}
	public void setGames(Set<SkillGameJT> games) {
		this.games = games;
	}
	public Set<SkillPlayerJT> getPlayers() {
		return players;
	}
	public void setPlayers(Set<SkillPlayerJT> players) {
		this.players = players;
	}
	public Set<SkillRoomJT> getRooms() {
		return rooms;
	}
	public void setRooms(Set<SkillRoomJT> rooms) {
		this.rooms = rooms;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + skillId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Skill)) {
			return false;
		}
		Skill other = (Skill) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (skillId != other.skillId) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		return "Skill [skillId=" + skillId + ", name=" + name + "]";
	}
}
