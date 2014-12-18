package com.coo.m.game;

import android.annotation.SuppressLint;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.kingstar.ngbf.ms.util.android.CommonBizActivity;

/**
 * [框架]关于消磨,采用Baidu请应用形式实现,加载请应用网页地址
 * @author boqing.shen
 * @since 1.2
 *
 */
public class SysAboutActivity extends CommonBizActivity {

	@Override
	public String getHeaderTitle() {
		return "关于消磨";
	}

	@Override
	public int getResViewLayoutId() {
		return R.layout.sys_about_activity;
	}

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public void loadContent() {
		WebView webview = (WebView) findViewById(R.id.wv_about);

		webview.getSettings().setJavaScriptEnabled(true);
		// 触摸焦点起作用
		webview.requestFocus();
		webview.loadUrl(GplusManager.QING_ABOUT_URL);

		// 页面中链接，如果希望点击链接继续在当前browser中响应
		webview.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view,
					String url) {
				view.loadUrl(url);
				return true;
			}
		});
		// 取消滚动条
		// webview.setScrollBarStyle(SCROLLBARS_OUTSIDE_OVERLAY);
	}
}
