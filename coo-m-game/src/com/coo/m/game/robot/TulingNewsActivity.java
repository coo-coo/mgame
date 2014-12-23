package com.coo.m.game.robot;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.widget.ListView;

import com.coo.m.game.GameProperty;
import com.coo.m.game.GplusActivity;
import com.coo.m.game.GplusManager;
import com.coo.m.game.R;
import com.kingstar.ngbf.ms.util.Reference;
import com.kingstar.ngbf.ms.util.android.CommonBizActivity;
import com.kingstar.ngbf.ms.util.android.component.IShakeListener;

/**
 * [GAME]晃晃新闻
 * 
 * @author boqing.shen
 * @since 1.3
 */
public class TulingNewsActivity extends GplusActivity implements IShakeListener {

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			TulingResult result = (TulingResult) msg.obj;
			if (result != null) {
				// 获得数据列表
				adapter.setItems(result.getList());
				adapter.notifyDataSetChanged();
			} else {
				toast("暂未获得新闻,再晃一下吧...");
			}
		};
	};

	private TulingNewsAdapter adapter;

	@Override
	public void loadContent() {
		List<TulingItem> items = new ArrayList<TulingItem>();
		ListView composite = (ListView) findViewById(R.id.lv_tuling_news);
		adapter = new TulingNewsAdapter(this, items, composite);
		// 注册监听晃动
		this.registerShakeListener(this);
		// 上来选一个
		onShake();
	}
	
	/**
	 * AdapterItem改变时调用，对应EVT_ITEM_CLICKED事件
	 */
	@Override
	@Reference(override=CommonBizActivity.class)
	public void onAdapterItemClicked(Object item) {
		TulingItem ti = (TulingItem)item;
//		toast(ti.getArticle());
		Bundle bundle = new Bundle();
		bundle.putSerializable("TULING_ITEM", ti);
		Intent intent = new Intent();
		intent.setClass(TulingNewsActivity.this, TulingNewsInfoActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);
		overridePendingTransition(R.anim.fade, R.anim.hold);
		
	}
	
	
	@Override
	public GameProperty getGameProperty() {
		return GplusManager.G_TULING_NEWS;
	}

	@Override
	@Reference(override = GplusActivity.class)
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.notgame, menu);
		return true;
	}

	@Override
	@Reference(override = IShakeListener.class)
	public void onShake() {
		TulingHelper.pickNews(handler);
	}
}
