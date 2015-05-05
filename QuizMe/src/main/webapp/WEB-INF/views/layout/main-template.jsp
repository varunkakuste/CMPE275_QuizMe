<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<style type="text/css">
	/* Global Links Top */
	#GlobalLinksTop{
	}
	#GlobalLinksTop div{
		overflow:hidden;
		
	}
	#GlobalLinksTop ul{
		float:right;
		margin:0 -13px 3px 0;
	}
	#GlobalLinksTop li{
		display:block;
		float:left;
		text-transform:uppercase;
		font-weight:bold;
		padding:0 13px 0 12px;
	}
	#GlobalLinksTop li a{
		text-decoration:none;
		color:#000;
	}
	
	#Menu{
	}
	#Menu li{
		display:block;
		text-transform:uppercase;
		font-weight:bold;
		padding:0 13px 0 12px;
	}
	
	/* Form style table elements */
	.StickyTable{
		position:relative;
	}
	.StickyTable td,
	.StickyTable th{
		font-weight:normal;
		vertical-align:top;
		padding:5px 0 5px 0;
		background:none;
	}
	.StickyTable th{
		text-align:left;
		padding-right:12px;
	}
	.StickyTable th label{
		display:block;
		padding:0 0 0 0;
		position:relative;
	}
	.StickyTable th label span{
		position:absolute;
		right:-8px;
		top:0;
	}
	* html .StickyTable th label span{
		right:3px;
	}
	
	/* Messages */
	#RnoMsg{
		margin:10px auto 20px auto;
	}
	#RnoMsg td{
		padding:8px 20px 12px 45px;
		background-position:8px 3px;
		background-repeat:no-repeat;
		border:1px solid #000;
	}
	#RnoMsg h2{
		font-weight:bold;
		font-size:120%;
		color:#000;
	}
	
	#RnoMsg.RnoMsgInfo td{
		border-color:#00A1C9;
	}
	#RnoMsg.RnoMsgInfo h2{
		color:#00A1C9;
	}
	
	#RnoMsg.RnoMsgError td{
		border-color:#FF0000;
	}
	#RnoMsg.RnoMsgError h2{
		color:#FF0000;
	}
	
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>QuizMe</title>
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
	
	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
	
	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<tiles:insertAttribute name="globallinks"></tiles:insertAttribute>
	<tiles:insertAttribute name="header"></tiles:insertAttribute>
	<tiles:insertAttribute name="menu"></tiles:insertAttribute>
	<tiles:insertAttribute name="body"></tiles:insertAttribute>
    <tiles:insertAttribute name="footer"></tiles:insertAttribute>
</body>
</html>