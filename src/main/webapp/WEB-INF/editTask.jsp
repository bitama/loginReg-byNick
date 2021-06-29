<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

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
          <h1>Edit </h1>
    
		    <p class="text-danger"><form:errors path="task.*"/></p>
		    
		    <form:form method="POST" action="/updateTask/${thisTask.id}" modelAttribute="task">
		        
		        <p>
		            <form:label path="name">Name:</form:label>
		            <form:input type="name" path="name" value="${thisTask.name}"/>
		            <form:errors path= "name" class="text-danger"/>
		        </p>
		        <p>
		            <form:label path="assignee">Assignee:</form:label>
		            <form:input type="text" path="assignee" value="${thisTask.assignee }"/>
		            <form:errors path= "assignee" class="assignee"/>
		        </p>
		        <p>
		            <form:label path="priority">Priority:</form:label>
		            <form:input type="text" path="priority" value="${thisTask.priority }"/>
		            <form:errors path= "priority" class="priority"/>
		        </p>
		        
		        <input type="submit" value="Edit" class="btn btn-primary"/>
		    </form:form>
		    <a href="/success">Go to success page</a>
		    
       </div>
</body>
</html>