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
<script type="text/javascript" src="../js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="../js/jquery.cookie.js"></script>

<script type="text/javascript">
	var selectedRow = null;
	// read cookies on page load.
	function adminOnLoad() {
		selectedRow = $.cookie("selectedRow");
		selectRow(selectedRow);
	}
	
	// store data into cookies.
	function adminOnUnload() {
		$.cookie("selectedRow", selectedRow, {expires: 1});
	}
	
	function selectRow(newRowId) {
		// unmark old selected row.
		var oldrow = document.getElementById('row_' + selectedRow);
		if (oldrow != null) {
			oldrow.setAttribute("class", "");
		}
		// mark old selected row.
		var newrow = document.getElementById('row_' + newRowId);
		if (newrow != null) {
			newrow.setAttribute("class", "selRow");
		}
		// store new value.
		selectedRow = newRowId;
	}
	
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administration Tools</title>
</head>
<body onload="adminOnLoad()" onunload="adminOnUnload()">
	<%@include file="header.jsp" %>
	
	<div id="top">
		<div class="formMainWrap">
			<h4>List of users:</h4>
			<div class="formBottomWrap">
				<form name="userListForm">
					<table id="userListTable" class="entityTable" border="1">
						<tr>
							<th class="hiddencol">ID</th>
							<th><spring:message code="adminpage.label.name" /></th>
							<th><spring:message code="adminpage.label.password" /></th>
							<th><spring:message code="adminpage.label.firstname" /></th>
							<th><spring:message code="adminpage.label.lastname" /></th>
							<th><spring:message code="adminpage.label.role" /></th>
							<th><spring:message code="adminpage.label.actions" /></th>
						</tr>
						<c:forEach items="${userList}" var="user" varStatus="i">
							<tr id="row_${user.id}" class="" onclick='selectRow(${user.id})'>
								<td class="hiddencol">${user.id}</td>
								<td>${user.name}</td>
								<td>${user.password}</td>
								<td>${user.firstname}</td>
								<td>${user.lastname}</td>
								<td>${user.role.name}</td>
								<td>pictures</td>
							</tr>
						</c:forEach>
					</table>
				</form>
			</div>
			<br>
			<div class="ButtonPanel">
				<form id="FormBtnAddUser" style="float: left">
					<button onclick='AdminLib.onAddUserBtn()'>Add User</button>
				</form>
				<form id="FormBtnEditUser" style="float: left">
					<button onclick='AdminLib.onEditUserBtn()'>Edit User</button>
				</form>
				<form id="FormBtnDeleteUser" style="float: left">
					<button onclick='AdminLib.onDeleteUserBtn()'>Delete User</button>
				</form>
			</div>
		</div>

		<div id="editUserFormId" class="formMainWrap">
			<h4>User editing:</h4>
			<div class="formBottomWrap">
				<form:form commandName="EditableUser" method="post" action="submitUser">
					<table>
						<tr>
							<td class="tdLabel"><form:label path="name">
									<spring:message code="adminpage.label.name" />
								</form:label></td>
							<td><form:input type="text" path="name" /> <form:errors path="name" cssClass="error" /></td>
						</tr>

						<tr>
							<td class="tdLabel"><form:label path="password">
									<spring:message code="adminpage.label.password" />
								</form:label></td>
							<td><form:input type="text" path="password" /> <form:errors path="password" cssClass="error" /></td>
						</tr>

						<tr>
							<td class="tdLabel"><form:label path="firstname">
									<spring:message code="adminpage.label.firstname" />
								</form:label></td>
							<td><form:input type="text" path="firstname" /> <form:errors path="firstname" cssClass="error" /></td>
						</tr>

						<tr>
							<td class="tdLabel"><form:label path="lastname">
									<spring:message code="adminpage.label.lastname" />
								</form:label></td>
							<td><form:input type="text" path="lastname" /> <form:errors path="lastname" cssClass="error" /></td>
						</tr>

						<tr>
							<td class="tdLabel"><form:label path="role.id">
									<spring:message code="adminpage.label.role" />
								</form:label></td>
							<td><form:select path="role.id" items="${selectableRoles}" itemValue="id" itemLabel="name"
									value="${editableUser.role.id}">
								</form:select></td>
						</tr>
						<tr>
							<td />
							<td class="tdSubmit"><input name="submit" type="submit" value="Save" /></td>
						</tr>
					</table>
				</form:form>
			</div>
		</div>

	</div>
</body>
</html>