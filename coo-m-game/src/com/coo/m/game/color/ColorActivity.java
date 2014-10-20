package com.coo.m.game.color;

import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;

import com.coo.m.game.GameProperty;
import com.coo.m.game.GplusActivity;
import com.coo.m.game.GplusManager;
import com.coo.m.game.R;

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
	public void loadContent() {
		container = (RelativeLayout) findViewById(R.id.rl_color_container);
		// 设置Policy
		this.setPolicy(new ColorPolicy());
		// 加载即启动游戏
		onMissionInit();
	}

	@Override
	public void onMissionInit() {
		toast("onMissionInit...");
		getState().init();
		refreshUI();
	}

	@Override
	public void onMissionSuccess() {
		toast("onMissionSuccess");
		getState().next();
		refreshUI();
	}

	@Override
	public void onMissionFail() {
		// 计分
		// 弹出对话框（提示失败）,确定不玩,跳回主界面
		// 弹出对话框（提示失败）,确定重新玩,重新执行
		toast("onMissionFail");
		this.state.init();
		refreshUI();
	}

	@Override
	public void onMissionGiveup() {
		// 计分
		// 弹出对话框（确定放弃）,确定不玩,跳回主界面
		// 弹出对话框（确定放弃）,确定重新玩,重新执行
		// this.state.finish();
		toast("onMissionGiveup");
	}

	/**
	 * 根据当前的state的关卡状态,进行游戏界面的刷新
	 */
	private void refreshUI() {
		// NOTE:RelativeLayout支持Remove之后在添加,LinearLayout不支持这样....
		container.removeAllViews();
		
		toast("第" + getState().getPass() + "关,当前得分:"
				+ getState().getScore());
		// 获得关卡参数,参见ColorPolicy
		int params[] = getCurrentPolicyParams();
		toast("当前参数:" + params[0] + "-" + params[1] + "-" + params[2]);
		cvg = new ColorViewGroup2(this, params[0], params[2]);
		
		container.addView(cvg, new LayoutParams(
				LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
	}
}
