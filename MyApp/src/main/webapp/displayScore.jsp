<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
/* Page Styling */
body {
    font-family: Arial, sans-serif;
    background-color: #f4f6f8;
    margin: 0;
    padding: 40px 20px;
    text-align: center;
    color: #2c3e50;
}

/* Heading */
h1 {
    font-size: 2rem;
    margin-bottom: 30px;
    color: #27ae60;
}

/* Score Container */
div {
    margin: 20px auto;
    padding: 20px;
    background-color: #ffffff;
    border-radius: 10px;
    display: inline-block;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
    min-width: 250px;
}

/* Score Label */
div > div {
    font-size: 1.3rem;
    margin-bottom: 15px;
    font-weight: bold;
}

/* Button Styling */
button {
    padding: 10px 20px;
    font-size: 1rem;
    background-color: #2980b9;
    color: white;
    border: none;
    border-radius: 6px;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

button:hover {
    background-color: #1c5980;
}

</style>
<script>
history.pushState(null, null, location.href);
window.onpopstate = function () {
    // When the user tries to go back
    window.location.href = "home.jsp";
};
</script>
</head>
<body>
<% 
	String score = request.getParameter("score");
	if(score==null){ 
		response.sendRedirect("home.jsp");
	}
%>	
	<h1>Congratulations you completed ur quiz</h1>
	<div>
		<div>Your Score - <%=score %></div> <br>
		<button onclick = "window.location.href='home.jsp'">Go to home page</button>
	</div>
</body>
</html>