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
		var select = $("#yearSelector");
		var date = new Date();
		var year = date.getFullYear();

		for ( var i = 0; i < 12; i++) {
			var option = document.createElement('option');
			option.setAttribute('value', "00" + i);
			select.append('<option value="' + (year - 7 + i) + '">'
					+ (year - 7 + i) + '</option>');
		}
		select.val(year);
		$('#simpleSearch').click();
	}
	
	function setSearchType(type) {
		if (type==1) { // simple searching
			$('#simpleSearchDiv :input').removeAttr('disabled');
			$('#advancedSearchDiv :input').attr('disabled', true);
			$('#advancedSearch').removeAttr('disabled');
			$('#advancedSearchDiv :input').val("");
		} else { // advanced searching
			$('#simpleSearchDiv :input').attr('disabled', true);
			$('#advancedSearchDiv :input').removeAttr('disabled');
			$('#simpleSearch').removeAttr('disabled');
			$('#stuffNumber').val("");
		}
	}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-16">
<title>Advanced Searching</title>
</head>
<body onload="onPageLoad()">
	<%@include file="header.jsp"%>

	<div id="top">
		<div class="formMainWrap" style="width: 820px;">
			<h4>Search criteria</h4>
			<form:form name="searchForm" commandName="criteria" method="post" action="processSearch">
				<div style="height: 50px;">
						<label>reciever</label>
						<form:input	type="text" path="reciever"/> 
						<label>sender</label> 
						<form:input type="text" path="sender"/>
						<label>send number</label> 
						<form:input type="text" path="sendNumber"/>
					</div>
				<div style="height: 50px;">
						<label>Send date range</label><br> 
						<label>start</label>
						<form:input type="date" path="sendDateStartRange" style="width: 100px" />
						<label>end</label> 
						<form:input type="date" path="sendDateEndRange" style="width: 100px" />
				</div>
				<div style="float: right; margin-top: 10px">
					<input type="submit" value="Search">
				</div>
			</form:form>
		</div>
	</div>

</body>
</html>