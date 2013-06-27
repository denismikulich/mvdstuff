<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
<title>Submit Send</title>
</head>
<body onload="onPageLoad()">
	<%@include file="header.jsp"%>
	<div id="top">
		<div class="formMainWrap">
			<h4>
				<spring:message code="sendstuffpage.label.preparestuffs" />
			</h4>
			<div class="formBottomWrap" style="margin: auto">
				<form:form name="stuffListForm" method="post" action="submitStuffs">
					<table id="stuffListTable" class="entityTable" border="1">
						<tr>
							<th style="width: 15px">!</th>
							<th>Stuff number</th>
							<th>Stuff description</th>
						</tr>
						<c:forEach items="${stuffNumbers}" var="stuff" varStatus="i">
							<tr >
								<td class='stuffState${stuff.state}'>&nbsp</td>
								<td >${stuff.stuffNumber}</td>
								<td >${stuff.stateDescription}</td>
							</tr>
						</c:forEach>
					</table>
					<div style="float: right; margin-top: 10px">
						<input type="submit" name="back" value="Back"> <input type="submit" name="next" value="Submit">
					</div>
				</form:form>
			</div>
		</div>
	</div>


</body>
</html>