package com.coo.m.game.g2048;

import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;

import com.coo.m.game.GameProperty;
import com.coo.m.game.GplusActivity;
import com.coo.m.game.GplusManager;
import com.coo.m.game.IGame;
import com.coo.m.game.IGamePolicy;
import com.coo.m.game.R;
import com.kingstar.ngbf.ms.util.Reference;

/**
 * 2048
 * 
 */
public class G2048Activity extends GplusActivity {

	private G2048View gameView = null;
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
	public int getResViewLayoutId() {
		return R.layout.g2048_activity;
	}

	@Override
	public void loadContent() {
		// 初始化gameView
		// gameView = (G2048View) findViewById(R.id.gv_2048_gameView);
		container = (RelativeLayout) findViewById(R.id.rl_2048_container);
		gameView = new G2048View(this);
		container.addView(gameView, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		toast("loadContent..");
		// 游戏启动
		notify(IGame.GAME_INIT);
	}

	@Override
	@Reference(override = GplusActivity.class)
	public void refreshUI() {
		// container.removeAllViews();

		gameView.startGame();

	}

}
