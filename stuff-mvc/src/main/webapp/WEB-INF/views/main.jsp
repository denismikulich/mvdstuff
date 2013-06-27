<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" type="text/css" href="../css/main.css" media="screen" />
<script type="text/javascript" src="../js/common.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>
<body>
	<%@include file="header.jsp" %>
	
	<div id='mainmenu-container'>
		<menu class="mainmenu1" dir="ltr">
			<li><a href="../main/sendstuff"> <strong>Send stuff</strong></a></li>
			<li><a href="../main/search"> <strong>search</strong></a></li>
			<li><a href="../main/simpleSearch"> <strong>Simple Search</strong></a></li>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li><a href="../admin/admin"><strong>Admin tools</strong></a></li>
			</sec:authorize>
		</menu>
	</div>

</body>
</html>