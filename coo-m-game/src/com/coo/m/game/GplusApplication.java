package com.coo.m.game;

import java.io.File;

import org.litepal.LitePalApplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.kingstar.ngbf.ms.util.FileUtil;
import com.kingstar.ngbf.ms.util.android.CommonConfig;

/**
 * [框架]应用，APP入口
 * @author boqing.shen
 * @since 1.0
 *
 */
public class GplusApplication extends LitePalApplication {

	private static final String TAG = GplusApplication.class
			.getSimpleName();

	@Override
	public void onCreate() {
		super.onCreate();
		Log.i(TAG, "App on created..");

		// 初始化Model
		initCommonModel();

		saveAppQrIconToSD();
		// GplusManager.score(GplusManager.G_CIRCULAR,220);
	}

	/**
	 * 保存App二维码到SD,分享用
	 */
	private void saveAppQrIconToSD() {
		try {
			File file = new File(GplusManager.APP_ICON_SDPATH);
			if (!file.exists()) {
				Bitmap bmp = BitmapFactory.decodeResource(
						getResources(),
						R.drawable.gplus_qr);
				FileUtil.saveBitmap(bmp,
						GplusManager.APP_ICON_SDPATH);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void initCommonModel() {
		CommonConfig.clearParams();
		CommonConfig.initParam(CommonConfig.KEY_CLASS_HOME_ACTIVITY,
				SysMainActivity.class);
		// CommonItemConfig.initParam(
		// CommonItemConfig.KEY_INT_DIALOG_VIEW_ID,
		// R.layout.common_dialog);
		// CommonItemConfig.initParam(
		// CommonItemConfig.KEY_INT_DIALOG_LAYOUT_ID,
		// R.id.layout_dialog_common);
	}
}
