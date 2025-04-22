<%@page import="com.training.exam.model.Question"%>
<%@page import="java.util.List"%>
<%@page import="com.training.exam.model.QuestionBankLoader"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<%
	String url = "ProxyServerServlet";
	System.out.println("Working -> home.jsp");
	QuestionBankLoader qbLoader =(QuestionBankLoader) session.getAttribute("QBLoader");
	if(qbLoader == null){
		qbLoader = new QuestionBankLoader();
		qbLoader.loadQuestionsOn();
		session.setAttribute("QBLoader", qbLoader);
	}
	List<String> subjects = qbLoader.qb.getSubject();
%>

<h1 class="title">Online MCQ Platform (Github)(Dev1)(Dev1-2nd try)(3-try)</h1>
	<div class="card-container">
	<% 	for(String subjectName: subjects){ %>	
			<div class="card" onclick="document.location.href='<%=url %>?url=LoadSubjectQuestions?subject=<%= subjectName %>'">
    			<%= subjectName %>
			</div>
	<%	} %>

</body>
</html>