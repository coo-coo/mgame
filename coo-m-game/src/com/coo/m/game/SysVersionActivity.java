package com.coo.m.game;

import java.util.ArrayList;
import java.util.List;

import android.widget.ListView;

import com.coo.m.game.util.CommonItemAdapter;
import com.kingstar.ngbf.ms.util.android.CommonBizActivity;
import com.kingstar.ngbf.ms.util.android.res.ServiceProvider;
import com.kingstar.ngbf.ms.util.model.CommonItem;

/**
 * [框架] 版本信息
 * 
 * @author boqing.shen
 * @since 1.3
 */
public class SysVersionActivity extends CommonBizActivity {

	@Override
	public String getHeaderTitle() {
		return "版本信息";
	}

	@Override
	public int getResViewLayoutId() {
		return R.layout.sys_version_activity;
	}

	// @Override
	public void loadContent() {
		ListView listView = (ListView) findViewById(R.id.lv_sys_version);
		// 定义适配器
		adapter = new CommonItemAdapter(this, getItems(), listView);
	}

	/**
	 * 产生属性条目对象,用于集中展现
	 * 
	 * @return
	 */
	private List<CommonItem> getItems() {
		String vcurrent = ServiceProvider.getAppVersionName(this);
		List<CommonItem> items = new ArrayList<CommonItem>();
		items.add(new CommonItem("version.app", "应用名称",
				GplusManager.APP_NAME));
		items.add(new CommonItem("version.current", "当前版本", vcurrent));
		items.add(new CommonItem("version.author", "联系作者",
				"shenboqing@163.com"));

		// items.add(new CommonItem("version.latest", "最新版本",
		// GplusManager.APP_VLATEST));
		return items;
	}

}
