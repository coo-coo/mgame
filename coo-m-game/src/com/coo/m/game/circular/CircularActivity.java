package com.coo.m.game.circular;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

import com.coo.m.game.GameProperty;
import com.coo.m.game.GplusActivity;
import com.coo.m.game.GplusManager;
import com.coo.m.game.IGame;
import com.coo.m.game.IGamePolicy;
import com.coo.m.game.R;
import com.kingstar.ngbf.ms.util.Reference;
import com.kingstar.ngbf.ms.util.android.CommonBizActivity;

/**
 * 点几下...
 */
public class CircularActivity extends GplusActivity {

	private HoloCircularProgressBar progressBar;
	private ObjectAnimator progressAnimator;
	// private RelativeLayout container;

	// 定义点击次数
	private final static int TIMES = 4;

	private AnimatorListener listener;

	@Override
	@Reference(override = CommonBizActivity.class)
	public void loadContent() {
		progressBar = (HoloCircularProgressBar) findViewById(R.id.holoCircularProgressBar);
		progressBar.setProgress(0.7f);
		progressBar.setMarkerProgress(0.3f);

		// 定义监听器
		listener = new CircularAnimatorListener(this);

		// 加载即启动游戏
		notify(IGame.GAME_INIT);
	}

	@Override
	@Reference(override = GplusActivity.class)
	public void refreshUI() {
		if (progressAnimator != null) {
			progressAnimator.cancel();
		}

		// 获得随机位置
		float targetProgress = (float) (Math.random() * 1);
		progressBar.reset();

		// 时间间隔 随关卡降低,参见CircularPolicy
		int params[] = getCurrentPolicyParams();
		int duration = params[0];
		// int duration = 2000;
		animate(progressBar, listener, targetProgress, duration);
	}

	/**
	 * 动画结束之后进行判定...
	 */
	public void onAnimationEnd() {
		if (progressBar.getTap() == TIMES) {
			toast("恭喜过关!");
			notify(IGame.MISSION_SUCCESS);
		} else {
			notify(IGame.MISSION_FAIL);
		}
	}

	/**
	 * 动画执行...
	 */
	private void animate(final HoloCircularProgressBar progressBar,
			AnimatorListener listener, float progress, int duration) {
		progressAnimator = ObjectAnimator.ofFloat(progressBar,
				"progress", progress);
		progressAnimator.setDuration(duration);
		if (listener != null) {
			progressAnimator.addListener(listener);
		}
		progressAnimator.reverse();
		progressAnimator.addUpdateListener(new AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(
					final ValueAnimator animation) {
				progressBar.setProgress((Float) animation
						.getAnimatedValue());
			}
		});
		progressBar.setMarkerProgress(progress);
		progressAnimator.start();
	}

	@Override
	@Reference(override = CommonBizActivity.class)
	public int getResViewLayoutId() {
		return R.layout.circular_activity;
	}

	@Override
	@Reference(override = GplusActivity.class)
	public GameProperty getGameProperty() {
		return GplusManager.G_CIRCULAR;
	}

	@Override
	@Reference(override = GplusActivity.class)
	public IGamePolicy getGamePolicy() {
		return new CircularPolicy();
	}
}

/**
 * 动画监听器...
 * 
 * @author boqing.shen
 * 
 */
class CircularAnimatorListener implements AnimatorListener {

	private CircularActivity activity;

	public CircularAnimatorListener(CircularActivity activity) {
		this.activity = activity;
	}

	@Override
	public void onAnimationCancel(Animator animation) {
		animation.end();
	}

	@Override
	public void onAnimationEnd(Animator animation) {
		activity.onAnimationEnd();
	}

	@Override
	public void onAnimationRepeat(Animator animation) {
	}

	@Override
	public void onAnimationStart(Animator animation) {
	}
}