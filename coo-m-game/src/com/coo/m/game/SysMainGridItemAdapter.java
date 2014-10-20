package com.coo.m.game;

import java.util.List;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.kingstar.ngbf.ms.util.android.CommonItemAdapter;
import com.kingstar.ngbf.ms.util.android.CommonItemHolder;

public class SysMainGridItemAdapter extends CommonItemAdapter<GameProperty> {

	/**
	 * 构造函数
	 */
	public SysMainGridItemAdapter(List<GameProperty> items, GridView composite) {
		super(items, composite);
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
		startGame(item);
		// toast(item.getLabel() + "-" +
		// item.getActivityClass().getName());
	}

	private void startGame(GameProperty item) {
		Intent intent = new Intent(this.getActivity(),
				item.getActivityClass());
		getActivity().startActivity(intent);
	}
}

class GameGridHolder extends CommonItemHolder {
	// public Button btn_game;
	public TextView tv_label;
	public ImageView iv_icon;
}