<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
		<title>User Signup</title>
		<script type="text/javascript">
			function signUp() {
				var signUpForm = document.forms['signUpFormId'];
				signUpForm.method = "POST";
				signUpForm.action = "signup";
				signUpForm.submit();
			}	
			
			function saveChanges() {
				var signUpForm = document.forms['signUpFormId'];
				signUpForm.method = "POST";
				signUpForm.action = "savechanges";
				signUpForm.submit();
			}
			
			function cancel() {
				var signUpForm = document.forms['signUpFormId'];
				signUpForm.method = "get";
				signUpForm.action = "login";
				signUpForm.submit();
			}	
			
			function cancelSignUp() {
				var signUpForm = document.forms['signUpFormId'];
				signUpForm.method = "get";
				signUpForm.action = "getTaken";
				signUpForm.submit();
			}	
		</script>
	</head>
	<body>
		<form:form id="signUpFormId" modelAttribute="signUpForm">
			<div class="panel panel-primary">
		    	<div class="panel-heading">
		        	<h3 class="panel-title">Sign Up</h3>
		        </div>
		        <c:if test="${signingUpError ne null && not empty signingUpError}">
					<div class="alert alert-danger" role="alert">
						<div align="center">
							<strong style="color: red;">
								<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
								Password Mismatch or Error during Registration
							</strong>
						</div>
					</div>
				</c:if>

				<c:if test="${signedUpInfo ne null && not empty signedUpInfo}">
			        <div class="alert alert-info" role="alert">
			        	<div align="center">
				        	<strong style="color: #3366CC;">
				        		Cheers...Registration Successful...!!!
				        	</strong>
				        </div>
			        </div>
				</c:if>
		        <div class="panel-body">
					<div class="table-responsive" style="overflow-y: auto; height: 400px;">
						<table class="table table-striped table-bordered table-hover" id="dataTables-example">
							<thead>
			                   <tr style="background-color: #404040; color: #F8F8F8;">
			                       <th colspan="2">Enter Details</th>
			                   </tr>
							</thead>
							<tbody>
								<tr>
									<td>First Name: </td>
									<td><form:input path="firstName" placeholder="enter your first name" /></td>
								</tr>
								<tr>
									<td>Last Name: </td>
									<td><form:input path="lastName" placeholder="enter your last name" /></td>
								</tr>
								<tr>
									<td>userName: </td>
									<td><form:input path="userName" placeholder="enter user name" /> <label style="color: red;"><form:errors path="userName" element="div" /></label></td>
								</tr>
								<tr>
									<td>Email: </td>
									<td><form:input path="email" placeholder="enter email"/> <label style="color: red;"><form:errors path="email" element="div" /></label></td>
								</tr>
								<tr>
									<td>Password: </td>
									<td><form:input path="password" type="password" placeholder="enter password"/> <label style="color: red;"><form:errors path="password" element="div" /></label></td>
								</tr>
								<tr>
									<td>Confirm Password: </td>
									<td><form:input path="confirmPassword" type="password" placeholder="enter confirm password" /> <label style="color: red;"><form:errors path="confirmPassword" element="div" /></label></td>
								</tr>
							</tbody>
						</table>
						<c:choose>
							<c:when test="${changeSettings ne null && not empty changeSettings}">
								<button type="button" class="btn btn-sm btn-primary" onclick="javascript: saveChanges();">Save Changes</button>
								<button type="button" class="btn btn-sm btn-warning" onclick="javascript: cancelSignUp();">Cancel</button>
							</c:when>
							<c:otherwise>
								<button type="button" class="btn btn-sm btn-primary" onclick="javascript: signUp();">Register</button>
								<button type="button" class="btn btn-sm btn-warning" onclick="javascript: cancel();">Cancel</button>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
	    	</div>
		</form:form>
	</body>
</html>