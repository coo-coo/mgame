package com.coo.m.game;

import java.util.List;

import android.os.Bundle;
import android.widget.GridView;

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
}
