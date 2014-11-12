package com.coo.m.game.color;

import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;

import com.coo.m.game.GameProperty;
import com.coo.m.game.GplusActivity;
import com.coo.m.game.GplusManager;
import com.coo.m.game.IGame;
import com.coo.m.game.IGamePolicy;
import com.coo.m.game.R;
import com.kingstar.ngbf.ms.util.Reference;

public class ColorActivity extends GplusActivity {

	private RelativeLayout container;
	private ColorViewGroup2 cvg;

	@Override
	public GameProperty getGameProperty() {
		return GplusManager.G_COLOR;
	}

	@Override
	public int getResViewLayoutId() {
		return R.layout.color_activity;
	}

	@Override
	@Reference(override = GplusActivity.class)
	public IGamePolicy getGamePolicy() {
		return new ColorPolicy();
	}

	@Override
	public void loadContent() {
		container = (RelativeLayout) findViewById(R.id.rl_color_container);

		// 加载即启动游戏
		notify(IGame.GAME_INIT);
	}

	/**
	 * 根据当前的state的关卡状态,进行游戏界面的刷新
	 */
	@Override
	@Reference(override = GplusActivity.class)
	public void refreshUI() {
		// NOTE:RelativeLayout支持Remove之后在添加,LinearLayout不支持这样....
		container.removeAllViews();

		// 获得关卡参数,参见ColorPolicy
		int params[] = getCurrentPolicyParams();
		// toast("当前参数:" + params[0] + "-" + params[1] + "-" +
		// params[2]);
		cvg = new ColorViewGroup2(this, params[0], params[2]);
		
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

		container.addView(cvg, lp);
	}
}
