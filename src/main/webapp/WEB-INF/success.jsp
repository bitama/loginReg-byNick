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
          <h1>Welcome ${user.userName}!!!!!!</h1>
          <a href="/logout">Logout</a>
          
          <table class="table table-dark table-striped">
              <thead>
                  <tr>
                     <th>Task</th>
                      <th>Creator</th>
                      <th>Assignee</th>
                      <th>Priority</th>
                  </tr>
              </thead>
              <tbody>
                    <c:forEach items="${allTasks}" var="t">
                     <tr>
                        <td><a href="/oneTask/${t.id}">${t.name}</a></td>
                        <td>${t.user.userName}</td>
                        <td>${t.assignee}</td>
                        <td>${t.priority}</td>
                     </tr>
                    </c:forEach>
              </tbody>
          </table>
          <a href="/newTask">create Task</a><br>
          
       </div>
</body>
</html>