package com.coo.m.game.robot;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.webkit.WebView;

import com.coo.m.game.R;
import com.coo.m.game.util.InnerWebViewClient;
import com.kingstar.ngbf.ms.util.android.CommonBizActivity;

/**
 * [框架]新闻内容显示
 * 
 * @author boqing.shen
 * @since 1.3
 * 
 */
public class TulingNewsInfoActivity extends CommonBizActivity {

	@Override
	public String getHeaderTitle() {
		return "晃晃看看";
	}

	@Override
	public int getResViewLayoutId() {
		return R.layout.g_tuling_news_info_activity;
	}

	private TulingItem item;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public void loadContent() {
		Intent intent = getIntent();
		item = ((TulingItem) intent.getSerializableExtra("TULING_ITEM"));
		WebView webview = (WebView) findViewById(R.id.wv_tuling_news);
		// 页面中链接，如果希望点击链接继续在当前browser中响应
		webview.setWebViewClient(new InnerWebViewClient());
		if (item != null) {
			webview.loadUrl(item.getDetailurl());
		}
	}
}
