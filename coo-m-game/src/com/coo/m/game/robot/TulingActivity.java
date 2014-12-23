package com.coo.m.game.robot;

import android.os.Handler;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.widget.TextView;

import com.coo.m.game.GameProperty;
import com.coo.m.game.GplusActivity;
import com.coo.m.game.GplusManager;
import com.coo.m.game.R;
import com.kingstar.ngbf.ms.util.Reference;
import com.kingstar.ngbf.ms.util.android.component.IShakeListener;

/**
 * [GAME]晃晃看看
 * 
 * @author boqing.shen
 * @since 1.2
 */
public class TulingActivity extends GplusActivity implements IShakeListener {

	private TulingTopicHandler h_xiaohua;
	private TulingTopicHandler h_miyu;
	private TulingTopicHandler h_naojin;
	private TulingTopicHandler h_gushi;

	@Override
	public void loadContent() {
		TextView tv_xiaohua = (TextView) this
				.findViewById(R.id.tv_tuling_xiaohua);
		h_xiaohua = new TulingTopicHandler(tv_xiaohua);

		TextView tv_miyu = (TextView) this
				.findViewById(R.id.tv_tuling_miyu);
		h_miyu = new TulingTopicHandler(tv_miyu);

		TextView tv_naojin = (TextView) this
				.findViewById(R.id.tv_tuling_naojin);
		h_naojin = new TulingTopicHandler(tv_naojin);

		TextView tv_gushi = (TextView) this
				.findViewById(R.id.tv_tuling_gushi);
		tv_gushi.setMovementMethod(new ScrollingMovementMethod());
		h_gushi = new TulingTopicHandler(tv_gushi);

		// tv_info.setAutoLinkMask(Linkify.WEB_URLS);
		// tv_info.setMovementMethod(LinkMovementMethod.getInstance());
		// // 注册监听晃动,上来晃一下
		this.registerShakeListener(this);
		onShake();
	}

	@Override
	public GameProperty getGameProperty() {
		return GplusManager.G_TULING;
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
		TulingHelper.pickTopic(h_xiaohua, "笑话");
		TulingHelper.pickTopic(h_miyu, "谜语");
		TulingHelper.pickTopic(h_naojin, "脑筋急转弯");
		TulingHelper.pickTopic(h_gushi, "故事");
	}
}

class TulingTopicHandler extends Handler {
	private TextView tv;

	public TulingTopicHandler(TextView tv) {
		this.tv = tv;
	}

	public void handleMessage(Message msg) {
		TulingResult result = (TulingResult) msg.obj;
		if (result != null) {
			tv.setText(result.toHtmlText(false));

		} else {
			tv.setText(TulingHelper.ERR_TEXT);
		}
	};

}
