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
		<form:form action="${pageContext.request.contextPath}/logout" method="POST">
			<input type="submit" value="Logout" />		
		</form:form>
		
	</body>
</html>