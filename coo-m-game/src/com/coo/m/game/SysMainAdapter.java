package com.coo.m.game;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.kingstar.ngbf.ms.util.android.CommonAdapter;
import com.kingstar.ngbf.ms.util.android.CommonItemHolder;

/**
 * 主界面适配器
 * @author boqing.shen
 * @since 1.0
 *
 */
public class SysMainAdapter extends CommonAdapter<GameProperty> {

	/**
	 * 构造函数
	 */
	public SysMainAdapter(Activity parent, List<GameProperty> items,
			GridView composite) {
		super(parent, items, composite);
	}

	/**
	 * 返回布局...
	 */
	@Override
	public int getItemConvertViewId() {
		return R.layout.sys_main_activity_grid;
	}

	@Override
	public CommonItemHolder initHolder(View convertView) {
		GameGridHolder holder = new GameGridHolder();
		holder.iv_icon = (ImageView) convertView
				.findViewById(R.id.iv_sysmain_game_icon);
		holder.tv_label = (TextView) convertView
				.findViewById(R.id.iv_sysmain_game_label);
		return holder;
	}

	@Override
	public void initHolderValue(CommonItemHolder ciHolder, GameProperty item) {
		GameGridHolder holder = (GameGridHolder) ciHolder;
		// 设置标题
		// 设置图片....
		ImageView icon = holder.iv_icon;
		icon.setAdjustViewBounds(true);
		// icon.setScaleType(ImageView.ScaleType.CENTER_CROP);
		icon.setPadding(0, 0, 1, 1);
		icon.setImageResource(item.getIcon());
		holder.tv_label.setText(item.getLabel());
	}

	@Override
	public void onItemClick(AdapterView<?> parentView, View view,
			int position, long rowId) {
		GameProperty item = getItem(position);
		// 跳转到指定的Game中去
		Intent intent = new Intent(parent, item.getActivityClass());
		parent.startActivity(intent);
	}
}

class GameGridHolder extends CommonItemHolder {
	// public Button btn_game;
	public TextView tv_label;
	public ImageView iv_icon;
}