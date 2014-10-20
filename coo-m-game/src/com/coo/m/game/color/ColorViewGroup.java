package com.coo.m.game.color;

import java.util.Random;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * /** 手势自定义布局
 * 
 * @since 0.1.0.0
 * @author ming.wang
 */

public class ColorViewGroup extends RelativeLayout {

	/**
	 * 每个边上的GestureLockView的个数
	 */
	private int count = 4;
	/**
	 * GestureLockView无手指触摸的状态下内圆的颜色
	 */
	private String color = "#ffffff";
	
	
	/**
	 * 保存所有的GestureLockView
	 */
	private ColorView[] mColorViews;
	
	
	
	/**
	 * 保存用户选中的GestureLockView的id
	 */
	private int mChoose = 1;
	
	/**
	 * 每个GestureLockView中间的间距 设置为：mGestureLockViewWidth * 25%
	 */
	private int mMarginBetweenLockView = 30;
	/**
	 * GestureLockView的边长 4 * mWidth / ( 5 * mCount + 1 )
	 */
	private int mColorViewWidth;

	

	/**
	 * 宽度
	 */
	private int mWidth;
	/**
	 * 高度
	 */
	private int mHeight;
	
	
	public ColorViewGroup(Context context) {
		super(context);
		mChoose = getRandom();
	}

	public ColorViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		mChoose = getRandom();
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		mWidth = MeasureSpec.getSize(widthMeasureSpec);
		mHeight = MeasureSpec.getSize(heightMeasureSpec);

		mHeight = mWidth = mWidth < mHeight ? mWidth : mHeight;

		// setMeasuredDimension(mWidth, mHeight);

		// 初始化mGestureLockViews
		if (mColorViews == null) {
			mColorViews = new ColorView[count * count];
			// 计算每个GestureLockView的宽度
			mColorViewWidth = (int) (4 * mWidth * 1.0f / (5 * count + 1));
			// 计算每个GestureLockView的间距
			mMarginBetweenLockView = (int) (mColorViewWidth * 0.25);

			for (int i = 0; i < mColorViews.length; i++) {
				// 初始化每个GestureLockView
				if (mChoose == i) {
					mColorViews[i] = new ColorView(getContext(),Color.parseColor(color)+10);
				} else {
					mColorViews[i] = new ColorView(getContext(), Color.parseColor(color));
				}
				mColorViews[i].setId(i + 1);
				// 设置参数，主要是定位GestureLockView间的位置
				RelativeLayout.LayoutParams colorParams = new RelativeLayout.LayoutParams(
						mColorViewWidth, mColorViewWidth);

				// 不是每行的第一个，则设置位置为前一个的右边
				if (i % count != 0) {
					colorParams.addRule(RelativeLayout.RIGHT_OF,
							mColorViews[i - 1].getId());
				}
				// 从第二行开始，设置为上一行同一位置View的下面
				if (i > count - 1) {
					colorParams.addRule(RelativeLayout.BELOW, mColorViews[i
							- count].getId());
				}
				// 设置右下左上的边距
				int rightMargin = mMarginBetweenLockView;
				int bottomMargin = mMarginBetweenLockView;
				int leftMagin = 0;
				int topMargin = 0;
				/**
				 * 每个View都有右外边距和底外边距 第一行的有上外边距 第一列的有左外边距
				 */
				if (i >= 0 && i < count)// 第一行
				{
					topMargin = mMarginBetweenLockView;
				}
				if (i % count == 0)// 第一列
				{
					leftMagin = mMarginBetweenLockView;
				}

				colorParams.setMargins(leftMagin, topMargin, rightMargin,
						bottomMargin);
				addView(mColorViews[i], colorParams);
			}

		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		int x = (int) event.getX();
		int y = (int) event.getY();

		switch (action) {
		case MotionEvent.ACTION_DOWN:
			ColorView child = getChildIdByPos(x, y);
			if (child != null) {
				int cId = child.getId();
				if (mChoose == (cId - 1)) {
					Toast.makeText(getContext(), "选对了", Toast.LENGTH_SHORT)
							.show();
				} else {
					Toast.makeText(getContext(), "选错了", Toast.LENGTH_SHORT)
							.show();

				}
			}
			break;
		}
		invalidate();
		return true;
	}

	private boolean checkPositionInChild(View child, int x, int y) {

		// 设置了内边距，即x,y必须落入下GestureLockView的内部中间的小区域中，可以通过调整padding使得x,y落入范围不变大，或者不设置padding
		int padding = (int) (mColorViewWidth * 0.15);

		if (x >= child.getLeft() + padding && x <= child.getRight() - padding
				&& y >= child.getTop() + padding
				&& y <= child.getBottom() - padding) {
			return true;
		}
		return false;
	}

	private ColorView getChildIdByPos(int x, int y) {
		for (ColorView mView : mColorViews) {
			if (checkPositionInChild(mView, x, y)) {
				return mView;
			}
		}

		return null;
	}

	public void setCount(int mCount) {
		this.count = mCount;
	}

	public void setColor(String mColor) {
		this.color = mColor;
	}

	public int getRandom() {
		Random random = new Random();
		int i = random.nextInt(count * count);
		return i;
	}
	
	public void refresh() {
		this.postInvalidate();
	}

}
