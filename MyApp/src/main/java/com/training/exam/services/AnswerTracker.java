package com.training.exam.services;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.training.exam.model.Question;

/**
 * Servlet implementation class AnswerTracker
 */
@WebServlet("/AnswerTracker")
public class AnswerTracker extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		List<Question> subjectQuestions =(List<Question>) session.getAttribute("subjectQuestions");
		if(subjectQuestions==null){
			response.sendRedirect("home.jsp");
			return;
		}
//		if(session.getAttribute("subjectQuestions")==null) {
//			response.sendRedirect("home.jsp");
//			return;
//		}
		Map<Integer, List<String>> answerTracker = (Map<Integer, List<String>>) session.getAttribute("answerTracker");
		String[] answerList = request.getParameterValues("question");
		String submit = request.getParameter("submit");
		System.out.println(submit);
		
		Integer questionIndex = Integer.parseInt(request.getParameter("questionIdx"));
		System.out.println(questionIndex);
		if(answerList==null) {
			answerTracker.put(questionIndex,null);
		}else {
			answerTracker.put(questionIndex,Arrays.asList(answerList));
		}
		System.out.println(answerTracker);
		if(submit!=null && submit.equals("true")) {
			response.sendRedirect("CalculateScore");
			return;
		}
		session.setAttribute("answerTracker", answerTracker);
		Integer totalQuestion = (Integer) session.getAttribute("totalQuestions");
		String choice = request.getParameter("choice");
		response.sendRedirect("GenerateQuestion?choice="+choice);
	}

}
