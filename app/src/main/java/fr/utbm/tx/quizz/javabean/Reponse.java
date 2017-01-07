package fr.utbm.tx.quizz.javabean;

public class Reponse {
	private int question_id;
	private int rep_id;
	private String rep_lib;
	private int rep_true;
	private String rep_info;
	
	public Reponse(){
		
	}
	
	public Reponse(int question_id, int rep_id, String rep_lib, int rep_true,
			String rep_info) {
		super();
		this.question_id = question_id;
		this.rep_id = rep_id;
		this.rep_lib = rep_lib;
		this.rep_true = rep_true;
		this.rep_info = rep_info;
	}

	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public String getRep_lib() {
		return rep_lib;
	}
	public void setRep_lib(String rep_lib) {
		this.rep_lib = rep_lib;
	}

	public int getRep_true() {
		return rep_true;
	}

	public void setRep_true(int rep_true) {
		this.rep_true = rep_true;
	}

	public int getRep_id() {
		return rep_id;
	}

	public void setRep_id(int rep_id) {
		this.rep_id = rep_id;
	}

	@Override
	public String toString() {
		return "Reponse [question_id=" + question_id + ", rep_id=" + rep_id
				+ ", rep_lib=" + rep_lib + ", rep_true=" + rep_true + "]";
	}

	public String getRep_info() {
		return rep_info;
	}

	public void setRep_info(String rep_info) {
		this.rep_info = rep_info;
	}
}
