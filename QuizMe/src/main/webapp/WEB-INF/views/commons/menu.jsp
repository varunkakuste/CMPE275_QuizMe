<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	function home() {
		alert("this is home");
	}
	
	function createQuiz() {
		var quizForm = document.forms['quizForm'];
		quizForm.method = "get";
		quizForm.action = "createQuiz";
		quizForm.submit();
	}
	
	function searchQuiz() {
		alert("search quiz");
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu</title>
</head>
<body>
	<form:form modelAttribute="quizForm">
		<nav class="navbar navbar-inverse">
			<div class="container">
				<div class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li><a href="javascript:home();">Home</a></li>
						<li><a href="javascript:home();">User Dashboard</a></li>
						<li><a href="javascript:createQuiz();">Create Quiz</a></li>
						<li><a href="javascript:searchQuiz();">Search Quiz</a></li>
					</ul>
				</div>
			</div>
		</nav>
	</form:form>
</body>
</html>