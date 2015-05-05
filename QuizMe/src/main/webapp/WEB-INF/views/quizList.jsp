<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form:form name="quizForm" action="/takeQuiz" type="post">
	
    	
        <div class="panel-body">
			<div class="table-responsive" style="overflow-y: auto; height: 190px;">
				<table class="table table-striped table-bordered table-hover" id="dataTables-example">
					<thead>
	                   <tr style="background-color: #404040; color: #F8F8F8;">
	                       <th colspan="2">Quiz List</th>
	                   </tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${quizList ne null && not empty quizList }">
								<tr>
									<th>Quiz Name</th>
									<th>Take Quiz</th>
								</tr>
								<c:set var="count" value="0" scope="page" />
								<c:forEach var="quiz" items="${quizList}">
									<tr>
										<input type="hidden" id="quiz-"${count}></input>
										<c:set var="count" value="${count +1 }" scope="page" />
										<td>${quiz}</td>
										<td><input type="submit" value="Take "${quiz}> </input> </td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr><td>No Quiz Found</td></tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
				
			</div>
		</div>
   	
</table>
</form:form>
