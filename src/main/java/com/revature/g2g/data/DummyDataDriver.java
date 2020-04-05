package com.revature.g2g.data;

import com.revature.g2g.data.generators.GameGenerator;

public class DummyDataDriver {
	public static void main(String[] args) {
//		new PlayerGenerator().generate();
//		new RoomGenerator().generate();
//		new PlayerRoomJTGenerator().generate();
		new GameGenerator().generate();
	}
}
