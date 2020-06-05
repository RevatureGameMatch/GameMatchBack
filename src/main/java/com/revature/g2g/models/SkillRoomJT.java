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
@Table(name = "G2G_SKILL_ROOM_JT")
@Getter @Setter @EqualsAndHashCode @ToString
public class SkillRoomJT implements Serializable{
	private static final long serialVersionUID = 3217583926494781567L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "skill_room_jt_id")
	private long skillRoomJTId;
	
	@NotNull(message = "SkillRoomJT requires a skill.")
	@ManyToOne()
	@JoinColumn(name = "skill_id")
	private Skill skill;
	
	@NotNull(message = "Room requires a room.")
	@ManyToOne()
	@JoinColumn(name = "room_id")
	private Room room;
	
	@Column(name = "min_value")
	private double minValue;

	public SkillRoomJT() {
		super();
		this.skill = new Skill();
		this.room = new Room();
	}
	
	public SkillRoomJT(int skillRoomJTId, Skill skill, Room room, double minValue) {
		super();
		this.skillRoomJTId = skillRoomJTId;
		this.skill = skill;
		this.room = room;
		this.minValue = minValue;
	}
	
	public SkillRoomJT(SkillRoomDTO source) {
		this();
		this.skillRoomJTId = source.getSkillRoomId();
		this.skill = new Skill(source.getSkill() );
		this.room = new Room(source.getRoom() );
		this.minValue = source.getMinValue();
	}
}