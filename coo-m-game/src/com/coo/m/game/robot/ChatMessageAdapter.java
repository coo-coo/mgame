package com.coo.m.game.robot;

import java.text.SimpleDateFormat;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.coo.m.game.R;
import com.coo.m.game.robot.ChatMessage.Type;

/**
 * 对话消息适配器:AdapterAdapter
 * http://stackoverflow.com/questions/8558732/listview-textview
 * -with-linkmovementmethod-makes-list-item-unclickable
 * 
 * @author boqing.shen
 * 
 */
@SuppressLint("SimpleDateFormat")
public class ChatMessageAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	private List<ChatMessage> mDatas;

	public ChatMessageAdapter(Context context, List<ChatMessage> mDatas) {
		mInflater = LayoutInflater.from(context);
		this.mDatas = mDatas;

	}

	@Override
	public int getCount() {
		return mDatas.size();
	}

	@Override
	public Object getItem(int positon) {
		return mDatas.get(positon);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public int getItemViewType(int position) {
		ChatMessage chatm = mDatas.get(position);
		if (chatm.getType() == Type.INCOMING) {
			return 0;
		}
		return 1;
	}

	@Override
	public int getViewTypeCount() {
		return 2;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ChatMessage chatmessage = mDatas.get(position);
		ViewHolder viewHolder = null;
		if (convertView == null) {
			if (getItemViewType(position) == 0) {
				// 收到机器人消息
				convertView = mInflater.inflate(
						R.layout.g_robot_from_msg, parent,
						false);
				viewHolder = new ViewHolder();
				viewHolder.mDate = (TextView) convertView
						.findViewById(R.id.tv_from_date);
				viewHolder.msg = (TextView) convertView
						.findViewById(R.id.tv_from_msg);

			} else {
				convertView = mInflater.inflate(
						R.layout.g_robot_to_msg, parent,
						false);
				viewHolder = new ViewHolder();
				viewHolder.mDate = (TextView) convertView
						.findViewById(R.id.tv_to_date);
				viewHolder.msg = (TextView) convertView
						.findViewById(R.id.tv_to_msg);
			}
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		SimpleDateFormat df = new SimpleDateFormat(
				"yyyy-MM-dd HH:MM:ss");
		viewHolder.mDate.setText(df.format(chatmessage.getDate()));

		viewHolder.msg.setAutoLinkMask(Linkify.ALL);
		viewHolder.msg.setMovementMethod(LinkMovementMethod.getInstance());
		// 暂时隐掉HTML
//		viewHolder.msg.setText(Html.fromHtml(chatmessage.getMsg()));
		viewHolder.msg.setText(chatmessage.getMsg());
		return convertView;
	}

	private final class ViewHolder {
		public TextView mDate;
		public TextView msg;
	}
}
