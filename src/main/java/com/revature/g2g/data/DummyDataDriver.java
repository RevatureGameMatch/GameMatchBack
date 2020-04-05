package com.revature.g2g.data;

import com.revature.g2g.data.generators.PlayerGenerator;
import com.revature.g2g.data.generators.PlayerRoomJTGenerator;
import com.revature.g2g.data.generators.RoomGenerator;

public class DummyDataDriver {
	public static void main(String[] args) {
		new PlayerGenerator().generate();
		new RoomGenerator().generate();
		new PlayerRoomJTGenerator().generate();
	}
}
