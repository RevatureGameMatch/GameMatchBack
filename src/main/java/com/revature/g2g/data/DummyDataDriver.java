package com.revature.g2g.data;

import com.revature.g2g.data.generator.PlayerGenerator;
import com.revature.g2g.data.generator.PlayerRoomJTGenerator;
import com.revature.g2g.data.generator.RoomGenerator;

public class DummyDataDriver {
	public static void main(String[] args) {
		new PlayerGenerator().generate();
		new RoomGenerator().generate();
		new PlayerRoomJTGenerator().generate();
	}
}
