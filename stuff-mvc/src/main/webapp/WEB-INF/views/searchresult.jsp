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
<!-- Next libs enable datetime picker -->
<link rel="stylesheet" href="../css/redmond/jquery-ui.css" />
<script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui-1.10.2.js"></script>
<script type="text/javascript" src="../js/modernizr-latest.js"></script>
<script type="text/javascript">
	function onPageLoad() {
	}
</script>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Result</title>
</head>
<body onload="onPageLoad()">
	<%@include file="header.jsp"%>
	<div id="top">
		<div class="formMainWrap">
			<div class="formBottomWrap" style="width: 900px; margin: auto">
				<table id="searchResultTable" class="entityTable" border="1">
					<tr>
						<th><spring:message code="common.tableheader.stuffnumber"/></th>
						<th><spring:message code="common.tableheader.stufftype"/></th>
						<th><spring:message code="common.tableheader.year"/></th>
						<th><spring:message code="common.tableheader.recipient"/></th>
						<th><spring:message code="common.tableheader.sender"/></th>
						<th><spring:message code="common.tableheader.outgoingNo"/></th>
						<th><spring:message code="common.tableheader.outgoingDate"/></th>
						<th><spring:message code="common.tableheader.signature"/></th>
						<th><spring:message code="common.tableheader.user"/></th>
						<th><spring:message code="common.tableheader.description"/></th>
					</tr>
					<c:forEach items="${searchResult}" var="flow" varStatus="i">
					<tr>
						<td>${flow.stuff.regNumber}</td>
						<td>${flow.stuff.type}</td>
						<td>${flow.stuff.year}</td>
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
	</div>


</body>
</html>