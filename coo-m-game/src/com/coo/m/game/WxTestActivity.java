package com.coo.m.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.coo.ms.cloud.model.NetLink;
import com.coo.ms.cloud.model.NetText;
import com.coo.ms.cloud.weixin.WeixinApi;
import com.kingstar.ngbf.ms.util.android.CommonBizActivity;
import com.kingstar.ngbf.ms.util.android.res.ResourceFactory;

public class WxTestActivity extends CommonBizActivity {

	// @SuppressLint("HandlerLeak")
	// private Handler handler = new Handler() {
	//
	// };

	private void doWxShareText() {
		NetText nt = new NetText("测试4..." + System.currentTimeMillis());
		// WeixinApi.share(this, nt);
		Message msg = GplusManager.createMessage(1000, nt);
		wxHandler.sendMessage(msg);
	}

	private void doWxShareLink() {
		String url = "http://lightapp.baidu.com/?appid=1568236";
		Bitmap thumb = ResourceFactory.getBitmap(R.drawable.gplus_32);
		NetLink nl = new NetLink("来玩消磨吧", url, thumb);
		nl.setDescription("我玩点四下得分1001分呢");
		// WeixinApi.share(this, nl);
		Message msg = GplusManager.createMessage(1000, nl);
		wxHandler.sendMessage(msg);
	}

	private WxHandler wxHandler = null;

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

		wxHandler = new WxHandler(this);
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

class WxHandler extends Handler {
	private Context context;

	public WxHandler(Context context) {
		this.context = context;
	}

	@Override
	public void handleMessage(Message msg) {
		if (msg.obj instanceof NetText) {
			NetText nt = (NetText) msg.obj;
			WeixinApi.share(context, nt);
		} else if (msg.obj instanceof NetLink) {
			NetLink nl = (NetLink) msg.obj;
			WeixinApi.share(context, nl);
		} else {

		}
	};
}