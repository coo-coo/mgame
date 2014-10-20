package com.coo.m.game;

import com.kingstar.ngbf.ms.util.android.CommonBizActivity;

public abstract class GplusActivity extends CommonBizActivity {

	public abstract GameProperty getGameProperty();

	public void score(int score) {
		@SuppressWarnings("unused")
		GameScore gs = new GameScore(getGameProperty(), score);
		// gs.setTs(System.currentTimeMillis());
		// gs.setHost();
		// socre.save();
	}

	@Override
	public String getHeaderTitle() {
		return getGameProperty().getLabel();
	}

}
