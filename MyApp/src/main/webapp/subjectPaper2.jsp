<%@page import="java.util.Map"%>
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
<script>
	var answerSelected = [];
function check(){
	<%
	Integer questionIndex =Integer.parseInt(request.getParameter("question")) ;
	Map<Integer, List<String>> answerTracker = (Map<Integer, List<String>>) session.getAttribute("answerTracker");
	for (Map.Entry<Integer, List<String>> entry : answerTracker.entrySet()) {
	    if(entry.getValue()==null){
	%>  
	    	answerSelected.push("");
	<%
	    }
	    else{
	%>
	    	answerSelected.push(<%=entry.getValue()%>);
	<%
	    }
	}
	%>
	var currentQuestionOptionSelected = answerSelected[<%=questionIndex %>];
	document.querySelectorAll('.question').forEach(elem =>{
		const type = elem.dataset.type;
		const checkBoxes = elem.querySelectorAll('input[type="checkbox"]');
		const checkBoxesArray = Array.from(checkBoxes);
		if(currentQuestionOptionSelected != ""){
			currentQuestionOptionSelected.forEach((value,index)=>{
				checkBoxesArray[value].checked = true;
				console.log("working fine")
			})	
		}
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
	console.log(answerSelected)
}

	
	function validate(questionIndex){
		var currQuestionAnswers = []
		var elem = document.querySelector('.question');
		CheckBoxes = elem.querySelectorAll('input[type="checkbox"]');
		CheckBoxes.forEach(checkBox => {
			if(checkBox.checked){
				currQuestionAnswers.push(checkBox.value);
			}
		})
		console.log(currQuestionAnswers);
		if(currQuestionAnswers.length!=0){
			answerSelected[<%=questionIndex%>] = currQuestionAnswers;
		}
		
		const valid = answerSelected.every( value => value!="");
		if(valid){
			const form = document.getElementById("myQuestionForm");
			form.action = "AnswerTracker?submit=true&questionIdx="+questionIndex;
			form.submit();	
		}else{
			alert("Attempt all question");			
		}
	}
	
	function changeQuestion(choice,questionIndex){
		console.log("Hii")
		const form = document.getElementById("myQuestionForm");
		form.action = "AnswerTracker?choice="+choice+"&questionIdx="+questionIndex;
		form.submit();
	}
</script>
</head>

<body onload="check()">
	<%
		
		Question currentQuestion =(Question) session.getAttribute("currentQuestion");
		Integer totalQuestion = (Integer) session.getAttribute("totalQuestions");
		if(currentQuestion==null || totalQuestion==null){
			response.sendRedirect("GenerateQuestion");
			return;
		}
		String subjectName = request.getParameter("subject");
		Boolean questionType = currentQuestion.getMultipleAnswer();
	%>
	<header>
		<h1>Question for <%=subjectName %></h1> 
		<button onclick="validate(<%=questionIndex%>)">submit</button>
	</header>
  	
	
	<form method="post" action="" id="myQuestionForm">
	<div class="question-container">
	
		<div class="question" data-type="<%=questionType %>">
			<%=currentQuestion.getQuestion() %>
			<%
				List<Option> options = currentQuestion.getOptions();
				for(int j=0;j<options.size();j++){
					Option opt = options.get(j);
			%>
				<div class="option-wrapper">
  					<input type="checkbox" name="question" value="<%=j%>">
  					<label><%=opt.getOption()%></label>
				</div>
			<%} %> 
		</div>
	</div>
	<div style="display: flex">
	<button 
		type="button" 
		onclick="changeQuestion('previous',<%=questionIndex %>)" 
		<%= (questionIndex == 0)? "disabled class='disabledButton'": "" %>>
		Previous
	</button>
	<button 
		type="button" 
		onclick="changeQuestion('next',<%=questionIndex %>)"
		<%= (questionIndex == totalQuestion-1)? "disabled class='disabledButton'": "" %>>
			Next
	</button>
	
	</div>
	</form>
</body>
</html>