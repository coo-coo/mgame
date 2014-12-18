package com.coo.m.game;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.litepal.crud.DataSupport;

import android.os.Environment;

import com.coo.m.game.circular.CircularActivity;
import com.coo.m.game.color.ColorActivity;
import com.coo.m.game.g2048.G2048Activity;
import com.coo.m.game.guess.GuessActivity;
import com.coo.m.game.robot.RobotActivity;
import com.coo.m.game.robot.TulingActivity;
import com.kingstar.ngbf.ms.util.DateUtil;

/**
 * [框架]游戏管理器
 * @author boqing.shen
 * @since 1.0
 */
public final class GplusManager {

	private static List<GameProperty> GAMES = new ArrayList<GameProperty>();

	// public static Class<?> MAIN_CLASS = CircularActivity.class;
	public static Class<?> MAIN_CLASS = SysMainActivity.class;

	public static String QING_ABOUT_URL = "http://lightapp.baidu.com/?appid=1568236";

	public static GameProperty G_GUESS = new GameProperty(
			GuessActivity.class, "猜猜看", R.drawable.gguess);

	public static GameProperty G_G2048 = GameProperty.blank()
			.activityClass(G2048Activity.class).label("2048")
			.icon(R.drawable.g2048)
			.layout(R.layout.g_2048_activity)
			.help("亲,手指上下左右的滑动一下,相同的数字碰到一起就会翻倍哦~");
	public static GameProperty G_COLOR = GameProperty.blank()
			.activityClass(ColorActivity.class).label("找颜色")
			.icon(R.drawable.gcolor)
			.layout(R.layout.g_color_activity)
			.help("亲,找出不一样的颜色方块吧~");
	public static GameProperty G_CIRCULAR = GameProperty.blank()
			.activityClass(CircularActivity.class).label("点4下")
			.icon(R.drawable.gcircle)
			.layout(R.layout.g_circular_activity)
			.help("亲,在橙色条消失之前点圆环4下吧,要4下哦~");
	public static GameProperty G_ROBOT = GameProperty.blank()
			.activityClass(RobotActivity.class).label("对话机器人")
			.icon(R.drawable.grobot)
			.layout(R.layout.g_robot_activity)
			.help("亲,向机器人无聊的发消息吧,看Ta怎么回答~");
	public static GameProperty G_TULING = GameProperty.blank()
			.activityClass(TulingActivity.class).label("晃晃看看")
			.icon(R.drawable.gtuling)
			.layout(R.layout.g_tuling_activity)
			.help("亲,晃一晃,随便看看吧~");

	/**
	 * BaiDu下载地址
	 */
	public static String URL_APP_DOWNLOAD = "点击下载<a href=\"http://shouji.baidu.com/game/item?docid=7244427&from=as&f=search_app_%E6%B6%88%E7%A3%A8%40list_1_title%401%40header_all_input\">消磨</a>...";
	public static String URL_APP_DOWNLOAD1 = "http://gdown.baidu.com/data/wisegame/07995b1aad7046f4/xiaomo_1.apk";

	public static String SDPATH = Environment.getExternalStorageDirectory()
			.getPath();
	public static String APP_ICON_SDPATH = SDPATH + "/Gplus_qr.png";
	public static String APP_DESC = "[消磨]:一个小游戏集合，闲暇时光里消磨一下吧!(获取二维码图片,扫描之后即可下载)";

	static {
		// 增加支持的游戏
		// TODO 从配置文件获得...

		GAMES.add(G_G2048);
		GAMES.add(G_COLOR);
		GAMES.add(G_CIRCULAR);
		GAMES.add(G_ROBOT);
		GAMES.add(G_TULING);

		// GAMES.add(G_GUESS); // 暂时不上架....
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
	 * 根据时间戳和现在的时间戳进行比较,显示3天以内的标示,如果超过三天，则显示日期
	 * 
	 * @param ts
	 * @return
	 */
	public static String getTsExpression(long ts) {
		long diff = System.currentTimeMillis() - ts;
		if (diff >= DAY_3) {
			return DateUtil.format(new Date(ts), "yyyy年MM月dd日");
		} else if (diff < DAY_3 && diff >= DAY_2) {
			return "两天前";
		} else if (diff < DAY_2 && diff >= DAY_1) {
			return "一天前";
		} else if (diff < DAY_1 && diff >= HOUR_1) {
			int hour = (int) (diff / HOUR_1);
			return hour + "小时前";
		} else if (diff < HOUR_1 && diff >= 0) {
			int min = (int) (diff / MIN_1);
			return min + "分钟前";
		} else {
			return "";
		}
	}

	private static long MIN_1 = 60 * 1000;
	private static long HOUR_1 = MIN_1 * 60;
	private static long DAY_1 = HOUR_1 * 24;
	private static long DAY_2 = DAY_1 * 2;
	private static long DAY_3 = DAY_1 * 3;

	/**
	 * 获得所有的支持的游戏
	 * 
	 * @return
	 */
	public static List<GameProperty> getGames() {
		return GAMES;
	}

	/**
	 * 记录当前玩家的游戏记录 TODO 成绩云存储(参看ms-baidu)
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
