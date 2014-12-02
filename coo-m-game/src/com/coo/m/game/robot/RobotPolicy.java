package com.coo.m.game.robot;

import com.coo.m.game.IGamePolicy;

public class RobotPolicy implements IGamePolicy {

	@Override
	public int[] params(int pass) {
		return new int[0];
	}

	@Override
	public int score(int pass) {
		return 1000;
	}

	
}
