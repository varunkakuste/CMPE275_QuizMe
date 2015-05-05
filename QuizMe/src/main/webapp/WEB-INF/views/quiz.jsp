<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Create Quiz</title>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
		 <script type="text/javascript">
			function getQuiz() {
				var formData=$("#quizFormId").serialize();
				
				/* jQuery.get("viewOrderEventList.html?screenName=update", function( data ) {
					  $(".middleframe_container").html( data );
					}); */
				
				$.ajax({
					type:"POST",
					data:formData,
					 dataType:"json",
					url:'/quizme/getQuizList',
					success:function(data,status){
						
						$("#quizList").html(data);
					},
					complete: function (xhr, status) {
					    if (status === 'error' || !xhr.responseText) {
					        handleError();
					    }
					    else {
					        var data = xhr.responseText;
					        $("#quizList").html(data);
					        //...
					    }
					},
				error:function(data,status){
					
					$("#quizList").html(data);
				}
				});
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
					<div class="table-responsive" style="overflow-y: auto; height: 150px;">
						<table class="table table-striped table-bordered table-hover" id="dataTables-example">
							<thead>
			                   <tr style="background-color: #404040; color: #F8F8F8;">
			                       <th colspan="6">Search Criteria</th>
			                   </tr>
							</thead>
							<tbody>
								<tr>
									<td>Enter Quiz Name: </td>
									<td><form:input path="quizName" /></td>
								
									<td>Category: </td>
									<td>
										<form:select path="category">
											<form:option value="1">General</form:option>
											<form:option value="2">Sports</form:option>
										</form:select>
									</td>
								
									<td>Difficulty Level: </td>
									<td>
										<form:select path="difficultyLevel">
											<form:option value="1">Easy</form:option>
											<form:option value="2">Medium</form:option>
											<form:option value="3">Hard</form:option>
										</form:select>
									</td>
								</tr>
							</tbody>
							
							
						</table>
						<button type="button" class="btn btn-sm btn-primary" onclick="javascript: getQuiz();">Search </button>
					</div>
					<div id="quizList">
					
					</div>
					
				</div>
	    	</div>
		</form:form>
	</body>
</html>