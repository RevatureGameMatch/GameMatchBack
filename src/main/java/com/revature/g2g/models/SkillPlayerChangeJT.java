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
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@Entity
@Table(name = "G2G_SKILL_PLAYER_CHANGE_JT")
@Getter @Setter @EqualsAndHashCode @ToString
public class SkillPlayerChangeJT implements Serializable{
	private static final long serialVersionUID = -253737794481944410L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "skill_player_change_jt")
	private long skillPlayerChangeJTId;
	
	@NotNull(message = "SkillPlayerChangeJT requires a SkillPlayerJT")
	@ManyToOne()
	@JoinColumn(name = "skill_id")
	private SkillPlayerJT skillPlayerJT;
	
	@NotNull(message = "SkillPlayerChangeJT requires a Changed Player.")
	@ManyToOne
	@JoinColumn(name = "player_id")
	private Player player;
	
	@NotNull(message = "SkillPlayerChangeJT requires a Modified Player")
	@ManyToOne
	@JoinColumn(name = "modified_by_id")
	private Player modifiedBy;
	
	@Column(name = "modified_by_expertise")
	private float expertise;
	
	@NotNull(message = "SkillPlayerChangeJT requires a Room.")
	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room room;
	
	@Column(name = "value")
	private float value;

	public SkillPlayerChangeJT() {
		super();
		this.skillPlayerJT = new SkillPlayerJT();
		this.player = new Player();
		this.modifiedBy = new Player();
		this.room = new Room();
	}
	public SkillPlayerChangeJT(int skillPlayerChangeJTId, SkillPlayerJT skillPlayerJT, Player player, Player modifiedBy,
			float expertise, Room room, float value) {
		super();
		this.skillPlayerChangeJTId = skillPlayerChangeJTId;
		this.skillPlayerJT = skillPlayerJT;
		this.player = player;
		this.modifiedBy = modifiedBy;
		this.expertise = expertise;
		this.room = room;
		this.value = value;
	}
	public SkillPlayerChangeJT(SkillPlayerChangeDTO source) {
		this();
		this.skillPlayerChangeJTId = source.getSkillPlayerChangeId();
		this.skillPlayerJT = new SkillPlayerJT(source.getSkillPlayer() );
		this.player = new Player(source.getPlayer() );
		this.modifiedBy = new Player(source.getModifiedBy() );
		this.expertise = source.getExpertise();
		this.room = new Room(source.getRoom() );
		this.value = source.getValue();
		
	}
}