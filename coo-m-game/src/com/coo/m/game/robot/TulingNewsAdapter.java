package com.coo.m.game.robot;

import java.util.List;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.coo.m.game.GplusManager;
import com.coo.m.game.R;
import com.kingstar.ngbf.ms.util.android.CommonAdapter;
import com.kingstar.ngbf.ms.util.android.CommonItemHolder;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Account管理行Adapter
 * @author boqing.shen
 * @since 1.3
 * 
 */
public class TulingNewsAdapter extends CommonAdapter<TulingItem> {

	public TulingNewsAdapter(Activity parent, List<TulingItem> items,
			ListView composite) {
		super(parent, items, composite);
	}

	/**
	 * 返回控件布局
	 */
	public int getItemConvertViewId() {
		return R.layout.g_tuling_news_row;
	}

	@Override
	public CommonItemHolder initHolder(View convertView) {
		TulingNewsRowHolder holder = new TulingNewsRowHolder();
		holder.tv_title = (TextView) convertView
				.findViewById(R.id.tv_tuling_news_title);
		holder.tv_souce = (TextView) convertView
				.findViewById(R.id.tv_tuling_news_source);
		holder.iv_icon = (ImageView) convertView
				.findViewById(R.id.iv_tuling_news_icon);
		return holder;
	}

	@Override
	public void initHolderValue(CommonItemHolder ciHolder, TulingItem item) {
		TulingNewsRowHolder holder = (TulingNewsRowHolder) ciHolder;
		holder.tv_title.setText(item.getArticle());
		holder.tv_souce.setText("(" + item.getSource() + ")");
		// 显示图片..
		String iconUrl = item.getIcon();
		ImageLoader.getInstance().displayImage(iconUrl, holder.iv_icon,
				GplusManager.IMG_OPTIONS);
	}
}

class TulingNewsRowHolder extends CommonItemHolder {
	public TextView tv_title;
	public TextView tv_souce;
	public ImageView iv_icon;
}
