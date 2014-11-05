package com.coo.m.game.circular;

import com.coo.m.game.IGamePolicy;

public class CircularPolicy implements IGamePolicy {

	/**
	 * 数组说明:p[0]:块数;p[1]:时间(秒):颜色
	 */
	@Override
	public int[] params(int pass) {
		int druration = (int)(5000/pass);
		return new int[] { druration };
	}

	@Override
	public int score(int pass) {
		// 任意关卡得分都为100分
		return 100;
	}
}
