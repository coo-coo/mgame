package com.coo.m.game.guess;



public class GuessManager {

	public  GuessManager(){
		
	}
	
	public GuessBean getGuessModel(){
		GuessBean gm=new GuessBean();
		return gm;
	}
	
	public GuessBean mockGuessModel(){
		return sgb;
	}
	
	private static GuessBean sgb=new GuessBean();
	
	static {
		sgb.setAnswers(new String[]{"孙娜恩","娜恩"});
		sgb.setUri("sunnaen");
		sgb.setDis(200);
	}
	
}