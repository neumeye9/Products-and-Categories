<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Add New Product</h1>
	<c:forEach items="${errors}" var="error">
		<h2><c:out value="${error.getDefaultMessage()}"/></h2>
	</c:forEach>
	<form:form method="POST" action="/product/new" modelAttribute="product">
	<input type="hidden" id="thisField" name="proid" value="${product.id}">
		<form:label path="name">Name:
		<form:errors path="name"/>
		<form:input path="name"/></form:label>
		<form:label path="description">Description:
		<form:errors path="description"/>
		<form:input path="description"/></form:label>
		<form:label path="price">Price:
		<form:errors path="price"/>
		<form:input path="price"/></form:label>
		<input type="submit" value="Submit"/>
	</form:form>

</body>
</html>