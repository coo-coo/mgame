package com.coo.m.game.color;

import android.graphics.Color;

import com.coo.m.game.SimpleGamePolicy;

public class BlockPolicy extends SimpleGamePolicy {

	/**
	 * 数组说明:p[0]:块数;p[1]:时间(秒):颜色
	 */
	@Override
	public int[] params(int pass) {
		int num = pass + 3;
		int time = pass + 5; // 单位秒
		int color = getColor();
		return new int[] { num, time, color };
	}

	private int getColor() {
		return Color.parseColor("#FF6600");
	}

}
