<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Category</title>
</head>
<body>
<h1>Add New Category</h1>
	<c:forEach items="${errors}" var="error">
		<h2><c:out value="${error.getDefaultMessage()}"/></h2>
	</c:forEach>
	<form:form method="POST" action="/category/new" modelAttribute="category">
	<input type="hidden" id="thisField" name="catid" value="${category.id}">
		<form:label path="name">Name:
		<form:errors path="name"/>
		<form:input path="name"/></form:label>
		<input type="submit" value="Submit"/>
	</form:form>

</body>
</html>