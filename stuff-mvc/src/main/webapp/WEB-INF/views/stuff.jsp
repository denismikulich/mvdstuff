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
		<h4><spring:message code="common.stuffnumber"/>: ${history.stuff.regNumber}</h4>
		<h4><spring:message code="common.stufftype"/>: <spring:message code="${history.stuff.type.i18n}" /></h4>
		<h4><spring:message code="common.year"/>: ${history.stuff.year}</h4>
		<h4>&nbsp</h4>
		<h4><spring:message code="stuffpage.currentplace"/>:</h4>
		<div class="formBottomWrap"  style="width: 800px;">
			<table id="searchResultTable" class="entityTable" border="1">
				<tr>
					<th><spring:message code="common.tableheader.recipient"/></th>
					<th><spring:message code="common.tableheader.sender"/></th>
					<th><spring:message code="common.tableheader.outgoingNo"/></th>
					<th><spring:message code="common.tableheader.outgoingDate"/></th>
					<th><spring:message code="common.tableheader.signature"/></th>
					<th><spring:message code="common.tableheader.user"/></th>
					<th><spring:message code="common.tableheader.description"/></th>
				</tr>
				<tr>
					<td>${history.currentPlace.recipient}</td>
					<td>${history.currentPlace.sender}</td>
					<td>${history.currentPlace.outgoingNo}</td>
					<td>${history.currentPlace.outgoingDate}</td>
					<td>${history.currentPlace.signature}</td>
					<td>${history.currentPlace.user.fullName}</td>
					<td>${history.currentPlace.description}</td>
				</tr>
			</table>
		</div>
		
		<h4>&nbsp</h4>
		
		<h4><spring:message code="stuffpage.history"/>:</h4>
		<div class="formBottomWrap"  style="width: 800px;">
			<table id="searchResultTable" class="entityTable" border="1">
				<tr>
					<th><spring:message code="common.tableheader.recipient"/></th>
					<th><spring:message code="common.tableheader.sender"/></th>
					<th><spring:message code="common.tableheader.outgoingNo"/></th>
					<th><spring:message code="common.tableheader.outgoingDate"/></th>
					<th><spring:message code="common.tableheader.signature"/></th>
					<th><spring:message code="common.tableheader.user"/></th>
					<th><spring:message code="common.tableheader.description"/></th>
				</tr>
				<c:forEach items="${history.history}" var="flow" varStatus="i">
					<tr>
						<td>${flow.recipient}</td>
						<td>${flow.sender}</td>
						<td>${flow.outgoingNo}</td>
						<td>${flow.outgoingDate}</td>
						<td>${flow.signature}</td>
						<td>${flow.user.fullName}</td>
						<td>${flow.description}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

</body>
</html>