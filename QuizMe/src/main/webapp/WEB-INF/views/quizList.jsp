<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form:form id="quizFormId" modelAttribute="quizForm">
	<div class="panel-body">
		<div class="table-responsive" style="overflow-y: auto; height: 200px;">
			<table class="table table-striped table-bordered table-hover" id="dataTables-example">
				<thead>
	                  <tr style="background-color: #404040; color: #F8F8F8;">
	                      <th colspan="2">Quiz List</th>
	                  </tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${quizMap ne null && not empty quizMap }">
							<tr>
								<th>Quiz Id</th>
								<th>Quiz Name</th>
							</tr>
							<c:forEach items="${quizMap}" var="entry">
								<tr>
								    <td><form:radiobutton path="selectedQuizId" value="${entry.key}" /></td>
								    <td>${entry.value}</td>
							   	</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr><td>No Quiz Found</td></tr>
						</c:otherwise>
					</c:choose>
					
					<c:if test="${quizMap ne null && not empty quizMap }">
						<button type="button" class="btn btn-sm btn-warning" onclick="javascript: takeQuiz();">Take Quiz</button>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
</form:form>