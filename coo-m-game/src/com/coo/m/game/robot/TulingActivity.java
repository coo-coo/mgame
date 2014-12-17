package com.coo.m.game.robot;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.Menu;
import android.widget.TextView;

import com.coo.m.game.GameProperty;
import com.coo.m.game.GplusActivity;
import com.coo.m.game.GplusManager;
import com.coo.m.game.IGamePolicy;
import com.coo.m.game.R;
import com.coo.m.game.SimpleGamePolicy;
import com.kingstar.ngbf.ms.util.Reference;
import com.kingstar.ngbf.ms.util.android.component.IShakeListener;

/**
 * 随便看看
 * 
 * @author boqing.shen
 * 
 */
public class TulingActivity extends GplusActivity implements IShakeListener {

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			TulingResult result = (TulingResult) msg.obj;
			if (result != null) {
				// 请求获得消息之后,进行展现
				// CharSequence charSequence = Html
				// .fromHtml(result.toHtmlText());
				// tv_info.setText(charSequence);
				// 暂时不支持Html.fromHtml();
				tv_info.setText(result.toHtmlText(false));
			}
		};
	};

	// 标题信息
	private TextView tv_info;

	@Override
	public void loadContent() {
		tv_info = (TextView) this.findViewById(R.id.tv_tuling_info);
		tv_info.setAutoLinkMask(Linkify.WEB_URLS);
		tv_info.setMovementMethod(LinkMovementMethod.getInstance());
		// 注册监听晃动
		this.registerShakeListener(this);
		// 上来选一个
		TulingHelper.randomPick(handler);
	}

	@Override
	public GameProperty getGameProperty() {
		return GplusManager.G_TULING;
	}

	@Override
	public IGamePolicy getGamePolicy() {
		return new SimpleGamePolicy();
	}

	@Override
	public int getResViewLayoutId() {
		return R.layout.g_tuling_activity;
	}

	@Override
	@Reference(override = GplusActivity.class)
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.notgame, menu);
		return true;
	}

	@Override
	@Reference(override = IShakeListener.class)
	public void onShake() {
		TulingHelper.randomPick(handler);
	}

}
