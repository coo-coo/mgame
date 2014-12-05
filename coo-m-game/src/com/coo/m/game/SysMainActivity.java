package com.coo.m.game;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.widget.GridView;

import com.kingstar.ngbf.ms.util.Reference;
import com.kingstar.ngbf.ms.util.android.GenericActivity;

/**
 * 
 *
 */
public class SysMainActivity extends GenericActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sys_main_activity);
		getOverflowMenu();
		loadContent();
	}

	@SuppressWarnings("unused")
	private SysMainAdapter adapter;

	public void loadContent() {
		List<GameProperty> list = GplusManager.getGames();
		GridView composite = (GridView) findViewById(R.id.gv_game);
		adapter = new SysMainAdapter(this, list, composite);
	}

	@Override
	@Reference(override = GenericActivity.class)
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.item_main_version:
			Intent intent1 = new Intent(SysMainActivity.this,
					SysVersionActivity.class);
			startActivity(intent1);
			break;
		case R.id.item_main_share:
			shareApp();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * APP分享连接
	 */
	private void shareApp() {
		Intent intent = new Intent();
		// 直接调用微信组件...
		ComponentName componentName = new ComponentName(
				"com.tencent.mm",
				"com.tencent.mm.ui.tools.ShareImgUI");
		intent.setComponent(componentName);
		intent.setAction(Intent.ACTION_SEND);
		// intent.setType("text/html");
		intent.setType("image/*"); // 发送图片
		intent.putExtra(Intent.EXTRA_TEXT, GplusManager.APP_DESC);
		// intent.putExtra(Intent.EXTRA_SUBJECT, "标题");
		// intent.putExtra(Intent.EXTRA_HTML_TEXT,
		// GplusManager.URL_APP_DOWNLOAD);
		// intent.putExtra(Intent.EXTRA_ORIGINATING_URI,
		// GplusManager.URL_APP_DOWNLOAD1);
		// intent.putExtra(Intent.EXTRA_SHORTCUT_ICON, "SHORTCUT_ICON");
		// intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "SHORTCUT_NAME");
		// intent.putExtra(Intent.EXTRA_TITLE, "EXTRA_TITLE");
		try {
			File file = new File(GplusManager.APP_ICON_SDPATH);
			Uri uri = Uri.fromFile(file);
			intent.putExtra(Intent.EXTRA_STREAM, uri);
		} catch (Exception e) {
			toast("加载图片失败:" + e.getMessage());
		}
		
		intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
		startActivity(intent);
		// 分享很多..暂时不需要,只分享到微信...
		// startActivity(Intent.createChooser(intent, "分享消磨"));
	}

	private void getOverflowMenu() {
		try {
			ViewConfiguration config = ViewConfiguration.get(this);
			Field menuKeyField = ViewConfiguration.class
					.getDeclaredField("sHasPermanentMenuKey");
			if (menuKeyField != null) {
				menuKeyField.setAccessible(true);
				menuKeyField.setBoolean(config, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
