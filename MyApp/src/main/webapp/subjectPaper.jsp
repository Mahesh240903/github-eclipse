<%@page import="com.training.exam.model.Option"%>
<%@page import="com.training.exam.model.Question"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="questionPaper.css">
<style type="text/css">
.timer-container {
  position: fixed;
  top: 20px;
  right: 20px;
  background-color: #333;
  color: #fff;
  padding: 10px 20px;
  font-size: 24px;
  font-family: Arial, sans-serif;
  border-radius: 8px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
  z-index: 9999;
  display: flex;
  align-items: center;
  justify-content: center;
  opacity: 0;
}

#timeLeft {
  width: 70px;
  font-weight: bold;
}
</style>
<script>
	function setTimer(){
		let timeLeft = 60;
		let minutes = document.getElementById("minutes");
		let seconds = document.getElementById("seconds");
		let countdownTimer = setInterval(function(){
			if(timeLeft > -1){
				const min = Math.floor(timeLeft/60);
				const sec = Math.floor(timeLeft%60);
				const timeRemaining = timeLeft--;
				minutes.innerHTML = min;
				seconds.innerHTML = sec;
				console.log(timeRemaining);
			}else{
				clearInterval(countdownTimer);
				document.getElementById("myQuestionForm").submit();	
			}
		},1000);
		setTimeout(() => {
			document.querySelector(".timer-container").style.opacity = 1;			
		}, 1000);
		check();
	}

	function check(){
	document.querySelectorAll('.question').forEach(elem =>{
		const type = elem.dataset.type;
		const checkBoxes = elem.querySelectorAll('input[type="checkbox"]');
		console.log(checkBoxes);
		if(type == 'false'){
			checkBoxes.forEach(checkBox => {
				checkBox.addEventListener('change',()=>{
					checkBoxes.forEach(cb => {
						if(cb!=checkBox)	cb.checked = false;
					})
				})
				
			})
		}
	});
	}
	
	function validate(){
		let valid = true;
		Array.from(document.querySelectorAll('.question')).every(elem =>{
			const checkBoxes = elem.querySelectorAll('input[type="checkbox"]');
			const optionSelected = Array.from(checkBoxes).some(cb => cb.checked);
			if(!optionSelected){
				valid = false;
				alert("Attempt all questions");
				return false;
			}
		});
		if(valid){
		console.log("Submit2")
		document.getElementById("myQuestionForm").submit();			
		}
	}
</script>
</head>


<body onload="setTimer()">
	<div class="timer-container">
    	<div id="timeLeft"><span id="minutes"></span> : <span id="seconds"></span> </div>
  	</div>
	<%
		List<Question> subjectQuestions =(List<Question>) session.getAttribute("subjectQuestions");
		if(subjectQuestions==null){
			response.sendRedirect("home.jsp");
			return;
		}
		String subjectName = request.getParameter("subject");
	%>
	
  	
	<h1>Question for <%=subjectName %></h1>
	<form method="get" action="DisplayScoreCard" id="myQuestionForm">
	<div class="question-container">
	<%
		for(int i=0;i<subjectQuestions.size();i++){
			Question q = subjectQuestions.get(i);
			Boolean questionType = q.getMultipleAnswer();
	%>
	
		<div class="question" data-type="<%=questionType %>">
			<%=q.getQuestion() %>
			<%
				List<Option> options = q.getOptions();
				for(int j=0;j<options.size();j++){
					Option opt = options.get(j);
			%>
				<div class="option-wrapper">
  					<input type="checkbox" name="question<%=i%>" value="<%=j%>">
  					<label><%=opt.getOption()%></label>
				</div>
			<%} %> 
		</div>
	<%} %>
	</div>
	<button type="button" onclick="validate()">Submit</button>
	</form>
</body>
</html>