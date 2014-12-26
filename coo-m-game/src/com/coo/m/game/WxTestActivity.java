package com.coo.m.game;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;

import com.coo.ms.cloud.model.NetLink;
import com.coo.ms.cloud.model.NetText;
import com.coo.ms.cloud.weixin.WeixinHandler;
import com.kingstar.ngbf.ms.util.android.CommonBizActivity;
import com.kingstar.ngbf.ms.util.android.res.ResourceFactory;

public class WxTestActivity extends CommonBizActivity {

	private void doWxShareText() {
		NetText nt = new NetText("测试42..." + System.currentTimeMillis());
		wxHandler.share(nt);
	}

	private void doWxShareLink() {
		String url = "http://lightapp.baidu.com/?appid=1568236";
		Bitmap thumb = ResourceFactory.getBitmap(R.drawable.gplus_32);
		NetLink nl = new NetLink("来玩消磨吧", url, thumb);
		nl.setDescription("我玩点四下得分1001分呢");
		wxHandler.share(nl);
	}

	/**
	 * 采用WeixinHandler次线程发送
	 */
	private WeixinHandler wxHandler = null;

	@Override
	public String getHeaderTitle() {
		return "SDK测试3";
	}

	@Override
	public int getResViewLayoutId() {
		return R.layout.sys_cloud_activity;
	}

	@Override
	public void loadContent() {
		Button btnWxText = (Button) findViewById(R.id.btn_wx_share_text);
		btnWxText.setOnClickListener(this);
		Button btnWxLink = (Button) findViewById(R.id.btn_wx_share_link);
		btnWxLink.setOnClickListener(this);
		// 构造....
		wxHandler = new WeixinHandler(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_wx_share_text:
			doWxShareText();
			break;
		case R.id.btn_wx_share_link:
			doWxShareLink();
			break;
		}
	}
}