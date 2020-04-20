package com.revature.g2g.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
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
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Component
@JsonIgnoreProperties(value = { "childSkills", "games", "players", "rooms" })
@Entity
@Table(name = "G3G_SKILL")
public class Skill implements Serializable{
	private static final long serialVersionUID = -6477932264861456053L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "skill_id")
	private int skillId;
	
	@NotNull(message="Skill requires name.")
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
		this.name = "Another skill with no name";
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
		return Objects.hash(name, parentSkill, skillId);
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
		return Objects.equals(name, other.name) && Objects.equals(parentSkill, other.parentSkill)
				&& skillId == other.skillId;
	}

	@Override
	public String toString() {
		return "Skill [skillId=" + skillId + ", name=" + name + "]";
	}
}
