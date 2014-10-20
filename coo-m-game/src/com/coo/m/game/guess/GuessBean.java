package com.coo.m.game.guess;


public class GuessBean {
	
	private String id;
	private String uri;
	private String[] answers;
	private double dis;
	
	
	public double getDis() {
		return dis;
	}
	public void setDis(double dis) {
		this.dis = dis;
	}
	public GuessBean(){
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String[] getAnswers() {
		return answers;
	}
	public void setAnswers(String[] answers) {
		this.answers = answers;
	}


}
