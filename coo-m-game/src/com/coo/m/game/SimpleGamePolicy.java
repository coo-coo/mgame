package com.coo.m.game;

/**
 * 简单游戏规则,有些模块不一定是游戏,返回简单策略
 * @author boqing.shen
 * @since 1.0
 *
 */
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
