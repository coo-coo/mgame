package com.coo.m.game.util;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * 页面中链接，如果希望点击链接继续在当前browser中响应
 * @author boqing.shen
 * @since 1.3
 */
public class InnerWebViewClient extends WebViewClient{

	public InnerWebViewClient() {
	}
	
	@Override
	public boolean shouldOverrideUrlLoading(WebView view,
			String url) {
		view.loadUrl(url);
		return true;
	}
}
