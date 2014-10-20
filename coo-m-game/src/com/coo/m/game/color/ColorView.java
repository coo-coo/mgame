package com.coo.m.game.color;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.View;

/**
 * 手势中的每个圆
 * 
 * @since 0.1.0.0
 * @author ming.wang
 */
@SuppressLint("ViewConstructor")
public class ColorView extends View {

	/**
	 * 宽度
	 */
	private int mWidth;
	/**
	 * 高度
	 */
	private int mHeight;
	/**
	 * 外圆半径
	 */
	private int mRadius;
	/**
	 * 画笔的宽度
	 */
	private int mStrokeWidth = 2;

	/**
	 * 圆心坐标
	 */
	private int mCenterX;
	private int mCenterY;
	private Paint mPaint;

	private int mColor;

	public ColorView(Context context, int color) {
		super(context);
		this.mColor = color;
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		mWidth = MeasureSpec.getSize(widthMeasureSpec);
		mHeight = MeasureSpec.getSize(heightMeasureSpec);

		// 取长和宽中的小值
		mWidth = mWidth < mHeight ? mWidth : mHeight;
		mRadius = mCenterX = mCenterY = mWidth / 2;
		mRadius -= mStrokeWidth / 2;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// 绘制外圆
		mPaint.setColor(mColor);
		// mPaint.setColor(Color.parseColor(mColor));
		mPaint.setStrokeWidth(2);
		mPaint.setStyle(Style.FILL_AND_STROKE);
		canvas.drawCircle(mCenterX, mCenterY, mRadius, mPaint);
	}

}
