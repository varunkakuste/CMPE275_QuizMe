<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
		<title>User Login</title>
		<script type="text/javascript">
			function login() {
				var signUpForm = document.forms['loginFormId'];
				signUpForm.method = "get";
				signUpForm.action = "loginuser";
				signUpForm.submit();
			}	
		
			function register() {
				var signUpForm = document.forms['loginFormId'];
				signUpForm.method = "get";
				signUpForm.action = "usersignup";
				signUpForm.submit();
			}		
		</script>
	</head>
	<body>
		<form:form id="loginFormId" modelAttribute="loginForm">
			<div class="panel panel-primary">
		    	<div class="panel-heading">
		        	<h3 class="panel-title">Login</h3>
		        </div>
		        <c:if test="${loginError ne null && not empty loginError}">
			        <div class="alert alert-danger" role="alert">
			        	<div align="center">
				        	<strong style="color: red;">
				        		Access Denied...User not Registered...
				        	</strong>
				        </div>
			        </div>
				</c:if>
				<c:if test="${information ne null && not empty information}">
			        <div class="alert alert-info" role="alert">
			        	<div align="center">
				        	<strong style="color: #3366CC;">
				        		Cheers...Registration/Update Successful...!!!
				        	</strong>
				        </div>
			        </div>
				</c:if>
		        <div class="panel-body">
					<div class="table-responsive" style="overflow-y: auto; height: 400px;">
						<table class="table table-striped table-bordered table-hover" id="dataTables-example">
							<tbody>
								<tr>
									<td>User Name: </td>
									<td><form:input path="userName" placeholder="enter user name" /> <label style="color: red;"><form:errors path="userName" element="div" /></label></td>
								</tr>
								<tr>
									<td>Password: </td>
									<td><form:input path="password" type="password" placeholder="enter password" /> <label style="color: red;"><form:errors path="password" element="div" /></label></td>
								</tr>
							</tbody>
						</table>
						<button type="button" class="btn btn-sm btn-primary" onclick="javascript: login();">Login</button>
						<button type="button" class="btn btn-sm btn-warning" onclick="javascript: register();">Register</button>
					</div>
				</div>
	    	</div>
		</form:form>
	</body>
</html>