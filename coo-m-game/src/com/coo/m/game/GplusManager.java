package com.coo.m.game;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.litepal.crud.DataSupport;

import com.coo.m.game.circular.CircularActivity;
import com.coo.m.game.color.ColorActivity;
import com.coo.m.game.g2048.G2048Activity;
import com.coo.m.game.guess.GuessActivity;
import com.coo.m.game.robot.RobotActivity;
import com.kingstar.ngbf.ms.util.DateUtil;

/**
 * 游戏管理器
 * 
 */
public final class GplusManager {

	private static List<GameProperty> GAMES = new ArrayList<GameProperty>();

//	public static Class<?> MAIN_CLASS = CircularActivity.class;
	 public static Class<?> MAIN_CLASS = SysMainActivity.class;

	public static GameProperty G_GUESS = new GameProperty(
			GuessActivity.class, "猜猜看", R.drawable.gguess);
	public static GameProperty G_G2048 = new GameProperty(
			G2048Activity.class, "2048", R.drawable.g2048);
	public static GameProperty G_COLOR = new GameProperty(
			ColorActivity.class, "找颜色", R.drawable.gcolor);
	public static GameProperty G_CIRCULAR = new GameProperty(
			CircularActivity.class, "点4下", R.drawable.gcircle);
	public static GameProperty G_ROBOT = new GameProperty(
			RobotActivity.class, "对话机器人", R.drawable.gcircle);

	static {
		// 增加支持的游戏
		// GAMES.add(G_GUESS); // 暂时不上架....
		GAMES.add(G_G2048);
		GAMES.add(G_COLOR);
		GAMES.add(G_CIRCULAR);
		GAMES.add(G_ROBOT);
	}

	/**
	 * 获得当前游戏玩家,即手机号
	 * 
	 * @return
	 */
	public static String getCurrentPlayer() {
		// TODO
		return "13X12345678";
	}

	public static String getTsDateText(long ts) {
		return DateUtil.format(new Date(ts), "yyyy-MM-dd");
	}

	/**
	 * 返回TS的时间表达式
	 */
	public static String getTsText(long ts) {
		return DateUtil.format(new Date(ts), "yyyy-MM-dd HH:mm:ss");
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
	 */
	public static void score(GameProperty gp, GameState state) {
		GameScore gs = new GameScore(gp, state.getScore());
		gs.setPlayer(GplusManager.getCurrentPlayer());
		gs.setPass(state.getPass());
		gs.save();
	}

	/**
	 * 获得所有的游戏记录 https://github.com/LitePalFramework/LitePal
	 */
	public static List<GameScore> getScores() {
		return DataSupport.findAll(GameScore.class);
	}

	/**
	 * 获得指定GameKey的游戏成绩，按时间倒序排序
	 */
	public static List<GameScore> getScores(String gameKey) {
		return DataSupport.where("gameKey = ?", gameKey)
				.order("gamets desc").find(GameScore.class);
	}

}
