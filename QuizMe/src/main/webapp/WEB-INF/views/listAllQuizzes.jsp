<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>List of Quizzes</title>
		 <script type="text/javascript">
			function takeQuiz() {
				var quizForm = document.forms['quizListFormId'];
				quizForm.method = "post";
				quizForm.action = "takeQuiz";
				quizForm.submit();
			}
		</script> 
	</head>
	<body>
		<form:form id="quizListFormId" modelAttribute="quizForm">
			<div class="panel panel-primary">
				<div class="panel-body">
					<div class="table-responsive" style="overflow-y: auto; height: 200px;">
						<table class="table table-striped table-bordered table-hover" id="dataTables-example">
							<thead>
				                  <tr style="background-color: #404040; color: #F8F8F8;">
				                      <th colspan="4">List of Quizzes</th>
				                  </tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${ quizAllQuizzesList ne null && not empty quizAllQuizzesList }">
										<tr>
											<th>&nbsp;</th>
											<th>Quiz Name</th>
											<th>Category</th>
											<th>Difficulty Level</th>
										</tr>
										<c:forEach items="${quizAllQuizzesList}" var="quizModel">
											<tr>
											    <td><form:radiobutton path="selectedQuizId" value="${quizModel.quiz_id}" /></td>
											    <td>${quizModel.quizName}</td>
											    <td>${quizModel.categotyStr}</td>
											    <td>${quizModel.difficultyLevelStr}</td>
										   	</tr>
										</c:forEach>
										<button type="button" class="btn btn-sm btn-warning" onclick="javascript: takeQuiz();">Take Quiz</button>
									</c:when>
									<c:otherwise>
										<tr><td colspan="2">No Quizzes to list</td></tr>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
					</div>
				</div>
	    	</div>
		</form:form>
	</body>
</html>