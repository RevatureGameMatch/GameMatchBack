package com.revature.g2g.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "G2G_SKILL")
public class Skill implements Serializable{
	private static final long serialVersionUID = -6477932264861456053L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "skill_id")
	private int skillId;
	
	@Column(name = "skill_name")
	private String name;
	
	@ManyToOne()
	@JoinColumn(name="parent_id")
	private Skill parentSkill;

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
