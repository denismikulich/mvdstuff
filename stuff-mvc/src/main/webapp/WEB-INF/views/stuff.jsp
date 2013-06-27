<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" type="text/css" href="../css/main.css" media="screen" />
<script type="text/javascript" src="../js/common.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Stuff</title>
</head>
<body>
	<%@include file="header.jsp" %>
	
	<div class="formMainWrap">
		<h4>Flow list:</h4>
		<div class="formBottomWrap"  style="width: 800px;">
			<table id="searchResultTable" class="entityTable" border="1">
				<tr>
					<th class="hiddencol">ID</th>
					<th>Stuff number</th>
					<th>Year</th>
					<th>Receiver</th>
					<th>Sender</th>
					<th>Send number</th>
					<th>Send date</th>
					<th>Sign</th>
					<th>User</th>
					<th>Description</th>
				</tr>
				<c:forEach items="${stuffFlows}" var="flow" varStatus="i">
					<tr id="row_${flow.flowId}" class="" onclick='selectRow(${flow.flowId})'>
						<td class="hiddencol">${flow.flowId}</td>
						<td>${flow.stuff.regNumber}</td>
						<td>${flow.stuff.year}</td>
						<td>${flow.reciever}</td>
						<td>${flow.sender}</td>
						<td>${flow.sendNumber}</td>
						<td>${flow.sendDate}</td>
						<td>${flow.sign}</td>
						<td>${flow.user.lastname}</td>
						<td>${flow.description}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

</body>
</html>