<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
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
          <h1>Let make a menu</h1>
    
		    <p class="text-danger"><form:errors path="user.*"/></p>
		    
		    <form:form method="POST" action="/create" modelAttribute="menu">
		         <form:hidden path="user" value="${user.id}"/>
		        <p>
		            <form:label path="name">Name:</form:label>
		            <form:input type="text" path="name"/>
		        </p>
		        <p>
		            <form:label path="description">Description:</form:label>
		            <form:textarea type="description" path="description"/>
		        </p>
		        
		        <input type="submit" value="create Menu" class="btn btn-primary"/>
		    </form:form>
		 </div>
</body>
</html>