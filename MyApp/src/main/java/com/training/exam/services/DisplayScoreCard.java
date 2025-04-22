package com.training.exam.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.training.exam.model.Option;
import com.training.exam.model.Question;

/**
 * Servlet implementation class DisplayScoreCard
 */
@WebServlet("/DisplayScoreCard")
public class DisplayScoreCard extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		List<Question> subjectQuestions =(List<Question>) session.getAttribute("subjectQuestions");
		if(subjectQuestions==null){
			response.sendRedirect("home.jsp");
			return;
		}
		
		int score = 0;
		for(int i=0;i<subjectQuestions.size();i++) {
			int rightAnswer = 0;
			int totalRightAnswer=0;
			String[] values = request.getParameterValues("question" + i);
			if(values == null)	continue;
			Question question = subjectQuestions.get(i);
			List<Option> questionOption = question.getOptions();
			for(Option opt: questionOption) {
				if(opt.isRightAnswer()) totalRightAnswer++;
			}
			for(String ans: values) {
				int ansIdx = Integer.parseInt(ans);
				if(questionOption.get(ansIdx).isRightAnswer()) {
					rightAnswer++;
				}
			}
			if(rightAnswer==totalRightAnswer) {
				score++;
			}
		}
//		session.setAttribute("subjectQuestions","backToHome");
		response.sendRedirect("displayScore.jsp?score="+score);
	}


}
