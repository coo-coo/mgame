package com.coo.m.game.robot;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.coo.m.game.GameProperty;
import com.coo.m.game.GplusActivity;
import com.coo.m.game.GplusManager;
import com.coo.m.game.R;
import com.coo.m.game.robot.ChatMessage.Type;
import com.kingstar.ngbf.ms.util.Reference;

/**
 * [GAME]对话机器人
 * 
 * @author ming.wang
 * @since 1.1
 */
public class TulingRobotActivity extends GplusActivity {
	private ListView lvMsg;
	private ChatMessageAdapter mAdapter;
	private List<ChatMessage> mDatas;

	private Button btnSend;
	private EditText mInput;

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			TulingResult result = (TulingResult) msg.obj;
			ChatMessage chatMessage = null;
			if (result != null) {
				chatMessage = new ChatMessage(
						result.toHtmlText(false),
						Type.INCOMING);
			} else {
				chatMessage = new ChatMessage(
						TulingHelper.ERR_TEXT,
						Type.INCOMING);
			}
			mDatas.add(chatMessage);
			mAdapter.notifyDataSetChanged();
		};
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_robot_send:
			// 发送消息
			sendMsg();
			break;
		}
	}

	private void sendMsg() {
		String tomsg = mInput.getText().toString();
		if (TextUtils.isEmpty(tomsg)) {
			toast("发送消息不能为空");
			return;
		}

		// 添加请求消息
		ChatMessage toMsg = new ChatMessage(tomsg, Type.OUTCONMING);
		mDatas.add(toMsg);
		mAdapter.notifyDataSetChanged();

		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

		// 发送消息,交由Handler处理
		TulingHelper.sendMessage(tomsg, handler);

	}

	@Override
	public void loadContent() {
		lvMsg = (ListView) findViewById(R.id.lv_main_msgs);
		mInput = (EditText) findViewById(R.id.et_main_input);
		mInput.setText("新闻");

		btnSend = (Button) findViewById(R.id.btn_robot_send);
		btnSend.setOnClickListener(this);

		mDatas = new ArrayList<ChatMessage>();
		mDatas.add(new ChatMessage(TulingHelper.WELCOME_TEXT,
				Type.INCOMING));
		mAdapter = new ChatMessageAdapter(this, mDatas);
		lvMsg.setAdapter(mAdapter);

		// TextView textView = new TextView(this);
		// textView.setText("对话消息...");
		// lvMsg.addHeaderView(textView);
	}

	@Override
	@Reference(override = GplusActivity.class)
	public GameProperty getGameProperty() {
		return GplusManager.G_TULING_ROBOT;
	}

	@Override
	@Reference(override = GplusActivity.class)
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.notgame, menu);
		return true;
	}
}
