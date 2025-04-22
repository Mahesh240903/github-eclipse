package com.training.exam.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.training.exam.model.Option;
import com.training.exam.model.Question;

/**
 * Servlet implementation class CalculateScore
 */
@WebServlet("/CalculateScore")
public class CalculateScore extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Map<Integer, List<String>> answerTracker = (Map<Integer, List<String>>) session.getAttribute("answerTracker");
		List<Question> subjectQuestions =(List<Question>) session.getAttribute("subjectQuestions");
		System.out.println(answerTracker);
		int score=0;
		int totalRight=0;
		int rightOPtions=0;
		for(int i=0;i<subjectQuestions.size();i++) {
			Question question = subjectQuestions.get(i);
			List<Option> options = question.getOptions();
			List<String> myAnswers = answerTracker.get(i);
			if(myAnswers==null)	continue;
			System.out.println(myAnswers);
			for(Option opt: options) {
				if(opt.isRightAnswer())	
					totalRight++;
			}
			
			for(String idxOfOption: myAnswers) {
				Integer idxInteger = Integer.parseInt(idxOfOption);
				if(options.get(idxInteger).isRightAnswer()) {
					rightOPtions++;
				}
			}
			
			if(totalRight == rightOPtions) {
				score++;
			}
		}
		session.setAttribute("subjectQuestions", null);
		response.sendRedirect("displayScore.jsp?score="+score);
	}


}
