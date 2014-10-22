package com.coo.m.game.color;

import java.util.Random;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.coo.m.game.GplusActivity;
import com.coo.m.game.IGame;

/**
 * /** 手势自定义布局
 * 
 * @since 0.1.0.0
 * @author ming.wang
 */

public class ColorViewGroup2 extends RelativeLayout implements OnClickListener {

	/**
	 * 每个边上的View个数
	 */
	private int count = 4;
	/**
	 * View颜色
	 */
	private int color;
	/**
	 * 保存View
	 */
	private ColorView2[] views;
	// 指定那个不一样的序号
	private int random = 0;

	private GplusActivity activity = null;

	public ColorViewGroup2(Context context) {
		super(context);
	}

	/**
	 * 构造函数指定列数和颜色值
	 */
	public ColorViewGroup2(GplusActivity activity, int count, int color) {
		super(activity);
		this.activity = activity;
		this.count = count;
		this.color = color;
		// 设定不一样的那个序号
		this.random = getRandom(count);
		this.views = new ColorView2[count * count];
	}

	/**
	 * 设置View间距
	 */
	private int margin = 1;

	@SuppressLint("DrawAllocation")
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		// width ==500dp
		int width = MeasureSpec.getSize(widthMeasureSpec);
		setMeasuredDimension(width, width);

		int marginSum = (count - 1) * margin;
		// int marginSum = 0;
		int viewSize = (int) ((width - marginSum) / count);

		for (int i = 0; i < views.length; i++) {
			// toast("" + i + "--length==" + views.length + "-"
			// + viewSize);
			int iColor = color;
			if (i == random) {
				// TODO 颜色算法
				iColor = Color.RED;
			}
			ColorView2 cv = new ColorView2(getContext(), iColor, i);
			// 设置ID
			cv.setId(1000 + i);
			cv.setOnClickListener(this);
			views[i] = cv;

			// 设置参数，主要是定位GestureLockView间的位置
			RelativeLayout.LayoutParams viewLayoutParams = new RelativeLayout.LayoutParams(
					viewSize, viewSize);

			// 设置相对位置
			// 不是每行的第一个，则设置位置为前一个的右边
			if (i % count != 0) {
				viewLayoutParams.addRule(
						RelativeLayout.RIGHT_OF,
						views[i - 1].getId());
			}
			// 从第二行开始，设置为上一行同一位置View的下面
			if (i > count - 1) {
				viewLayoutParams.addRule(RelativeLayout.BELOW,
						views[i - count].getId());
			}

			// 每个View都缺省有左边距和上外边,没有右边距和下边距
			int leftMargin = margin;
			int topMargin = margin;
			int rightMargin = 0;
			int bottomMargin = 0;
			if (i < count) {
				// 第一行
				topMargin = 0;
			}
			if (i % count == 0) {
				// 第一列
				leftMargin = 0;
			}

			viewLayoutParams.setMargins(leftMargin, topMargin,
					rightMargin, bottomMargin);
			addView(cv, viewLayoutParams);
		}
	}

	@Override
	public void onClick(View view) {
		if (view instanceof ColorView2) {
			ColorView2 cv = (ColorView2) view;
			// toast("次序="+cv.getSequecne());
			if (cv.getSequecne() == this.random) {
				// 发送成功
				activity.notify(IGame.MISSION_SUCCESS);
			} else {
				activity.notify(IGame.MISSION_FAIL);
			}
		}
	}

	private int getRandom(int count) {
		Random random = new Random();
		return random.nextInt(count * count);
	}

	@SuppressWarnings("unused")
	private void toast(String message) {
		Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT)
				.show();
	}

}
