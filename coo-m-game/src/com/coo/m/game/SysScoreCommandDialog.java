package com.coo.m.game;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.coo.m.game.util.CommonCommandAdapter;
import com.kingstar.ngbf.ms.util.android.CommonItemDialog;
import com.kingstar.ngbf.ms.util.model.CommonItem;

/**
 * Topic 命令对话框
 * 
 * @since 0.6.0.0
 * @author boqing.shen
 * 
 */
public class SysScoreCommandDialog extends CommonItemDialog<GameScore> {

	public SysScoreCommandDialog(Activity parent, GameScore item) {
		super(parent, item);
	}

	@Override
	public void initControls(LinearLayout layout) {
		// 定义控件，控件New产生,没有从Context中寻找
		ListView lv = new ListView(parent);
		// 定义适配器
		@SuppressWarnings("unused")
		CommonCommandAdapter adapter = new CommonCommandAdapter(parent,
				getCommands(), lv);
		// 添加控件
		layout.addView(lv);
	}

	@Override
	protected String getTitle() {
		return item.getGameLabel() + ":" + item.getScore();
	}

	@Override
	public int getBtnGroup() {
		return BTN_GROUP_NONE;
	}

	/**
	 * 模拟产生Profile的属性条目对象,用于集中展现
	 * 
	 * @return
	 */
	private List<CommonItem> getCommands() {
		List<CommonItem> items = new ArrayList<CommonItem>();
		// 建立传递的item信息
		items.add(new CommonItem("score.share", "炫耀", item)
				.uiType(CommonItem.UIT_COMMAND_ACTION));
		// 关闭Dialog
		items.add(new CommonItem("dialog.cancel", "取消", this)
				.uiType(CommonItem.UIT_DIALOG_CANCEL));
		return items;
	}
}
