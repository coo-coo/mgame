package com.coo.m.game.robot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.coo.m.game.GameProperty;
import com.coo.m.game.GplusActivity;
import com.coo.m.game.GplusManager;
import com.coo.m.game.IGamePolicy;
import com.coo.m.game.R;
import com.coo.m.game.robot.ChatMessage.Type;

public class RobotActivity extends GplusActivity {
	private ListView mMsg;
	private ChatMessageAdapter mAdapter;
	private List<ChatMessage> mDatas;

	private Button mBtn;
	private EditText mInput;

	@SuppressLint("HandlerLeak")
	private Handler mhandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			ChatMessage frommsg = (ChatMessage) msg.obj;
			mDatas.add(frommsg);
			mAdapter.notifyDataSetChanged();
		};
	};

	private void initListener() {
		mBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final String tomsg = mInput.getText().toString();
				if (TextUtils.isEmpty(tomsg)) {
					Toast.makeText(RobotActivity.this, "发送消息不能为空",
							Toast.LENGTH_SHORT).show();
					return;
				}

				ChatMessage tomesssage = new ChatMessage();
				tomesssage.setMsg(tomsg);
				tomesssage.setDate(new Date());
				tomesssage.setType(Type.OUTCONMING);
				mDatas.add(tomesssage);
				mAdapter.notifyDataSetChanged();

				mInput.setText("");

				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
				new Thread() {
					public void run() {
						ChatMessage frommsg = HttpUtils.sendMessage(tomsg);
						Message m = Message.obtain();
						m.obj = frommsg;
						mhandler.sendMessage(m);
					};
				}.start();

			}
		});

	}

	private void initDatas() {
		mDatas = new ArrayList<ChatMessage>();
		mDatas.add(new ChatMessage("亲爱的你好", Type.INCOMING, new Date()));
		mAdapter = new ChatMessageAdapter(this, mDatas);

		mMsg.setAdapter(mAdapter);
	}

	private void initView() {
		mMsg = (ListView) findViewById(R.id.lv_main_msgs);
		mInput = (EditText) findViewById(R.id.et_main_input);
		mBtn = (Button) findViewById(R.id.b_main_sendmsg);

	}


	@Override
	public GameProperty getGameProperty() {
		return GplusManager.G_ROBOT;
	}

	@Override
	public IGamePolicy getGamePolicy() {
		return new RobotPolicy();
	}

	@Override
	public int getResViewLayoutId() {
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		return R.layout.robot_activity;
	}

	@Override
	public void loadContent() {
		initView();
		initDatas();
		initListener();
	}

}
