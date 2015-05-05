<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Create Quiz</title>
		<script type="text/javascript">
			function proceedToQuestion() {
				var quizForm = document.forms['quizFormId'];
				quizForm.method = "get";
				quizForm.action = "proceedToQuestion";
				quizForm.submit();
			}		
			
			function createQuiz() {
				var quizForm = document.forms['quizFormId'];
				quizForm.method = "post";
				quizForm.action = "createQuiz";
				quizForm.submit();
			}	
		</script>
	</head>
	<body>
		<form:form id="quizFormId" modelAttribute="quizForm">
			<div class="panel panel-primary">
		    	<div class="panel-heading">
		        	<h3 class="panel-title">Create Quiz</h3>
		        </div>
		        <div class="panel-body">
					<div class="table-responsive" style="overflow-y: auto; height: 375px;">
						<table class="table table-striped table-bordered table-hover" id="dataTables-example">
							<thead>
			                   <tr style="background-color: #404040; color: #F8F8F8;">
			                       <th colspan="2">Quiz Details</th>
			                   </tr>
							</thead>
							<tbody>
								<tr>
									<td>Enter Quiz Name: </td>
									<td><form:input path="quizName" /></td>
								</tr>
								<tr>
									<td>Category: </td>
									<td>
										<form:select path="category">
											<form:options items="${ quizForm.categoryModelList }" itemValue="categoryId" itemLabel="category"></form:options>
										</form:select>
									</td>
								</tr>
								<tr>
									<td>Difficulty Level: </td>
									<td>
										<form:select path="difficultyLevel">
											<c:forEach var="data" items="${quizForm.difficultyLevelModelList}" varStatus="loop">    
												<form:option value="${data.difficultyLevelId}" label="${data.difficultyLevel}" />
											</c:forEach>
										</form:select>
									</td>
								</tr>
							</tbody>
							
							<thead>
			                   <tr style="background-color: #404040; color: #F8F8F8;">
			                       <th>Question</th>
			                       <th>Option A</th>
			                       <th>Option B</th>
			                       <th>Option C</th>
			                       <th>Option D</th>
			                       <th>Correct Answer</th>
			                   </tr>
							</thead>
							
							<tbody>
								<c:if test="${quizForm.questionsList ne null && !quizForm.questionsList.isEmpty()}">
									<c:forEach var="questionBean" items="${quizForm.questionsList}" varStatus="questionLoop">    
										<tr>
											<td>
												${questionBean.question}
											</td>
											<td>
												${questionBean.optionA}
											</td>
											<td>
												${questionBean.optionB}
											</td>
											<td>
												${questionBean.optionC}
											</td>
											<td>
												${questionBean.optionD}
											</td>
											<td>
												${questionBean.correctAnswer}
											</td>
										</tr>
									</c:forEach>									
								</c:if>
							</tbody>
						</table>
						<button type="button" class="btn btn-sm btn-primary" onclick="javascript: proceedToQuestion();">Add Question</button>
						<button type="button" class="btn btn-sm btn-warning" onclick="javascript: createQuiz();">Create Quiz</button>
					</div>
				</div>
	    	</div>
		</form:form>
	</body>
</html>