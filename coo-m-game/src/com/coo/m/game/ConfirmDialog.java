package com.coo.m.game;

import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * 征询事件Dialog，获得确认，发送指定消息
 * 
 * @author boqing.shen
 * 
 */
public class ConfirmDialog implements DialogInterface.OnClickListener {

	private GplusActivity gplus;

	private int event;

	public ConfirmDialog(GplusActivity gplus, String title, int event) {
		this.gplus = gplus;
		this.event = event;
		invokeComfirmDialog(title);
	}

	private void invokeComfirmDialog(String title) {
		new AlertDialog.Builder(gplus).setCancelable(false)
				.setTitle(title).setPositiveButton("确定", this)
				.setIcon(R.drawable.gplus_32)
				.setNegativeButton("取消", this).show();
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		if (which == AlertDialog.BUTTON_POSITIVE) {
			gplus.notify(event);
		}
	}
}