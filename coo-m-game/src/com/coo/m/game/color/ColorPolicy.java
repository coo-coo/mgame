package com.coo.m.game.color;

import android.graphics.Color;

import com.coo.m.game.IGamePolicy;

public class ColorPolicy implements IGamePolicy {

	/**
	 * 数组说明:p[0]:块数;p[1]:时间(秒):颜色
	 */
	@Override
	public int[] params(int pass) {
		int num = pass + 1;
		int time = pass + 5; // 单位秒
		int color = getColor(pass);
		return new int[] { num, time, color };
		// if (0 < pass && pass < 5) {
		// return new int[] { 2, 5, color };
		// } else if (5 <= pass && pass < 10) {
		// return new int[] { 4, 10, color };
		// } else if (10 <= pass && pass < 15) {
		// return new int[] { 8, 10, color };
		// } else if (15 <= pass && pass < 20) {
		// return new int[] { 12, 15, color };
		// } else {
		// return new int[] { 40, 1, color };
		// }
	}

	@Override
	public int score(int pass) {
		// 任意关卡得分都为100分
		return 100;
	}

	private int getColor(int pass) {
		int k = pass % COLORS.length;
		return Color.parseColor(COLORS[k]);
	}

	private static final String[] COLORS = { "#FFFFB3", "#008885",
			"#75FFC2", "#8FDEFF", "#B3B3FF", "#042D42", "#0B84C2",
			"#075075", "#57BEF2", "#326E8C", "#CFF8F6", "#94D4D4",
			"#88B4BB", "#76AEAF", "#2A6D82", "#C12552", "#FF6600",
			"#F5C700", "#6A961F", "#008885" };
}
