package com.revature.g2g.models;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class SurveyDTO {
	private List<Player> players;
	private List<Skill> skills;
	private Room room;
}