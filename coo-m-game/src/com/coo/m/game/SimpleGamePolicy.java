package com.coo.m.game;

public class SimpleGamePolicy implements IGamePolicy{

	@Override
	public int[] params(int pass) {
		return new int[0];
	}

	@Override
	public int score(int pass) {
		return 100;
	}
}
