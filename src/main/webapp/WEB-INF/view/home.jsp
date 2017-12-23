<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
	<head>
		<title>Spring Home Page</title>
	</head>
	<body>
		<h2>Spring Home Page</h2>
		<hr />
		
		<p>Welcome to the Spring Home Page!</p>
		
		
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