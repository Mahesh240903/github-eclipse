package com.training.exam.model;

import java.util.List;

public class Question {

	private String question;
	private Boolean multipleAnswer=false;
	private List<Option> options;
	
	public Question(String question) {
		this.question = question;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}
	
	public void setMultipleAnswer() {
		multipleAnswer = !multipleAnswer;
	}
	
	public Boolean getMultipleAnswer() {
		return multipleAnswer;
	}
}
