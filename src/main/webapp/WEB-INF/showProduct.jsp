<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show Product</title>
</head>
<body>
	<h1> <c:out value="${product.name}"/> </h1>

	<h2>Categories:</h2>

	<c:forEach items="${product.categories}" var="category">
		<h3> <c:out value="${category.name}"/> </h3>
	</c:forEach>

	<h3>Add Category</h3>
	
	<form method="post" action="/product/${product.id}">
		<select name="category">
			<c:forEach items="${allCats}" var="category">
				<option value="${category.id}"> ${category.name}</option>
			</c:forEach>
		</select>
		
		<input type="submit" value="Add"></input>
	</form>
</body>
</html>