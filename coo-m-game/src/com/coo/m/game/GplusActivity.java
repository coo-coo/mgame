package com.coo.m.game;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;

import com.kingstar.ngbf.ms.util.Reference;
import com.kingstar.ngbf.ms.util.android.CommonBizActivity;
import com.kingstar.ngbf.ms.util.android.GenericActivity;

/**
 * 基本GameActivity，交由子类继承实现
 * 
 * @author boqing.shen
 * 
 */
public abstract class GplusActivity extends CommonBizActivity implements IGame,
		DialogInterface.OnClickListener {

	/**
	 * 获得Game的基本属性,用于Game的信息读取,交由子类继承实现
	 */
	public abstract GameProperty getGameProperty();

	/**
	 * 返回Game的计分规则,用于Game的计分,输入参数设置等,交由子类继承实现
	 */
	public abstract IGamePolicy getGamePolicy();

	/**
	 * 交由子类实现,主要是根据关卡重画UI等必要行为
	 */
	protected void refreshUI() {

	}

	/**
	 * 当前状态:系统初始化
	 */
	private GameState state = null;

	public GameState getState() {
		return state;
	}

	/**
	 * 游戏策略,交由子类返回，参见getGamePolicy()
	 */
	private IGamePolicy policy = null;

	/**
	 * 内置Handler,发送消息
	 */
	private GameHandler gameHandler = new GameHandler(this);

	@Override
	@Reference(override = CommonBizActivity.class)
	public void initParams() {
		// 设置策略和游戏状态对象
		if (policy == null) {
			policy = getGamePolicy();
		}
		state = new GameState(policy);
	}

	/**
	 * 获得当前关卡的游戏参数
	 */
	public int[] getCurrentPolicyParams() {
		int pass = state.getPass();
		return getPolicy().params(pass);
	}

	/**
	 * 返回初始化后的Policy
	 */
	private IGamePolicy getPolicy() {
		return policy;
	}

	/**
	 * 记录得分
	 */
	private void storeScore() {
		GplusManager.score(getGameProperty(), state);
	}

	/**
	 * 发送Game事件,参见IGame
	 */
	public void notify(int gameEvent) {
		gameHandler.sendEmptyMessage(gameEvent);
	}

	public void notify(Message msg) {
		gameHandler.sendMessage(msg);
	}

	@Override
	@Reference(override = IGame.class, note = "游戏初始化")
	public void onGameInit() {
		toast("开始新游戏...");
		state.init();
		refreshUI();
	}

	@Override
	@Reference(override = IGame.class, note = "游戏结束")
	public void onGameOver() {
		toast("游戏结束...");
		// 跳转到主界面
		Intent intent = new Intent(this, SysMainActivity.class);
		startActivity(intent);
		this.finish();
	}

	@Override
	@Reference(override = IGame.class, note = "游戏成绩")
	public void onGameScore() {
		Intent intent = new Intent(this, SysScoreActivity.class);
		intent.putExtra("GAME_KEY", getGameProperty().getKey());
		intent.putExtra("GAME_LABEL", getGameProperty().getLabel());
		startActivity(intent);
		this.finish();
	}

	@Override
	@Reference(override = IGame.class, note = "任务过关:多关模式")
	public void onMissionSuccess() {
		// 不记录游戏成绩,总有onFail|onComplete
		state.next();
		refreshUI();
	}

	@Override
	@Reference(override = IGame.class, note = "任务完成:单关模式")
	public void onMissionComplete() {
		storeScore(); // 记录游戏成绩
		invokeDialog("任务完成!");
	}

	@Override
	@Reference(override = IGame.class, note = "任务完成:单关模式,分值添加")
	public void onMissionScoreAdd(int score) {
		state.addScore(score);
	}

	@Override
	@Reference(override = IGame.class, note = "任务失败")
	public void onMissionFail() {
		storeScore(); // 记录游戏成绩
		invokeDialog("任务失败!");
	}

	@Override
	@Reference(override = IGame.class, note = "任务超时")
	public void onMissionTimeout() {
		storeScore(); // 记录游戏成绩
		invokeDialog("任务超时!");
	}

	@Override
	@Reference(override = IGame.class, note = "任务放弃")
	public void onMissionGiveup() {
		if (state.getScore() > 0) {
			storeScore();
		}
		onGameOver();
	}

	@Override
	@Reference(override = DialogInterface.OnClickListener.class)
	public void onClick(DialogInterface dialog, int whichButton) {
		if (whichButton == AlertDialog.BUTTON_POSITIVE) {
			notify(IGame.GAME_INIT);
		} else if (whichButton == AlertDialog.BUTTON_NEGATIVE) {
			notify(IGame.GAME_OVER);
		}
	}

	/**
	 * 当游戏失败、放弃等，触发交互Dialog
	 * 参见:http://www.shyond.com/index.php/kaifa/456.html
	 */
	private void invokeDialog(String title) {
		int pass = state.getPass() - 1;
		String msg = "通过" + pass + "关,得分:" + state.getScore();
		new AlertDialog.Builder(this).setCancelable(false)
				.setTitle(title).setIcon(R.drawable.gplus_32)
				.setMessage(msg)
				.setPositiveButton("再玩一次", this)
				.setNegativeButton("不玩了", this).show();
	}

	@Override
	@Reference(override = CommonBizActivity.class)
	public String getHeaderTitle() {
		return getGameProperty().getLabel();
	}

	/**
	 * 初始化菜单,分登陆账号的角色，初始化不同的菜单
	 */
	@Override
	@Reference(override = GenericActivity.class)
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

	@Override
	@Reference(override = GenericActivity.class)
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.item_game_init:
			new ConfirmDialog(this, "确定重新开始游戏么?", IGame.GAME_INIT);
			break;
		case R.id.item_game_score:
			notify(IGame.GAME_SCORE);
			break;
		case R.id.item_game_over:
			new ConfirmDialog(this, "确定离开游戏么?",
					IGame.MISSION_GIVEUP);
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}

///**
// * 征询事件Dialog，获得确认，发送指定消息
// * 
// * @author boqing.shen
// * 
// */
//class ConfirmDialog implements DialogInterface.OnClickListener {
//
//	private GplusActivity gplus;
//
//	private int event;
//
//	public ConfirmDialog(GplusActivity gplus, String title, int event) {
//		this.gplus = gplus;
//		this.event = event;
//		invokeComfirmDialog(title);
//	}
//
//	private void invokeComfirmDialog(String title) {
//		new AlertDialog.Builder(gplus).setCancelable(false)
//				.setTitle(title).setPositiveButton("确定", this)
//				.setIcon(R.drawable.gplus_32)
//				.setNegativeButton("取消", this).show();
//	}
//
//	@Override
//	public void onClick(DialogInterface dialog, int which) {
//		if (which == AlertDialog.BUTTON_POSITIVE) {
//			gplus.notify(event);
//		}
//	}
//}

/**
 * 内置GameHandler，用于发送消息
 */
class GameHandler extends Handler {

	private IGame game;

	public GameHandler(IGame game) {
		this.game = game;
	}

	@Override
	public void handleMessage(Message msg) {
		int what = msg.what;
		switch (what) {
		case IGame.MISSION_SUCCESS:
			game.onMissionSuccess();
			break;
		case IGame.MISSION_COMPLETE:
			game.onMissionComplete();
			break;
		case IGame.MISSION_FAIL:
			game.onMissionFail();
			break;
		case IGame.MISSION_TIMEOUT:
			game.onMissionTimeout();
			break;
		case IGame.MISSION_GIVEUP:
			game.onMissionGiveup();
			break;
		case IGame.MISSION_SCORE_ADD:
			int score = (Integer) msg.obj;
			game.onMissionScoreAdd(score);
			break;
		case IGame.GAME_INIT:
			game.onGameInit();
			break;
		case IGame.GAME_OVER:
			game.onGameOver();
			break;
		case IGame.GAME_SCORE:
			game.onGameScore();
			break;
		}
	}
}
