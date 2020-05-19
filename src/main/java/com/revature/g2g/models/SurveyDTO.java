package com.revature.g2g.models;

import java.util.List;

import com.revature.g2g.api.templates.SurveySkillTemplate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class SurveyDTO {
	private List<PlayerDTO> players;
	private List<SkillDTO> skills;
	private RoomDTO room;

	public SurveyDTO(PlayerDTO player, SurveySkillTemplate[] array) {
		super();
		this.players.add(player);
		// TODO: SurveySkillTemplate is depreciated, will become SurveySkillDTO
		for (SurveySkillTemplate sst: array) {
			this.skills.add(new SkillDTO(sst.getSkill()) );
		}
		// TODO: figure out how room is assigned
	}
}