package com.coo.m.game;

import java.util.List;

import android.content.Intent;
import android.widget.ListView;

import com.kingstar.ngbf.ms.util.android.CommonBizActivity;

/**
 * [框架]游戏成绩界面
 * @author boqing.shen
 * @since 1.0
 *
 */
public class SysScoreActivity extends CommonBizActivity {

	@Override
	public String getHeaderTitle() {
		Intent intent = getIntent();
		String label = intent.getStringExtra("GAME_LABEL");
		return  label+":成绩单";
	}

	@Override
	public int getResViewLayoutId() {
		return R.layout.sys_score_activity;
	}

	// SysCoreAdapter adapter
	@Override
	public void loadContent() {
		// 获得传递过来的信息,参见GplusActivity.onGameScore()
		Intent intent = getIntent();
		String gameKey = intent.getStringExtra("GAME_KEY");
		ListView composite = (ListView) findViewById(R.id.lv_sys_core);

		List<GameScore> list = GplusManager.getScores(gameKey);
		adapter = new SysScoreAdapter(this, list, composite);
	}
}
