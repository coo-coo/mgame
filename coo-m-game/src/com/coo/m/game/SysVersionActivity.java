package com.coo.m.game;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.TextView;

import com.kingstar.ngbf.ms.util.android.CommonBizActivity;

public class SysVersionActivity extends CommonBizActivity {

	private TextView versionTV = null;

	private TextView author1 = null;
	private TextView author2 = null;

	private TextView intro = null;

	@Override
	public String getHeaderTitle() {
		return "关于消磨";
	}

	@Override
	public int getResViewLayoutId() {
		return R.layout.sys_version_activity;
	}

	@Override
	public void loadContent() {

		versionTV = (TextView) findViewById(R.id.tv_sysversion_content);
		versionTV.setText(getVersion());

		author1 = (TextView) findViewById(R.id.tv_sysauthor_1);
		author2 = (TextView) findViewById(R.id.tv_sysauthor_2);
		author1.setText("作者邮箱:shenboqing@163.com");
		author2.setText("作者邮箱:lightby2@163.com");

		intro = (TextView) findViewById(R.id.tv_sysintro);
		intro.setText("关于消磨:等车太无聊了吗?那就来消磨无聊时间吧.消磨是一个小游戏的集合,目的是为了将各式各样的不同创意的小游戏集成进来,为大家在各种闲暇时候提供多样化的娱乐消遣.同时将不同的游戏进行积分化,可轻易了解你的最强游戏.向小伙伴们炫耀吧!将不定时的添加各种新的游戏,或者改进现有主流游戏来提供给大家,可以不停的期待下一个会是什么哦!");
	}

	/**
	 * 获取版本号
	 * 
	 * @return 当前应用的版本号
	 */
	public String getVersion() {
		try {
			PackageManager manager = this.getPackageManager();
			PackageInfo info = manager.getPackageInfo(
					this.getPackageName(), 0);
			String version = info.versionName;
			return this.getString(R.string.version_name) + version;
		} catch (Exception e) {
			e.printStackTrace();
			return this.getString(R.string.can_not_find_version_name);
		}
	}

}
