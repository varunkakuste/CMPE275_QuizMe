<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript">
	function logout() {
		alert("logout...");
 	}
	
	function help() {
		alert("help...");
	}
</script>

<form:form modelAttribute="filesForm">
	<div id="GlobalLinksTop">
		<div>
		    <ul>
		    	<li>Welcome User</li>
				<li><a href="#" onclick="javascript:logout();">Logout</a></li>
				<li><a href="#" onclick="javascript:help();">Help</a></li>
			</ul>
		</div>
	</div>
</form:form>