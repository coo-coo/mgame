package com.coo.m.game;

public interface IGame {

	int MISSION_SUCCESS = 10; // 任务过关
	int MISSION_COMPLETE = 11; // 任务完成
	int MISSION_FAIL = 20; // 任务失败
	int MISSION_TIMEOUT = 21; // 任务超时
	int MISSION_GIVEUP = 22; // 任务放弃
	
	int MISSION_SCORE_ADD = 3; // 增加分值
	
	int GAME_INIT = 0; // 游戏初始化
	int GAME_OVER = 9; // 游戏结束
	int GAME_SCORE = 100; // 游戏成绩
	
	/**
	 * 游戏初始化
	 */
	public void onGameInit();

	/**
	 * 游戏结束
	 */
	public void onGameOver();

	/**
	 * 游戏成绩
	 */
	public void onGameScore();

	/**
	 * 任务过关:多关模式
	 */
	public void onMissionSuccess();
	
	/**
	 * 任务完成:单关模式
	 */
	public void onMissionComplete();
	
	
	public void onMissionScoreAdd(int score);

	/**
	 * 任务失败
	 */
	public void onMissionFail();

	/**
	 * 任务放弃
	 */
	public void onMissionGiveup();

	/**
	 * 任务超时
	 */
	public void onMissionTimeout();

}
