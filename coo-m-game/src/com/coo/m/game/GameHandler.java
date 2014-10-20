package com.coo.m.game;

import android.os.Handler;
import android.os.Message;

public class GameHandler extends Handler {

	private GplusActivity game;

	public GameHandler(GplusActivity activity) {
		this.game = activity;
	}

	@Override
	public void handleMessage(Message msg) {
		int what = msg.what;
		switch (what) {
		case GameState.PASS_INIT:
			game.onMissionInit();
			break;
		case GameState.PASS_SUCCESS:
			game.onMissionSuccess();
			break;
		case GameState.PASS_FAIL:
			game.onMissionFail();
			break;
		case GameState.PASS_GIVEUP:
			game.onMissionGiveup();
			break;
		}
	}
	
	
	public void notifySuccess(){
		this.sendEmptyMessage(GameState.PASS_SUCCESS);
	}
	
	public void notifyFail(){
		this.sendEmptyMessage(GameState.PASS_FAIL);
	}
}
