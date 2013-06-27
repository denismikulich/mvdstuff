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
		CommonLib.activateDatePicker();
		var select = $("#yearSelector");
		var date = new Date();
		var year = date.getFullYear();
		
		for ( var i = 0; i < 12; i++) {
			var option = document.createElement('option');
			option.setAttribute('value', "00" + i);
			select.append('<option value="' +(year-7+i)+ '">' + (year-7+i) + '</option>');
		}
		select.val(year);
		
		// stuff type selector
		var typeSelector = $("#typeSelector");
		typeSelector.append('<option value="' + 1 + '">'
				+ "Otkaz" + '</option>');
		typeSelector.append('<option value="' + 2 + '">'
				+ "private" + '</option>');
		typeSelector.append('<option value="' + 3 + '">'
				+ "administr" + '</option>');
	}
</script>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Send Stuff</title>
</head>
<body onload="onPageLoad()">
	<%@include file="header.jsp"%>
	<div id="top">
		<div class="formMainWrap">
			<h4>
				<spring:message code="sendstuffpage.label.preparestuffs" />
			</h4>
			<div class="formBottomWrap" style="width: 800px; margin: auto">
				<form:form name="stuffListForm" commandName="sendBean" method="post" action="processStuffs">
					<div style="float: right; display: block;">
						<form:label path="sender">
							<spring:message code="sendstuffpage.label.sender" />
						</form:label>
						<br>
						<form:input type="text" path="sender" class="textinp" />
						<br>
						<form:label path="reciever">
							<spring:message code="sendstuffpage.label.reciever" />
						</form:label>
						<br>
						<form:input type="text" path="reciever" class="textinp" />
					</div>
					<h3 style="clear: both;"></h3>
					<div style="position: relative; height: 360px;">
						<div style="position: absolute; bottom: 0; left: 0; width: 80%">
							<form:label path="listStuffs">
								<spring:message code="sendstuffpage.label.liststuffs" />
							</form:label>
							<br>
							<form:textarea path="listStuffs" style="width: 100%" rows="20"></form:textarea>
						</div>
						<div style="position: absolute; bottom: 50px; right: 0; width: 18%">
							<form:label path="stuffsType">
								<spring:message code="sendstuffpage.label.stufftype" />
							</form:label>
							<br>
							<form:select id="typeSelector" path="stuffsType" style="width: 100%" />
						</div>
						<div style="position: absolute; bottom: 0; right: 0; width: 18%">
							<form:label path="stuffsYear">
								<spring:message code="sendstuffpage.label.stuffyear" />
							</form:label>
							<br>
							<form:select id="yearSelector" path="stuffsYear" style="width: 100%" />
						</div>
					</div>
					<h3 style="clear: both;"></h3>
					<div>
						<div style="float: left">
							<form:label path="sendNumber">
								<spring:message code="sendstuffpage.label.sendnumber" />
							</form:label>
							<br>
							<form:input type="text" path="sendNumber" class="textinpSmall" />
						</div>
						<div style="float: left">
							<form:label path="sendDate">
								<spring:message code="sendstuffpage.label.senddate" />
							</form:label>
							<br>
							<form:input type="date" path="sendDate" style="width: 100px" />
						</div>
						<div style="float: right;">
							<form:label path="sign">
								<spring:message code="sendstuffpage.label.sign" />
							</form:label>
							<br>
							<form:input type="text" path="sign" class="textinp" />
						</div>
					</div>
					<h3 style="clear: both;"></h3>
					<div style="float: right; margin-top: 10px">
						<input type="submit" value="Process">
					</div>
				</form:form>
			</div>
		</div>
	</div>


</body>
</html>