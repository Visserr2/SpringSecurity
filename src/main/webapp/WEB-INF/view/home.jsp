<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
	<head>
		<title>Spring Home Page</title>
	</head>
	<body>
		<h2>Spring Home Page</h2>
		<hr />
		
		<p>Welcome to the Spring Home Page!</p>
		
		<hr />
		
		User: <sec:authentication property="principal.username"/>
		<br /><br />
		Role(s): <sec:authentication property="principal.authorities"/>
		
		<hr />
		
		<sec:authorize access="hasRole('MANAGER')" >
		
		<p><a href="${pageContext.request.contextPath}/leaders">Leadership Meeting</a></p>
		
		</sec:authorize>
		
		<hr />
		
		<sec:authorize access="hasRole('ADMIN')" >
		
		<p><a href="${pageContext.request.contextPath}/admin">Admin Meeting</a></p>
		
		</sec:authorize>
		
		<hr />
		
		<!-- Adding logout button -->
		<!-- Always use form:form-tag for csrf-protection. Else you must add csrf-parameterName and csrf-token manually to every form-tag -->
		<form:form action="${pageContext.request.contextPath}/logout" method="POST">
			<input type="submit" value="Logout" />
			
			<!-- Manually adding csrf-token 
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			-->
			
		</form:form>
		
	</body>
</html>