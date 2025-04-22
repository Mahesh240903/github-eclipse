package com.training.exam.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.training.exam.model.Question;

/**
 * Servlet implementation class GenerateQuestion
 */
@WebServlet("/GenerateQuestion")
public class GenerateQuestion extends HttpServlet {
	int questionIndex=0;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String subjectName =(String) session.getAttribute("subjectName");
		List<Question> subjectQuestions =(List<Question>) session.getAttribute("subjectQuestions");
		String choice = request.getParameter("choice");
		System.out.println(choice);
		Integer disableButtonAt = null;
		
		if(choice!=null && questionIndex > -1 && questionIndex < subjectQuestions.size()) {
			if(choice.equals("next")) {
				questionIndex++;
			}else if(choice.equals("previous")){
				questionIndex--;
			}
		}
		Question currentQuestion = subjectQuestions.get(questionIndex);
		session.setAttribute("currentQuestion", currentQuestion);
		
		response.sendRedirect("subjectPaper2.jsp?subject="+subjectName+"&question="+questionIndex);
	}
}
