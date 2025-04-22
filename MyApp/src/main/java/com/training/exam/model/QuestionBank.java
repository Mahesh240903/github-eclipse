package com.training.exam.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class QuestionBank {

	private Map<String, List<Question>> questionBank;// = new HashMap<String, List<Question>>();
	List<String> subj = new ArrayList<String>();
	Random random = new Random();
	
	
	public QuestionBank() {
		questionBank = new HashMap<String, List<Question>>();
	}
	
	public void addNewSubject(String subject) {
		questionBank.put(subject, new ArrayList<Question>());
		subj.add(subject);
	}
	
	public List<String> getSubject() {
		return subj;
	}

	public void addNewQuestion(String subject, Question question) {
		List<Question> questions = questionBank.get(subject);
		questions.add(question);
	}
	
	public List<Question> getQuestionsFor(String subject) {
		Set<Question> questionsToSend = new HashSet<Question>();
		List<Question> questions = questionBank.get(subject);
//		for(int i=0;i<3;i++) {
//			int randomIndex = random.nextInt(questions.size());
//			if(!questionsToSend.contains(questions.get(randomIndex))) {
//				questionsToSend.add(questions.get(randomIndex));
//			}
//			else
//				i--;
//		}
		if(questions.size() <= 3) {
			return questions;
		}
		while(questionsToSend.size() < 3) {
			int randomIndex = random.nextInt(questions.size());
			questionsToSend.add(questions.get(randomIndex));
		}
		return new ArrayList<>(questionsToSend);
	}
}
