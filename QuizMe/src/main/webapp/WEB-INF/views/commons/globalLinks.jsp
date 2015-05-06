<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" %>

<script type="text/javascript">
	function changeSettings() {
		var signUpForm = document.forms['quizFormId'];
		signUpForm.method = "get";
		signUpForm.action = "changeSettings";
		signUpForm.submit();
	}

	function logout() {
		alert("logout...");
 	}
	
	function help() {
		alert("help...");
	}
</script>

<form:form id="quizFormId" modelAttribute="quizForm">
	<div id="GlobalLinksTop">
		<div>
		    <ul>
		    	<li>Welcome <c:out value="${sessionScope.userName}"/></li>
		    	<li><a href="#" onclick="javascript: changeSettings();">Change Settings</a></li>
				<li><a href="#" onclick="javascript:logout();">Logout</a></li>
				<li><a href="#" onclick="javascript:help();">Help</a></li>
			</ul>
		</div>
	</div>
</form:form>