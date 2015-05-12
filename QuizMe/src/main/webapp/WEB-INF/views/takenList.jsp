<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
	function shareQuiz() {
		var emailForm = document.forms['emailFormId'];
		emailForm.method = "get";
		emailForm.action = "share";
		emailForm.submit();
	}
</script>
    	
      <div class="panel-body">
	<div class="table-responsive" style="overflow-y: auto; height: 385px;">
		
		<c:if test="${quizCreatedMessage ne null && not empty quizCreatedMessage}">
	        <div class="alert alert-info" role="alert">
	        	<div align="center">
		        	<strong style="color: #3366CC;">
		        		Cheers...Quiz created...!!!
		        	</strong>
		        </div>
	        </div>
		</c:if>
		
		<table class="table table-striped table-bordered table-hover" id="dataTables-example">
			<thead>
                  <tr style="background-color: #404040; color: #F8F8F8;">
                      <th colspan="4">Taken Quiz List</th>
                  </tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${quizTaken ne null && not empty quizTaken }">
						<tr>
							<th>&nbsp;</th>
							<th>Quiz Name</th>
							<th>Score</th>
							<th>Comment</th>
						</tr>
						<c:set var="count" value="0" scope="page" />
						<form:form id="emailFormId" modelAttribute="emailForm">
							<c:forEach var="quiz" items="${quizTaken}">
								<tr>
									<td><input type="radio" id="selectedQuizName" name="selectedQuizName" value="${quiz.quizName}" /></td>
									<td>${quiz.quizName}</td>
									<td>${quiz.score}</td>
									<td>${quiz.comment}</td>
								</tr>
							</c:forEach>
							<button type="button" class="btn btn-sm btn-primary" onclick="javascript: shareQuiz();">Share</button>
    					</form:form>
					</c:when>
					<c:otherwise>
						<tr><td>No Quiz Yet Taken</td></tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		
	</div>
</div>
