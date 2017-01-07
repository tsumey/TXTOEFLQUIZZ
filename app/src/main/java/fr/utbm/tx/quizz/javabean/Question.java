package fr.utbm.tx.quizz.javabean;

public class Question {
	private int question_id;
	private String question_lib;
	private int theme_id;
	
	public Question(){
		
	}
	
	public Question(int id, String lib,int theme){
		this.question_id=id;
		this.question_lib=lib;
		this.theme_id=theme;
	}

	public int getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}

	public String getQuestion_lib() {
		return question_lib;
	}

	public void setQuestion_lib(String question_lib) {
		this.question_lib = question_lib;
	}

	public int getTheme_id() {
		return theme_id;
	}

	public void setTheme_id(int theme_id) {
		this.theme_id = theme_id;
	}

	@Override
	public String toString() {
		return "Question [question_id=" + question_id + ", question_lib="
				+ question_lib + ", theme_id=" + theme_id + "]";
	}
}
