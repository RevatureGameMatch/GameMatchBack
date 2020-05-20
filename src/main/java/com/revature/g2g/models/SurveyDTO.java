package com.revature.g2g.models;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class SurveyDTO {
	private List<PlayerDTO> players;
	private List<SkillDTO> skills;
	private RoomDTO room;

}