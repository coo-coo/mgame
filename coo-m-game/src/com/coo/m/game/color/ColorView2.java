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
public class ColorView2 extends View {

	/**
	 * 宽度|高度 相同,即正方形或者是圆形
	 */
	private int size = 250;

	private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

	private int color;

	private int sequecne = -1;

	public ColorView2(Context context, int color, int sequence) {
		super(context);
		this.color = color;
		this.sequecne = sequence;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		size = MeasureSpec.getSize(widthMeasureSpec);
		setMeasuredDimension(size, size);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// 绘制外圆
		paint.setColor(color);
		paint.setStrokeWidth(2);
		paint.setStyle(Style.FILL_AND_STROKE);
		canvas.drawRect(0, 0, size, size, paint);
	}

	public int getSequecne() {
		return sequecne;
	}

	public void setSequecne(int sequecne) {
		this.sequecne = sequecne;
	}
}
