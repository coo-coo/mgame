package com.coo.m.game.guess;


import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class GuessShow extends RelativeLayout {
	Context context;
	GuessBean gb;
	RelativeLayout rubblerBG;
	RubblerShow rubblerShow;

	int rubblerBGId = 10001;

	public GuessShow(Context context,GuessBean gb) {
		super(context);
		this.context = context;
		this.gb = gb;
		getElement();
		setElementLP();
		setElementStyle();
		setElement();
	}

	private void getElement() {
		rubblerBG = new RelativeLayout(context);
		rubblerShow = new RubblerShow(context, handler);

		rubblerBG.setId(rubblerBGId);
		rubblerBG.addView(rubblerShow);
		addView(rubblerBG);
	}

	private void setElementLP() {

		RelativeLayout.LayoutParams rubblerBG_LP = new RelativeLayout.LayoutParams(
				250, 300);

		rubblerBG.setLayoutParams(rubblerBG_LP);
		rubblerShow.setLayoutParams(rubblerBG_LP);

		// RelativeLayout.LayoutParams getReward_LP = new LayoutParams(-2, -2);
		// getReward_LP.addRule(RelativeLayout.CENTER_HORIZONTAL);
		// getReward_LP.addRule(RelativeLayout.BELOW,rubblerBGId);
		// getReward.setLayoutParams(getReward_LP);

	}

	private void setElementStyle() {
		// switch (level) {
		//
		// break;
		// }
		int resID = getResources().getIdentifier(gb.getUri(), "drawable",
				context.getPackageName());
		rubblerBG.setBackgroundResource(resID);
	}

	private void setElement() {
		rubblerShow.beginRubbler(Color.parseColor("#d3d3d3"), 30, 10);

	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what == 200) {
			} else {
				Toast.makeText(context, "已到最大长度", Toast.LENGTH_SHORT).show();
			}

		}

	};

}
