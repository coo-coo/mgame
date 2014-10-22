package com.coo.m.game;

import org.litepal.crud.DataSupport;

public class GameScore extends DataSupport {

	private long id = 0l;
	/**
	 * 游戏玩家账号，手机号?
	 * TODO 系统获得手机号
	 */
	private String player = "";
	/**
	 * 游戏成绩
	 */
	private int score = 0;
	/**
	 * 游戏关卡
	 */
	private int pass = 1;
	/**
	 * 游戏玩的时间戳（日期）
	 */
	private long gameTs = 0;
	/**
	 * 游戏Key，以包名
	 */
	private String gameKey = "";
	/**
	 * 游戏名称
	 */
	private String gameLabel = "";
	/**
	 * 游戏版本
	 */
	private String gameVersion = "";

	public GameScore(GameProperty gp, int score) {
		this.gameKey = gp.getKey();
		this.gameLabel = gp.getLabel();
		this.gameVersion = gp.getVersion();
		// 记录游戏得分
		this.score = score;
		// 记录游戏时间戳
		this.gameTs = System.currentTimeMillis();
	}
	
	public GameScore() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public long getGameTs() {
		return gameTs;
	}

	public void setGameTs(long gameTs) {
		this.gameTs = gameTs;
	}

	public String getGameKey() {
		return gameKey;
	}

	public void setGameKey(String gameKey) {
		this.gameKey = gameKey;
	}

	public String getGameLabel() {
		return gameLabel;
	}

	public void setGameLabel(String gameLabel) {
		this.gameLabel = gameLabel;
	}

	public String getGameVersion() {
		return gameVersion;
	}

	public void setGameVersion(String gameVersion) {
		this.gameVersion = gameVersion;
	}

	public int getPass() {
		return pass;
	}

	public void setPass(int pass) {
		this.pass = pass;
	}
}
