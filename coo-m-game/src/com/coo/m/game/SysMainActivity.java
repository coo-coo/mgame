package com.coo.m.game;

import java.util.List;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
		case R.id.item_main_author:
			Intent intent2 = new Intent(SysMainActivity.this,
					SysAuthorActivity.class);
			startActivity(intent2);
			break;
		case R.id.item_main_illustrate:

			break;
		case R.id.item_main_share:
			Uri uri = Uri
					.parse("http://gdown.baidu.com/data/wisegame/07995b1aad7046f4/xiaomo_1.apk");
			share("分享游戏", uri);
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void share(String content, Uri uri) {
		Intent shareIntent = new Intent(Intent.ACTION_SEND);
		if (uri != null) {
			shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
			shareIntent.setType("image/*");
			shareIntent.putExtra("sms_body", content);
		} else {
			shareIntent.setType("text/plain");
		}
		shareIntent.putExtra(Intent.EXTRA_TEXT, content);
		startActivity(Intent.createChooser(shareIntent, "分享游戏"));
		// 系统默认标题
		// startActivity(shareIntent);
	}

}
