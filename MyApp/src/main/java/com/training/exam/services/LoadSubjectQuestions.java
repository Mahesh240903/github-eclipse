package com.training.exam.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.training.exam.model.Question;
import com.training.exam.model.QuestionBankLoader;

/**
 * Servlet implementation class LoadSubjectQuestions
 */
@WebServlet("/LoadSubjectQuestions")
public class LoadSubjectQuestions extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		
		String subjectName = request.getParameter("subject");
		HttpSession  session = request.getSession();
		QuestionBankLoader qbLoader =(QuestionBankLoader) session.getAttribute("QBLoader");		
		if(qbLoader==null) {
			response.sendRedirect("home.jsp");
			return;
		}
		Map<Integer, List<String>> mapOfAnswers = new HashMap<Integer, List<String>>();
		
		List<Question> subjectQuestions = qbLoader.qb.getQuestionsFor(subjectName);
		for(int i=0;i<subjectQuestions.size();i++) {
			mapOfAnswers.put(i, null);
		}
		session.setAttribute("subjectQuestions", subjectQuestions);
		session.setAttribute("subjectName", subjectName);
		session.setAttribute("totalQuestions", subjectQuestions.size());
		session.setAttribute("answerTracker", mapOfAnswers);
		response.sendRedirect("GenerateQuestion?subject="+subjectName);
	}
}
