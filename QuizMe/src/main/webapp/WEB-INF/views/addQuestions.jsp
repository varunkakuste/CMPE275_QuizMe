<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Create Quiz</title>
		<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
		<script type="text/javascript">
			function addQuestion() {
				var questionForm = document.forms['questionFormId'];
				questionForm.method = "post";
				questionForm.action = "addQuestion";
				questionForm.submit();
			}		
			
		</script>
	</head>
	<body>
		<form:form id="questionFormId" modelAttribute="questionForm">
			<div class="panel panel-primary">
		    	<div class="panel-heading">
		        	<h3 class="panel-title">Add a Question</h3>
		        </div>
		        <div class="panel-body">
					<div class="table-responsive" style="overflow-y: auto; height: 375px;">
						<table class="table table-striped table-bordered table-hover" id="dataTables-example">
							<tbody>
								<tr>
									<td>Enter Question: </td>
									<td><form:input path="question" /></td>
								</tr>
								<tr>
									<td>Enter Option A: </td>
									<td><form:input path="optionA" /></td>
								</tr>
								<tr>
									<td>Enter Option B: </td>
									<td><form:input path="optionB" /></td>
								</tr>
								<tr>
									<td>Enter Option C: </td>
									<td><form:input path="optionC" /></td>
								</tr>
								<tr>
									<td>Enter Option D: </td>
									<td><form:input path="optionD" /></td>
								</tr>
								<tr>
									<td>Correct Answer: </td>
									<td><form:input path="correctAnswer" /></td>
								</tr>
							</tbody>
						</table>
						<button type="button" onclick="javascript: addQuestion();" class="btn btn-sm btn-primary">Add Question</button>
					</div>
				</div>
	    	</div>
		</form:form>
	</body>
</html>