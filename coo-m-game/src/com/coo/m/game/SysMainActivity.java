package com.coo.m.game;

import java.util.List;

import android.os.Bundle;
import android.widget.GridView;

import com.kingstar.ngbf.ms.util.android.GenericActivity;

/**
 * 
 *
 */
public class SysMainActivity extends GenericActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sys_main_activity);
		loadContent();
	}

	public void loadContent() {
		List<GameProperty> list = GplusManager.getGames();
		GridView girdView = (GridView) findViewById(R.id.gv_game);
		SysMainGridItemAdapter adapter = new SysMainGridItemAdapter(list,
				girdView);
		adapter.initContext(this);
	}

	
//	public void startGame(Activity a) {
//		Intent intent = new Intent(SysMainActivity.this, a.getClass());
//		startActivity(intent);
//	}
}
