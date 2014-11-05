package com.coo.m.game;

import java.util.List;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.kingstar.ngbf.ms.util.android.CommonAdapter;
import com.kingstar.ngbf.ms.util.android.CommonItemHolder;

public class SysCoreAdapter extends CommonAdapter<GameScore> {

	public SysCoreAdapter(Activity parent, List<GameScore> items,
			ListView composite) {
		super(parent, items, composite);
	}

	/**
	 * 返回控件布局
	 */
	public int getItemConvertViewId() {
		return R.layout.sys_score_activity_row;
	}

	@Override
	public CommonItemHolder initHolder(View convertView) {
		GameScoreRowHolder holder = new GameScoreRowHolder();
		holder.tv_label = (TextView) convertView
				.findViewById(R.id.tv_sys_score_label);
		holder.tv_ts = (TextView) convertView
				.findViewById(R.id.tv_sys_score_ts);
		holder.tv_score = (TextView) convertView
				.findViewById(R.id.tv_sys_score_score);
		return holder;
	}

	@Override
	public void initHolderValue(CommonItemHolder ciHolder, GameScore item) {
		GameScoreRowHolder holder = (GameScoreRowHolder) ciHolder;
		holder.tv_label.setText(item.getGameLabel());
		String ts = GplusManager.getTsText(item.getGameTs());
		holder.tv_ts.setText(ts);
		holder.tv_score.setText(item.getScore() + "分");
	}
}

class GameScoreRowHolder extends CommonItemHolder {
	public TextView tv_label;
	public TextView tv_ts;
	public TextView tv_score;
}
