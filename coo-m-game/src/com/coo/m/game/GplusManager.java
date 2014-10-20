package com.coo.m.game;

import java.util.ArrayList;
import java.util.List;

import org.litepal.crud.DataSupport;

import com.coo.m.game.circular.CircularActivity;
import com.coo.m.game.color.ColorActivity;
import com.coo.m.game.g2048.G2048Activity;
import com.coo.m.game.guess.GuessActivity;

/**
 * 游戏管理器
 *
 */
public final class GplusManager {

	private static List<GameProperty> GAMES = new ArrayList<GameProperty>();

	public static GameProperty G_GUESS = new GameProperty(
			GuessActivity.class, "猜猜看", R.drawable.g_001);
	public static GameProperty G_G2048 = new GameProperty(
			G2048Activity.class, "2048", R.drawable.g_002);
	public static GameProperty G_COLOR = new GameProperty(
			ColorActivity.class, "找颜色", R.drawable.g_003);
	public static GameProperty G_CIRCULAR = new GameProperty(
			CircularActivity.class, "点点看", R.drawable.g_004);

	static {
		// 增加支持的游戏
		GAMES.add(G_GUESS);
		GAMES.add(G_G2048);
		GAMES.add(G_COLOR);
		GAMES.add(G_CIRCULAR);
	}

	/**
	 * 获得所有的支持的游戏
	 * 
	 * @return
	 */
	public static List<GameProperty> getGames() {
		return GAMES;
	}

	/**
	 * 记录当前玩家的游戏记录
	 * 
	 * @param gp
	 */
	public static void score(GameProperty gp, int score) {
		GameScore gs = new GameScore(gp, score);
		gs.save();
	}

	/**
	 * 获得所有的游戏记录 https://github.com/LitePalFramework/LitePal
	 */
	public static List<GameScore> getScores() {
		return DataSupport.findAll(GameScore.class);
	}

	/**
	 * 获得指定GameKey的游戏成绩
	 */
	public static List<GameScore> getScores(String gameKey) {
		return DataSupport.where("gameKey = ?", gameKey).find(
				GameScore.class);
	}

}
