<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.HashMap" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
		<title>Search Quiz</title>
<!-- 		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> -->
		 <script type="text/javascript">
		 	var answersArray;
			function submitQuiz() {
				var quizForm = document.forms['questionsListId'];
				quizForm.method = "post";
				quizForm.action = "submitQuiz";
				quizForm.submit();
			}	
		</script> 
	</head>
	<body>
		<form:form id="questionsListId" modelAttribute="questionsList">
			<div class="panel panel-primary">
		    	<div class="panel-heading">
		        	<h3 class="panel-title">Questions</h3>
		        </div>
		        <div class="panel-body">
					<div class="table-responsive" style="overflow-y: auto; height: 375px;">
						<table class="table table-striped table-bordered table-hover" id="dataTables-example">
							<thead>
			                   <tr style="background-color: #404040; color: #F8F8F8;">
			                       <th>Question</th>
			                       <th>Option A</th>
			                       <th>Option B</th>
			                       <th>Option C</th>
			                       <th>Option D</th>
			                       <th>Answer</th>
			                   </tr>
							</thead>
							
							<tbody>
								<c:if test="${questionsList ne null && !questionsList.isEmpty()}">
<%-- 									<c:set var="questionId" value="0" scope="page" /> --%>
									<c:forEach var="questionBean" items="${questionsList}" varStatus="questionLoop">    
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
<%-- 												<c:set var="questionId" value="${ questionBean.questionId }" scope="page" /> --%>
												<input type="text" id="${questionBean.questionId}" name="${questionBean.questionId}" placeholder="enter A, B, C, D" />
 											</td>
										</tr>
									</c:forEach>									
								</c:if>
								
							<tr>
								<td>
									Comment: 
								</td>
								<td colspan="5">
									<textarea rows="5" cols="75" name="comment" id="comment"> </textarea>
								</td>
							</tr>
							</tbody>
						</table>
						<button type="button" class="btn btn-sm btn-primary" onclick="javascript: submitQuiz();">Submit Quiz</button>
					</div>
				</div>
	    	</div>
		</form:form>
	</body>
</html>