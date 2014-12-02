package com.coo.m.game;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.TextView;

import com.kingstar.ngbf.ms.util.android.CommonBizActivity;

public class SysVersionActivity extends CommonBizActivity{

	
	private TextView versionTV=null;
	
	private TextView author1=null;
	private TextView author2=null;
	@Override
	public String getHeaderTitle() {
		return "关于";
	}

	@Override
	public int getResViewLayoutId() {
		return R.layout.sys_version_activity;
	}

	@Override
	public void loadContent() {

		versionTV=(TextView) findViewById(R.id.tv_sysversion_content );
		versionTV.setText(getVersion());
		
		author1=(TextView) findViewById(R.id.tv_sysauthor_1);
		author2=(TextView) findViewById(R.id.tv_sysauthor_2);
		author1.setText("作者邮箱：shenboqing@163.com");
		author2.setText("作者邮箱：lightby2@163.com");
	}
	
	
	/**
	 * 获取版本号
	 * @return 当前应用的版本号
	 */
	public String getVersion() {
	    try {
	        PackageManager manager = this.getPackageManager();
	        PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
	        String version = info.versionName;
	        return this.getString(R.string.version_name) + version;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return this.getString(R.string.can_not_find_version_name);
	    }
	}

}
