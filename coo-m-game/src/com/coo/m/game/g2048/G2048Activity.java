package com.coo.m.game.g2048;

import com.coo.m.game.GameProperty;
import com.coo.m.game.GplusActivity;
import com.coo.m.game.GplusManager;
import com.coo.m.game.IGame;
import com.coo.m.game.IGamePolicy;
import com.coo.m.game.R;
import com.kingstar.ngbf.ms.util.Reference;

/**
 * 2048的activityaaaa  111222333
 * 
 */
public class G2048Activity extends GplusActivity {

	private G2048View gameView = null;

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
		gameView = (G2048View) findViewById(R.id.gv_2048_gameView);
//		gameView = new G2048View(this);
		gameView.setG2048(this);

		// 游戏启动
		notify(IGame.GAME_INIT);
	}

	@Override
	@Reference(override = GplusActivity.class)
	public void refreshUI() {
		gameView.initGameView();
	}

}
