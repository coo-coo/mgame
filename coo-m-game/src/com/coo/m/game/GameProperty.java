package com.coo.m.game;

/**
 * Game 的属性对象
 * 
 * @author boqing.shen
 * @since 1.0
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
		this.help = this.label;
	}

	private GameProperty() {

	}

	/**
	 * 链式API
	 * 
	 * @since 1.3
	 */
	public static GameProperty blank() {
		return new GameProperty();
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
	/**
	 * 帮助信息
	 * 
	 * @since 1.2
	 */
	private String help = "";
	/**
	 * 作者信息
	 * 
	 * @since 1.3
	 */
	private String author = "";

	public Class<?> getActivityClass() {
		return activityClass;
	}

	public void setActivityClass(Class<GplusActivity> activityClass) {
		this.activityClass = activityClass;
	}

	/**
	 * Android应用ICON资源
	 */
	private int icon = 0;
	/**
	 * @since 1.3 Android应用布局资源
	 */
	private int layout = 0;

	/**
	 * 版本开始于
	 * 
	 * @since 1.3
	 */
	private String start = "1.0";

	/**
	 * 版本结束于
	 * 
	 * @since 1.3
	 */
	private String end = "";

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	/**
	 * 返回所在包名,作为应用的Key
	 * 
	 * @return
	 */
	public String getKey() {
		if(key==null){
			// 如果未指定,则用包名作为Key
			return activityClass.getPackage().getName();
		}
		return key;
	}
	
	private String key = null;
	
	/**
	 * 链式API
	 * 
	 * @since 1.3
	 */
	public GameProperty key(String key) {
		this.key = key;
		return this;
	}
	
	/**
	 * 链式API
	 * 
	 * @since 1.3
	 */
	public GameProperty key() {
		// 缺省用类名作为Key
		this.key = activityClass.getName();
		return this;
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

	public String getHelp() {
		return help;
	}

	public void setHelp(String help) {
		this.help = help;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getLayout() {
		return layout;
	}

	public void setLayout(int layout) {
		this.layout = layout;
	}

	// //////////////////////////////////////////////////////////

	/**
	 * 链式API
	 * 
	 * @since 1.3
	 */
	public GameProperty help(String help) {
		this.help = help;
		return this;
	}

	/**
	 * 链式API
	 * 
	 * @since 1.3
	 */
	public GameProperty icon(int icon) {
		this.icon = icon;
		return this;
	}

	/**
	 * 链式API
	 * 
	 * @since 1.3
	 */
	public GameProperty label(String label) {
		this.label = label;
		return this;
	}

	/**
	 * 链式API
	 * 
	 * @since 1.3
	 */
	public GameProperty version(String version) {
		this.version = version;
		return this;
	}

	/**
	 * 链式API
	 * 
	 * @since 1.3
	 */
	public GameProperty activityClass(Class<?> activityClass) {
		this.activityClass = activityClass;
		return this;
	}

	/**
	 * 链式API
	 * 
	 * @since 1.3
	 */
	public GameProperty author(String author) {
		this.author = author;
		return this;
	}

	/**
	 * 链式API
	 * 
	 * @since 1.3
	 */
	public GameProperty layout(int layout) {
		this.layout = layout;
		return this;
	}

	/**
	 * 链式API
	 * 
	 * @since 1.3
	 */
	public GameProperty start(String start) {
		this.start = start;
		return this;
	}

	/**
	 * 链式API
	 * 
	 * @since 1.3
	 */
	public GameProperty end(String end) {
		this.end = end;
		return this;
	}
}
