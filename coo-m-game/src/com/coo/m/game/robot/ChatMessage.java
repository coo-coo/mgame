package com.coo.m.game.robot;

import java.util.Date;

/**
 * 对话消息
 * 
 * @author boqing.shen
 * 
 */
public class ChatMessage {

	private String name;
	private String msg;
	private Type type;
	private Date date;

	public enum Type {
		INCOMING, OUTCONMING
	}

	public ChatMessage() {

	}

	public ChatMessage(String msg, Type type, Date date) {
		super();
		this.msg = msg;
		this.type = type;
		this.date = date;
	}

	public ChatMessage(String msg, Type type) {
		this(msg, type, new Date());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
