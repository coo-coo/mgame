package com.coo.m.game;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;

import com.kingstar.ngbf.ms.util.android.CommonBizActivity;

public abstract class GplusActivity extends CommonBizActivity {

	public abstract GameProperty getGameProperty();

	/**
	 * 当前状态
	 */
	protected GameState state = null;
	/**
	 * 有i型
	 */
	protected IGamePolicy policy = null;
	
	protected GameHandler gameHandler = new GameHandler(this);
	
	
	
	/**
	 * 获得当前关卡的游戏参数
	 * 
	 * @return
	 */
	protected int[] getCurrentPolicyParams() {
		int pass = getState().getPass();
		return getPolicy().params(pass);
	}
	
	public GameHandler getGameHandler() {
		return gameHandler;
	}
	
	/**
	 * 设置Policy,设置游戏状态
	 */
	public void setPolicy(IGamePolicy policy) {
		this.policy = policy;
		this.state = new GameState(policy);
	}

	/**
	 * 返回缺省的Policy
	 * 
	 * @return
	 */
	public IGamePolicy getPolicy() {
		return policy;
	}

	public void score(int score) {
		@SuppressWarnings("unused")
		GameScore gs = new GameScore(getGameProperty(), score);
		// gs.setTs(System.currentTimeMillis());
		// gs.setHost();
		// socre.save();
	}

	public GameState getState() {
		return state;
	}

	/**
	 * 游戏重新开始
	 */
	protected void doGamePlay() {
		toast("doGameStart");
		// TODO 记录上一次成绩
		// 游戏状态重新开始
		this.state.init();
		// TODO 游戏重新开始....
		onMissionInit();
	}

	/**
	 * 开始游戏,交由子类实现
	 */
	protected void onMissionInit() {

	}

	protected void onMissionSuccess() {

	}

	protected void onMissionFail() {

	}

	protected void onMissionGiveup() {

	}

	/**
	 * 查看游戏成绩，转向ScoreActivity
	 */
	protected void doGameScore() {
		toast("doGameScore");
	}

	@Override
	public String getHeaderTitle() {
		return getGameProperty().getLabel();
	}

	/**
	 * 初始化菜单,分登陆账号的角色，初始化不同的菜单
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.item_play:
			doGamePlay();
			break;
		case R.id.item_score:
			doGameScore();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	
	
	
}



