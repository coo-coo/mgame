package com.coo.m.game.g2048;

import com.coo.m.game.IGamePolicy;

public class G2048Policy implements IGamePolicy {

	@Override
	public int[] params(int pass) {
		return new int[0];
	}

	@Override
	public int score(int pass) {
		return 1000;
	}

	
}
