package com.coo.m.game.robot;

public class TulingItem implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -802828532049472915L;

	public TulingItem() {
		// TODO Auto-generated constructor stub
	}
	
	private String article = "";
	private String source = "";
	private String detailurl = "";
	private String icon = "";
	
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDetailurl() {
		return detailurl;
	}
	public void setDetailurl(String detailurl) {
		this.detailurl = detailurl;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
}
