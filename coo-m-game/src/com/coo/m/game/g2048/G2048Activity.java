package com.coo.m.game.g2048;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coo.m.game.GameProperty;
import com.coo.m.game.GplusActivity;
import com.coo.m.game.GplusManager;
import com.coo.m.game.IGame;
import com.coo.m.game.IGamePolicy;
import com.coo.m.game.R;
import com.kingstar.ngbf.ms.util.Reference;

/**
 * [GAME]2048
 * @author ming.wang
 * @since 1.0
 */
public class G2048Activity extends GplusActivity {

	private G2048View gameView = null;
	private TextView score = null;
	private RelativeLayout container;

	@Override
	@Reference(override = GplusActivity.class)
	public GameProperty getGameProperty() {
		return GplusManager.G_G2048;
	}

	@Override
	@Reference(override = GplusActivity.class)
	public IGamePolicy getGamePolicy() {
		return new G2048Policy();
	}

	@Override
	public void loadContent() {
		// 初始化gameView
		// gameView = (G2048View) findViewById(R.id.gv_2048_gameView);
		container = (RelativeLayout) findViewById(R.id.rl_2048_container);
		score = (TextView) findViewById(R.id.tv_2048_game_score);
		gameView = new G2048View(this);

		score.setText(getString(R.string.g_2048_current_score) + "0");
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		gameView.setId(10);
		container.addView(gameView, lp);

		// 游戏启动
		notify(IGame.GAME_INIT);
	}

	@Override
	@Reference(override = GplusActivity.class)
	public void refreshUI() {
		gameView.startGame();
	}

	@SuppressLint("HandlerLeak")
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 100:
				score.setText(getString(R.string.g_2048_current_score)
						+ msg.obj.toString());
				break;
			}
			super.handleMessage(msg);
		}
	};

}
