<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
        <div class="container">
          <h1>Edit Menu</h1>
    
		    <p class="text-danger"><form:errors path="user.*"/></p>
		    
		    <form:form method="POST" action="/updateMenu/${thisMenu.id}" modelAttribute="menu">
		        
		        <p>
		            <form:label path="name">Name:</form:label>
		            <form:input type="name" path="name" value="${thisMenu.name}"/>
		        </p>
		        <p>
		            <form:label path="description">Description:</form:label>
		            <form:input type="description" path="description" value="${thisMenu.description }"/>
		        </p>
		        
		        <input type="submit" value="Edit" class="btn btn-primary"/>
		    </form:form>
       </div><br>
</body>
</html>