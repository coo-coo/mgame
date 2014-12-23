package com.coo.m.game.color;

import android.graphics.Color;

import com.coo.m.game.SimpleGamePolicy;

public class ColorPolicy extends SimpleGamePolicy {

	/**
	 * 数组说明:p[0]:块数;p[1]:时间(秒):颜色
	 */
	@Override
	public int[] params(int pass) {
		int num = (int)(pass /4+ 3);
		int time = pass + 5; // 单位秒
		int color = getColor(pass);
		return new int[] { num, time, color };
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
