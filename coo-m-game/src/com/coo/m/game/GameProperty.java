package com.coo.m.game;

/**
 * Game 的属性对象
 * @author boqing.shen
 *
 */
public final class GameProperty {
	
	/**
	 * 构造函数
	 */
	public GameProperty(Class<?> activityClass, String label, int icon) {
		this.activityClass = activityClass;
		this.label = label;
		this.icon = icon;
	}

	/**
	 * 名称
	 */
	private String label = "";
	/**
	 * 版本
	 */
	private String version = "1.0.0";
	/**
	 * 主类，用于SysMain上的适配器进行跳转
	 */
	private Class<?> activityClass;

	public Class<?> getActivityClass() {
		return activityClass;
	}

	public void setActivityClass(Class<GplusActivity> activityClass) {
		this.activityClass = activityClass;
	}

	/**
	 * 应用ICON资源
	 */
	private int icon = 0;

	/**
	 * 返回所在包名,作为应用的Key
	 * 
	 * @return
	 */
	public String getKey() {
		// 返回所在包名,作为应用的Key
		return activityClass.getPackage().getName();
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}
}
