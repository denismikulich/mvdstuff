<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" type="text/css" href="../css/main.css"
	media="screen" />
<script type="text/javascript" src="../js/common.js"></script>
<!-- Next libs enable datetime picker -->
<link rel="stylesheet" href="../css/redmond/jquery-ui.css" />
<script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui-1.10.2.js"></script>
<script type="text/javascript" src="../js/modernizr-latest.js"></script>
<script type="text/javascript">
	function onPageLoad() {
		CommonLib.activateDatePicker();
		CommonLib.initYearSelector("yearSelector");

		var typeSelector = $("#typeSelector");
		typeSelector.append('<option value="' + 1 + '">' + "<spring:message code='stufftype.otkaz' />"
				+ '</option>');
		typeSelector.append('<option value="' + 2 + '">' + "<spring:message code='stufftype.private' />"
				+ '</option>');
		typeSelector.append('<option value="' + 3 + '">' + "<spring:message code='stufftype.administr' />"
				+ '</option>');
	}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-16">
<title>Simple Search</title>
</head>
<body onload="onPageLoad()">
	<%@include file="header.jsp"%>

	<div id="top">
		<div class="formMainWrap" style="width: 570px;">
			<h4><spring:message code="simplesearchpage.searchcriteria"/></h4>
			<form:form name="searchForm" commandName="criteria" method="post"
				action="processSimpleSearch">
				<div id="simpleSearchDiv" class="formBottomWrap"
					style="width: 550px; margin: auto">
					<div style="height: 30px;">
						<form:label class="standardLbl" path="stuffNumber">
							<spring:message code="common.stuffnumber" />
						</form:label>
						<form:input id="stuffNumber" class="textinpSmall" type="text"
							path="stuffNumber" />
						<form:label class="standardLbl" path="type" style="padding-left: 10px">
							<spring:message code="common.stufftype" />
						</form:label>
						<form:select id="typeSelector" path="type" style="width: 90px"></form:select>
						<form:label class="standardLbl" path="stuffsYear" style="padding-left: 10px">
							<spring:message code="common.year" />
						</form:label>
						<form:select id="yearSelector" path="stuffsYear"></form:select>
					</div>
				</div>

				<c:set var="noResClass" value="formBottomWrap_hidden"></c:set>
				<c:if test="${showNoResult=='true'}">
					<c:set var="noResClass" value="formBottomWrap"></c:set>
				</c:if>

				<div class="${noResClass}" style="width: 550px; margin: auto">
					<label style="color: red; padding-left: 50px;"><spring:message
							code="simplesearch.label.noresults" /></label>
				</div>

				<div style="float: right; margin-top: 10px">
					<input type="submit" value="поиск">
				</div>
			</form:form>
		</div>
	</div>

</body>
</html>