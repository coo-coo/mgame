package com.coo.m.game;

import java.io.File;
import java.util.List;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;

import com.coo.ms.cloud.model.NetLink;
import com.coo.ms.cloud.weixin.WeixinHandler;
import com.kingstar.ngbf.ms.util.android.GenericActivity;

/**
 * [框架]主界面
 * 
 * @author boqing.shen
 * @since 1.0
 */
public class SysMainActivity extends GenericActivity {

	@SuppressWarnings("unused")
	private SysMainAdapter adapter;
	
	/**
	 * WeixinHandler
	 */
	private WeixinHandler wxHandler = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		loadContent();
	}

	@Override
	public void loadContent() {
		setContentView(R.layout.sys_main_activity);
		List<GameProperty> list = GplusManager.getGames();
		GridView composite = (GridView) findViewById(R.id.gv_game);
		adapter = new SysMainAdapter(this, list, composite);
		wxHandler = new WeixinHandler(this);
	}

	@Override
	public int getResMenuId() {
		return R.menu.main;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.item_main_about:
			Intent intent1 = new Intent(SysMainActivity.this,
					SysAboutActivity.class);
			startActivity(intent1);
			break;
		case R.id.item_main_share:
			shareToWeixin();
			break;
		case R.id.item_main_version:
			Intent intent2 = new Intent(SysMainActivity.this,
					SysVersionActivity.class);
			startActivity(intent2);
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * 发送Link地址到微信
	 * 
	 * @since 1.3
	 */
	private void shareToWeixin() {
		NetLink nl = GplusManager
				.createNetLink("百度,安智,安卓,91等市场都有的下哦~(暂时只支持安卓手机..)");
		wxHandler.share(nl);
	}

	/**
	 * APP分享连接
	 * 
	 * @deprecated @since 1.3
	 */
	@SuppressWarnings("unused")
	private void shareApp_1() {
		try {
			Intent intent = new Intent();
			// 直接调用微信组件...
			ComponentName componentName = new ComponentName(
					"com.tencent.mm",
					"com.tencent.mm.ui.tools.ShareImgUI");
			intent.setComponent(componentName);
			intent.setAction(Intent.ACTION_SEND);
			// intent.setType("text/html");
			intent.setType("image/*"); // 发送图片
			intent.putExtra(Intent.EXTRA_TEXT,
					GplusManager.APP_DESC);
			// intent.putExtra(Intent.EXTRA_SUBJECT, "标题");
			// intent.putExtra(Intent.EXTRA_HTML_TEXT,
			// GplusManager.URL_APP_DOWNLOAD);
			// intent.putExtra(Intent.EXTRA_ORIGINATING_URI,
			// GplusManager.URL_APP_DOWNLOAD1);
			// intent.putExtra(Intent.EXTRA_SHORTCUT_ICON,
			// "SHORTCUT_ICON");
			// intent.putExtra(Intent.EXTRA_SHORTCUT_NAME,
			// "SHORTCUT_NAME");
			// intent.putExtra(Intent.EXTRA_TITLE, "EXTRA_TITLE");

			File file = new File(GplusManager.APP_ICON_SDPATH);
			Uri uri = Uri.fromFile(file);
			intent.putExtra(Intent.EXTRA_STREAM, uri);

			intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
			startActivity(intent);
			// 分享很多..暂时不需要,只分享到微信...
			// startActivity(Intent.createChooser(intent, "分享消磨"));
		} catch (Exception e) {
			toast("分享失败!");
		}
	}

}
