package com.coo.m.game;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

import org.apache.log4j.lf5.util.Resource;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
			Resources res=getResources();
//			Bitmap bt=BitmapFactory.decodeResource(res, R.drawable.gplus);
//			File f=getFilesDir();
//			FileOutputStream outStream;
//			try {
//				outStream = new FileOutputStream(f);
//				bm.compress(Bitmap.CompressFormat.PNG, 100, outStream);
//				try {
//					outStream.flush();
//					outStream.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			}

			Uri uri = Uri.fromFile(new File("android.resource://" + getPackageName()
					+ "/drawable/" + "gplus.png"));
//			Uri uri = Uri.fromFile(new File("android.resource://" + getPackageName()
//					+ "/drawable/" + "gplus.png"));
			share("http://gdown.baidu.com/data/wisegame/07995b1aad7046f4/xiaomo_1.apk", uri);
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void share(String content, Uri uri) {
		Intent shareIntent = new Intent(Intent.ACTION_SEND);
//		if (uri != null) {
			if (true) {
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
		startActivity(Intent.createChooser(shareIntent, "分享"));
		// 系统默认标题
		// startActivity(shareIntent);
	}
	
	private void getOverflowMenu() {  
		   
	     try {  
	        ViewConfiguration config = ViewConfiguration.get(this);  
	        Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");  
	        if(menuKeyField != null) {  
	            menuKeyField.setAccessible(true);  
	            menuKeyField.setBoolean(config, false);  
	        }  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }  
	}  

}
