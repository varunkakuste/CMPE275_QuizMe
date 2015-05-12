<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Create Quiz</title>
<!-- 		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> -->
		 <script type="text/javascript">
// 			function getQuiz() {
// 				var formData=$("#quizFormId").serialize();
				
// 				/* jQuery.get("viewOrderEventList.html?screenName=update", function( data ) {
// 					  $(".middleframe_container").html( data );
// 					}); */
				
// 				$.ajax({
// 					type:"POST",
// 					data:formData,
// 					 dataType:"json",
// 					url:'/quizme/getQuizList',
// 					success:function(data,status){
						
// 						$("#quizList").html(data);
// 					},
// 					complete: function (xhr, status) {
// 					    if (status === 'error' || !xhr.responseText) {
// 					        handleError();
// 					    }
// 					    else {
// 					        var data = xhr.responseText;
// 					        $("#quizList").html(data);
// 					        //...
// 					    }
// 					},
// 				error:function(data,status){
					
// 					$("#quizList").html(data);
// 				}
// 				});
// 			}		

			function getQuiz() {
				var quizForm = document.forms['quizFormId'];
				quizForm.method = "post";
				quizForm.action = "getQuizList";
				quizForm.submit();
			}
			
			function takeQuiz() {
				var quizForm = document.forms['quizFormId'];
				quizForm.method = "post";
				quizForm.action = "takeQuiz";
				quizForm.submit();
			}
		</script> 
	</head>
	<body>
		<form:form id="quizFormId" modelAttribute="quizForm">
			<div class="panel panel-primary">
		    	<div class="panel-heading">
		        	<h3 class="panel-title">Search Quiz</h3>
		        </div>
		        <div class="panel-body">
					<div class="table-responsive" style="overflow-y: auto; height: 140px;">
						<table class="table table-striped table-bordered table-hover" id="dataTables-example">
							<thead>
			                   <tr style="background-color: #404040; color: #F8F8F8;">
			                       <th colspan="6">Search Criteria</th>
			                   </tr>
							</thead>
							<tbody>
								<tr>
									<td>Enter Quiz Name: </td>
									<td><form:input path="quizName" placeholder="enter quiz name to search for" /></td>
								
									<td>Category: </td>
									<td>
										<form:select path="category">
											<form:options items="${ quizForm.categoryModelList }" itemValue="categoryId" itemLabel="category"></form:options>
										</form:select>
									</td>
								
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
						</table>
						<button type="button" class="btn btn-sm btn-primary" onclick="javascript: getQuiz();">Search</button>
					</div>
					
					
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
											<c:when test="${ isQuizFound ne null && not empty isQuizFound }">
												<c:if test="${ quizMap ne null && not empty quizMap }">
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
													<button type="button" class="btn btn-sm btn-warning" onclick="javascript: takeQuiz();">Take Quiz</button>
												</c:if>
											</c:when>
											<c:otherwise>
												<tr><td colspan="2">No Quizzes to list</td></tr>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
							</div>
						</div>
					
<!-- 					<div id="quizList"> -->
<!-- 					</div> -->
					
				</div>
	    	</div>
		</form:form>
	</body>
</html>