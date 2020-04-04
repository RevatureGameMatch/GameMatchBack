package com.revature.g2g;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.g2g.models.Game;
import com.revature.g2g.repositories.GameDAO;

public class SpringDriver {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		Game game = (Game) ac.getBean("game");
		game.setDescription("A basic description of some game");
		game.setName("Fake Game 201");
		Game game2 = (Game) ac.getBean("game");
		game2.setDescription("A basic description of some game");
		game2.setName("Fake Game 301");
		System.out.println(game);
		System.out.println(game2);
	}
}
