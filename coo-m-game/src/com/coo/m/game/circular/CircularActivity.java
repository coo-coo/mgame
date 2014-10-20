package com.coo.m.game.circular;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.coo.m.game.GameProperty;
import com.coo.m.game.GplusActivity;
import com.coo.m.game.GplusManager;
import com.coo.m.game.R;

/**
 * The Class CircularProgressBarSample.
 */
public class CircularActivity extends GplusActivity {

	private Button btnstart;
	private Button btnnum;

	private HoloCircularProgressBar pb;

	private ObjectAnimator mProgressBarAnimator;

	private float ap = 0.7f;
	private int times = 0;

	@Override
	public void loadContent() {
		pb = (HoloCircularProgressBar) findViewById(R.id.holoCircularProgressBar);
		pb.setProgress(ap);
		pb.setMarkerProgress(0.3f);
		btnstart = (Button) findViewById(R.id.btn_circular_start);
		btnnum = (Button) findViewById(R.id.btn_circular_num);
		btnstart.setOnClickListener(this);
	}

	// /*
	// * (non-Javadoc)
	// *
	// * @see android.app.Activity#onCreate(android.os.Bundle)
	// */
	// @Override
	// protected void onCreate(final Bundle savedInstanceState) {
	//
	// super.onCreate(savedInstanceState);
	// setContentView(R.layout.circular_activity);
	//
	// pb = (HoloCircularProgressBar)
	// findViewById(R.id.holoCircularProgressBar);
	// pb.setProgress(ap);
	// pb.setMarkerProgress(0.3f);
	// btnstart = (Button) findViewById(R.id.btn_circular_start);
	// btnnum = (Button) findViewById(R.id.btn_circular_num);
	// btnstart.setOnClickListener(this);
	// }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_circular_start:
			start();
			break;
		}
	}

	private void start() {
		if (mProgressBarAnimator != null) {
			mProgressBarAnimator.cancel();
		}

		final float progress = (float) (Math.random() * 1);
		Toast.makeText(this, progress + "//" + ap, Toast.LENGTH_SHORT)
				.show();

		times = (int) (Math.abs(progress * 10 - ap * 10));
		Toast.makeText(this, times + "", Toast.LENGTH_SHORT).show();
		if (times >= 10) {
			times = 10;
		}
		if (times <= 2) {
			times = 2;
		}
		btnnum.setText(times + "");

		int duration = 2000;
		pb.setTap(0);
		animate(pb, null, progress, duration);
	}

	private void animate(final HoloCircularProgressBar progressBar,
			final AnimatorListener listener, final float progress,
			final int duration) {

		mProgressBarAnimator = ObjectAnimator.ofFloat(progressBar,
				"progress", progress);
		mProgressBarAnimator.setDuration(duration);

		mProgressBarAnimator.addListener(new AnimatorListener() {

			@Override
			public void onAnimationCancel(final Animator animation) {
				animation.end();
			}

			@Override
			public void onAnimationEnd(final Animator animation) {
				progressBar.setProgress(progress);
				ap = progress;
				if (times == pb.getTap()) {
					btnnum.setText("正确");
				} else {
					btnnum.setText("错误，输入了" + pb.getTap());
				}
				pb.setTap(0);
			}

			@Override
			public void onAnimationRepeat(final Animator animation) {
			}

			@Override
			public void onAnimationStart(final Animator animation) {
			}
		});
		if (listener != null) {
			mProgressBarAnimator.addListener(listener);
		}
		mProgressBarAnimator.reverse();
		mProgressBarAnimator
				.addUpdateListener(new AnimatorUpdateListener() {

					@Override
					public void onAnimationUpdate(
							final ValueAnimator animation) {
						progressBar.setProgress((Float) animation
								.getAnimatedValue());
					}
				});
		progressBar.setMarkerProgress(progress);
		mProgressBarAnimator.start();
	}

	@Override
	public GameProperty getGameProperty() {
		return GplusManager.G_CIRCULAR;
	}

	@Override
	public int getResViewLayoutId() {
		return R.layout.circular_activity;
	}

}
