package com.coo.m.game.robot;

import java.util.ArrayList;
import java.util.List;

/**
 * 发送请求至之后,获得Message消息,参见:http://www.tuling123.com/openapi/cloud/access_api.jsp#
 * type
 * 
 * @author boqing.shen
 * 
 */
public final class TulingResult {

	/**
	 * 消息代码
	 */
	private int code;
	/**
	 * 消息文本
	 */
	private String text;
	/**
	 * 直接连接地址,暂不支持
	 */
	private String url;
	/**
	 * 请求消息
	 */
	private String reqMsg = "";
	
	private List<TulingItem> list = new ArrayList<TulingItem>();

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<TulingItem> getList() {
		return list;
	}

	public void setList(List<TulingItem> list) {
		this.list = list;
	}
	
	public String getReqMsg() {
		return reqMsg;
	}

	public void setReqMsg(String reqMsg) {
		this.reqMsg = reqMsg;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * 返回结果文本,在TextView内尽心显示
	 * @param html
	 * @return
	 */
	public String toHtmlText(boolean html) {
		StringBuffer sb = new StringBuffer();
		int size = getList().size();
		if (size > 0) {
			for (TulingItem ti : list) {
				String txt = ti.getArticle() + " (来自:"+ti.getSource()+")\n"
						+ ti.getDetailurl() + "\n\n";
				if (html) {
					txt = "<a href=\"" + ti.getDetailurl()
							+ "\">"
							+ ti.getArticle()
							+ "</a>&nbsp;&nbsp;("
							+ ti.getSource()
							+ ")<p/>";
				}
				sb.append(txt);
			}
		} else {
			sb.append(getText());
		}
		return sb.toString();
	}
}
