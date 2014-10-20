package com.coo.m.game;

import org.litepal.LitePalApplication;

import android.util.Log;

import com.kingstar.ngbf.ms.util.android.CommonItemConfig;

public class GplusApplication extends LitePalApplication {

	private static final String TAG = GplusApplication.class
			.getSimpleName();

	@Override
	public void onCreate() {
		super.onCreate();
		Log.i(TAG, "App on created..");

		initCommonModel();
		
		GplusManager.score(GplusManager.G_CIRCULAR,220);
	}

	private void initCommonModel() {
		CommonItemConfig.clearParams();
		CommonItemConfig.initParam(
				CommonItemConfig.KEY_CLASS_HOME_ACTIVITY,
				SysMainActivity.class);
		// CommonItemConfig.initParam(
		// CommonItemConfig.KEY_INT_DIALOG_VIEW_ID,
		// R.layout.common_dialog);
		// CommonItemConfig.initParam(
		// CommonItemConfig.KEY_INT_DIALOG_LAYOUT_ID,
		// R.id.layout_dialog_common);
	}
}
