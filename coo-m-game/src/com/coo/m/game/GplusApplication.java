package com.coo.m.game;

import java.io.File;

import org.litepal.LitePalApplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.coo.ms.cloud.weixin.WeixinApi;
import com.kingstar.ngbf.ms.util.FileUtil;
import com.kingstar.ngbf.ms.util.android.CommonConfig;
import com.kingstar.ngbf.ms.util.android.res.ResourceFactory;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * [框架]应用，APP入口
 * 
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

		// 初始化ImageLoader
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				this).build();
		ImageLoader.getInstance().init(config);

		// 初始化资源Factory
		ResourceFactory.init(this);

		// 初始化微信SDK
		WeixinApi.register(getApplicationContext());

		// 初始化百度SDK
		// BaiduFactory.initSDK(getApplicationContext());
	}

	/**
	 * 保存App二维码到SD,分享用
	 */
	@SuppressWarnings({ "unused", "deprecation" })
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
		CommonConfig.initParam(
				CommonConfig.KEY_INT_DIALOG_VIEW_ID,
				R.layout.sys_command_dialog);
		CommonConfig.initParam(
				CommonConfig.KEY_INT_DIALOG_LAYOUT_ID,
				R.id.ll_command_dialog);
	}
}
