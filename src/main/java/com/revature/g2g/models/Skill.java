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
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.g2g.services.handlers.SkillHandler;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@JsonIgnoreProperties(value = { "childSkills", "games", "players", "rooms" })
@Entity
@Table(name = "G2G_SKILL")
@Getter @Setter @EqualsAndHashCode(exclude = { "childSkills", "games", "players", "rooms" }) @ToString(exclude = { "childSkills", "games", "players", "rooms" })
public class Skill implements Serializable{
	private static final long serialVersionUID = -6477932264861456053L;
	@Autowired
	private transient SkillHandler skillHandler;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "skill_id")
	private long skillId;
	
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

	public Skill(SkillDTO source) {
		this();
		this.skillId = source.getSkillId();
		this.name = source.getName();
		if (this.parentSkill != null) {
			this.parentSkill = skillHandler.findByName(source.getParentSkill().getName() );
		}
	}
	
}
