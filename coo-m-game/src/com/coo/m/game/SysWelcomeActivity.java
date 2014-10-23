package com.coo.m.game;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.coo.m.game.g2048.G2048Activity;

/**
 * 欢迎界面
 * 
 * @since 0.1.0.0
 * @author ming.wang
 */
public class SysWelcomeActivity extends Activity implements AnimationListener {
	/**
	 * 背景图片
	 */
	private ImageView imageView;
	/**
	 * 版本,没有用到
	 */
	@SuppressWarnings("unused")
	private TextView copyright;

	private Animation alphaAnimation = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 全屏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// title不显示
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.sys_welcome_activity);

		// 加载自界面内容
		loadContent();
	}

	@SuppressLint("NewApi")
	public void loadContent() {
		Display mDisplay = this.getWindowManager().getDefaultDisplay();
		Point size = new Point();
		mDisplay.getSize(size);
		imageView = (ImageView) findViewById(R.id.iv_welcome_anim);
		imageView.setAdjustViewBounds(true);
		imageView.setScaleType(ImageView.ScaleType.FIT_XY);
		imageView.setMaxHeight(size.x);
		imageView.setMaxWidth(size.y);

		alphaAnimation = AnimationUtils
				.loadAnimation(this, R.anim.welcome_anim);
		alphaAnimation.setFillEnabled(true);
		alphaAnimation.setFillAfter(true);
		imageView.setAnimation(alphaAnimation);

		// copyright = (TextView) findViewById(R.id.copyright);
		// copyright.setAnimation(alphaAnimation);
		alphaAnimation.setAnimationListener(this);
	}

	@Override
	public void onAnimationEnd(Animation arg0) {
		// Intent intent = new Intent(SysWelcomeActivity.this,
		// SysMainActivity.class);
		Intent intent = new Intent(SysWelcomeActivity.this, G2048Activity.class);
		startActivity(intent);
		this.finish();
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
	}
}
