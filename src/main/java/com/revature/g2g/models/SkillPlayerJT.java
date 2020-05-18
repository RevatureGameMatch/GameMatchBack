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

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.g2g.services.helpers.BalanceConstants;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@JsonIgnoreProperties(value = { "changes" })
@Entity
@Table(name = "G3G_SKILL_PLAYER_JT")
@Getter @Setter @EqualsAndHashCode(exclude = {"changes"}) @ToString(exclude = {"changes"})
public class SkillPlayerJT implements Serializable{
	private static final long serialVersionUID = 9019617057828732231L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "skill_player_jt_id")
	private long skillPlayerJtId;
	
	@NotNull(message = "SkillPlayerJT requires a skill")
	@ManyToOne()
	@JoinColumn(name = "skill_id")
	private Skill skill;
	
	@NotNull(message = "SkillPlayerJT requires a player")
	@ManyToOne()
	@JoinColumn(name = "player_id")
	private Player player;
	
	@NotNull(message = "SkillPlayerJT requires a value")
	@Column(name = "player_skill_value")
	private double value;
	
	@Column(name = "player_skill_exertise")
	private double expertise;

	@OneToMany(mappedBy = "skillPlayerJT", fetch = FetchType.LAZY)
	private Set<SkillPlayerChangeJT> changes = new HashSet<>();

	public SkillPlayerJT() {
		super();
		this.skill = new Skill();
		this.player = new Player();
		this.value = BalanceConstants.getStartValue();
	}
	
	public SkillPlayerJT(long skillPlayerJtId, Skill skill, Player player, double value, double expertise) {
		super();
		this.skillPlayerJtId = skillPlayerJtId;
		this.skill = skill;
		this.player = player;
		this.value = value;
		this.expertise = expertise;
	}
	
	public SkillPlayerJT(SkillPlayerDTO source) {
		this();
		this.skillPlayerJtId = source.getSkillPlayerId();
		this.skill = new Skill(source.getSkill() );
		this.player = new Player(source.getPlayer() );
		this.value = source.getValue();
		this.expertise = source.getExpertise();
	}
}