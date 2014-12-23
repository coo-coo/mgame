package com.coo.m.game.color;

import com.coo.m.game.GameProperty;
import com.coo.m.game.GplusActivity;
import com.coo.m.game.GplusManager;
import com.coo.m.game.IGamePolicy;
import com.kingstar.ngbf.ms.util.Reference;

/**
 * [GAME]点方块
 * @author boqing.shen
 * @since 1.3
 */
public class BlockActivity extends ColorActivity {

	@Override
	public GameProperty getGameProperty() {
		return GplusManager.G_BLOCK;
	}
	
	@Override
	@Reference(override = GplusActivity.class)
	public IGamePolicy getGamePolicy() {
		return new BlockPolicy();
	}
}
