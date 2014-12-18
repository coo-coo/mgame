package com.coo.m.game;

/**
 * 当前游戏状态
 * 
 * @author boqing.shen
 * @since 1.0
 */
public final class GameState {
	
	private IGamePolicy policy;

	public GameState(IGamePolicy policy) {
		this.policy = policy;
	}

	/**
	 * 初始关
	 */
	public void init() {
		this.pass = 1;
		this.score = 0;
	}

	/**
	 * 多关模式: 下一关，成功会跳转到下一关
	 */
	public void next() {
		// 计分
		score = score + policy.score(pass);
		// 关卡下一步
		pass = pass + 1;
	}
	
	/**
	 * 单关模式: pass==1, 自定义加分机制
	 */
	public void addScore(int i) {
		score = score + i;
		pass=1;
	}

	/**
	 * 不玩了,或者自动结束,调用此方法,进行分值存储
	 */
	public void finish() {
		// TODO 分值存储
	}

	/**
	 * 选关,暂不支持
	 */
	@SuppressWarnings("unused")
	private void jump(int pass) {
		
	}

	/**
	 * 当前关号
	 */
	private int pass = 1;
	/**
	 * 当前成绩
	 */
	private int score = 0;

	/**
	 * TODO 当前计时器,参见相关参数
	 */

	public IGamePolicy getPolicy() {
		return policy;
	}

	public void setPolicy(IGamePolicy policy) {
		this.policy = policy;
	}

	public int getPass() {
		return pass;
	}

	public void setPass(int pass) {
		this.pass = pass;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
