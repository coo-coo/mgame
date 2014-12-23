package com.coo.m.game;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.kingstar.ngbf.ms.util.android.CommonAdapter;
import com.kingstar.ngbf.ms.util.android.CommonItemHolder;

/**
 * 游戏成绩适配器
 * @author boqing.shen
 * @since 1.0
 *
 */
public class SysScoreAdapter extends CommonAdapter<GameScore> {

	GameScoreRowHolder holder = new GameScoreRowHolder();
	Context context =null;

	public SysScoreAdapter(Activity parent, List<GameScore> items,
			ListView composite) {
		super(parent, items, composite);
		this.context=parent;
	}

	/**
	 * 返回控件布局
	 */
	public int getItemConvertViewId() {
		return R.layout.sys_score_activity_row;
	}

	@Override
	public CommonItemHolder initHolder(View convertView) {
		holder = new GameScoreRowHolder();
		holder.tv_ts = (TextView) convertView
				.findViewById(R.id.tv_sys_score_ts);
		holder.tv_score = (TextView) convertView
				.findViewById(R.id.tv_sys_score_score);
		return holder;
	}



	@Override
	public void initHolderValue(CommonItemHolder ciHolder, GameScore item) {
		holder = (GameScoreRowHolder) ciHolder;
		String ts = GplusManager.getTsExpression(item.getGameTs());
		holder.tv_ts.setText(ts);
		holder.tv_score.setText(item.getScore() + "分");
	}
	
	/**
	 * TODO 分享分数...
	 * @param content
	 * @param uri
	 */
	private void share(String content, Uri uri) {
		Intent shareIntent = new Intent(Intent.ACTION_SEND);
		if (uri != null) {
			shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
			shareIntent.setType("image/*");
			shareIntent.putExtra("sms_body", content);
			shareIntent.putExtra(Intent.EXTRA_TEXT, content);
			
		} else {
			shareIntent.setType("text/plain");
			shareIntent.putExtra(Intent.EXTRA_SUBJECT, "分享");
			shareIntent.putExtra(Intent.EXTRA_TEXT, content);
		}
		shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
		shareIntent.putExtra(Intent.EXTRA_TEXT, content);
		context.startActivity(Intent.createChooser(shareIntent, "分享成绩"));
		// 系统默认标题
		// startActivity(shareIntent);
	}

	class lvButtonListener implements OnClickListener {
		private int score;
		private String label;

		lvButtonListener(int score,String label) {
			this.score = score;
			this.label=label;
		}

		@Override
		public void onClick(View v) {
			String s="我在"+label+"游戏中"+"获得了"+score+"分";
			share(s,null);
		}
	}

}

class GameScoreRowHolder extends CommonItemHolder {
//	public TextView tv_label;
	public TextView tv_ts;
	public TextView tv_score;
}
