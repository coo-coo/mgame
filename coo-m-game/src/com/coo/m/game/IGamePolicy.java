package com.coo.m.game;

public interface IGamePolicy {

	/**
	 * 根据关卡(Pass1->2->100),返回相关的难度参数数组
	 * 数组内参数,可以是 时间(S),怪物数量,方块数量等
	 */
	public int[] params(int pass);
	
	/**
	 * 根据所过的关卡,返回该关卡得分
	 */
	public int score(int pass);
}
