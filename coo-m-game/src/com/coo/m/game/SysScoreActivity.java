package com.coo.m.game;

import java.util.List;

import android.app.Dialog;
import android.content.Intent;
import android.widget.ListView;

import com.coo.ms.cloud.weixin.WeixinHandler;
import com.kingstar.ngbf.ms.util.android.CommonBizActivity;
import com.kingstar.ngbf.ms.util.model.CommonItem;

/**
 * [框架]游戏成绩界面
 * 
 * @author boqing.shen
 * @since 1.0
 * 
 */
public class SysScoreActivity extends CommonBizActivity {

	@Override
	public String getHeaderTitle() {
		Intent intent = getIntent();
		String label = intent.getStringExtra("GAME_LABEL");
		return label + ":成绩单";
	}

	@Override
	public int getResViewLayoutId() {
		return R.layout.sys_blank_activity;
	}

	/**
	 * WeixinHandler
	 */
	private WeixinHandler wxHandler = null;

	@Override
	public void loadContent() {
		// 获得传递过来的信息,参见GplusActivity.onGameScore()
		Intent intent = getIntent();
		String gameKey = intent.getStringExtra("GAME_KEY");
		
		// 创建对象,添加对象,适用于简单列表
		ListView composite = new ListView(this, null,
				R.attr.ref_common_lv);
		this.setContentView(composite);
		
		List<GameScore> list = GplusManager.getScores(gameKey);
		adapter = new SysScoreAdapter(this, list, composite);
		
		wxHandler = new WeixinHandler(this);
	}

	/**
	 * 监听GameScore的长嗯响应
	 * 
	 * @since 1.3
	 */
	@Override
	public void onAdapterItemClickedLong(Object item) {
		GameScore score = (GameScore) item;
		// 弹出命令对话框
		new SysScoreCommandDialog(this, score).show();
	}

	/**
	 * 监听SysScoreCommandDialog的Item中的单击响应
	 * 
	 * @since 1.3
	 */
	@Override
	public void onAdapterItemClicked(Object item) {
		if (item instanceof CommonItem) {
			CommonItem ci = (CommonItem) item;
			int uiType = ci.getUiType();
			switch (uiType) {
			case CommonItem.UIT_COMMAND_ACTION:
				// 分享到微信...
				shareToWeixin((GameScore) ci.getValue());
				break;
			case CommonItem.UIT_DIALOG_CANCEL:
				Dialog dlg = (Dialog) ci.getValue();
				dlg.cancel();
				break;
			default:
				// 其它，包括Label/Boolean等,不处理
				break;
			}
		}
	}

	/**
	 * 发送Link地址到微信
	 * 
	 * @since 1.3
	 */
	private void shareToWeixin(GameScore score) {
		if (score != null) {
			String ts = GplusManager.getTsDateText(score
					.getGameTs());
			String desc = "我\"" + score.getGameLabel() + "\"得了"
					+ score.getScore() + "分,厉害吧~(" + ts
					+ ")";
			wxHandler.share(GplusManager.createNetLink(desc));
		} else {
			toast("分享失败,项目没选中!");
		}
	}
}
