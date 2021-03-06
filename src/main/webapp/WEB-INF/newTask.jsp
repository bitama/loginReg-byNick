<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
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
          <h1>Create a new Task</h1>
          
              <c:out value="${error}"/>
		    <p class="text-danger"><form:errors path="task.*"/></p>
		    
		    <form:form method="POST" action="/create" modelAttribute="task">
		         
		        <p>
		            <form:label path="name">Name:</form:label>
		            <form:input type="text" path="name"/>
		            <form:errors path= "name" class="text-danger"/>
		        </p>
		        <p>
		            <form:label path="assignee">Name:</form:label>
		            <form:input type="text" path="assignee"/>
		            <form:errors path= "assignee" class="text-danger"/>
		        </p>
		        <p>
		            <form:label path="priority">Name:</form:label>
		            <form:input type="text" path="priority"/>
		            <form:errors path= "priority" class="text-danger"/>
		        </p>
		        
		        <input type="submit" value="create task" class="btn btn-primary"/>
		    </form:form>
		    <a href="/success">Go back</a>
		 </div>
</body>
</html>