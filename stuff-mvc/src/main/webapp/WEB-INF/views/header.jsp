<div id="header">
	<a class="homeUrl" href="<c:url value="${homePageUrl}"/>"> <img
		src="../image/home_button2.png" height="40" width="120"></img>
	</a>
	<h2>
		<spring:message code="${headerHtmlContext}" />
	</h2>
	<label class="userFullName">(${userFullName})</label> <a
		class="logoutUrl" href="<c:url value="../j_spring_security_logout" />">
		<spring:message code='header.logout' />
	</a>
</div>