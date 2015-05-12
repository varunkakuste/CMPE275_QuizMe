<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Share Quiz</title>
		
		<script type="text/javascript">
			function sendQuiz() {
				var emailForm = document.forms['emailFormId'];
				emailForm.method = "post";
				emailForm.action = "sendEmail";
				emailForm.submit();
			}
		</script>
	</head>
	<body>
		<form:form id="emailFormId" name="emailFormId" modelAttribute="emailForm">
			<div class="panel panel-primary">
		    	<div class="panel-heading">
		        	<h3 class="panel-title">Create Quiz</h3>
		        </div>
		        
		        <div class="panel-body">
					<div class="table-responsive" style="overflow-y: auto; height: 375px;">
						<table class="table table-striped table-bordered table-hover" id="dataTables-example">
							<thead>
			                   <tr style="background-color: #404040; color: #F8F8F8;">
			                       <th colspan="2">Share Quiz</th>
			                   </tr>
							</thead>
							<tbody>
								<tr>
									<td>Share with E-mail: <label style="color: red;">*</label> </td>
									<td><form:input path="emailTo" placeholder="enter TO email" size="72" /></td>
								</tr>
								<tr>
									<td>Subject: </td>
									<td><form:textarea path="subject" rows="1" cols="75" placeholder="enter subject for email"/></td>
								</tr>
								<tr>
									<td>Email body: </td>
									<td>
										<form:textarea path="mailBody" rows="5" cols="75" placeholder="enter email body"/>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<button type="button" class="btn btn-sm btn-primary" onclick="javascript: sendQuiz();">Send</button> 
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</form:form>
	</body>
</html>
