<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Insert title here</title>
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"></link>
	<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet"></link>
</head>
<body>
	<div class="wrapcontacts">
	<div class="contacts">
		<a href="${contextPath}/user/addContact"><input class="btn btn-primary" type="button" value="Add contact"></a>
		<a href="${contextPath}/logout"><input class="btn btn-primary" type="button" value="Logout"></a>
		<p></p>
		<form class="form-inline" action="${contextPath}/users/contacts/search" method = "GET">
  			<div class="form-group">
    			<label>Surname</label>
    			<input type="text" name="surname" class="form-control">
  			</div>
  			<div class="form-group">
    			<label>Name</label>
    			<input type="text" name="name" class="form-control">
  			</div>
  			<div class="form-group">
    			<label>Phone</label>
    			<input type="text" name="phone" class="form-control">
  			</div>
  			<button type="submit" class="btn btn-default">Search</button>
		</form>
		
		<h1>Contacts</h1>
		<c:if test = "${not empty contacts}">
		<table class="table">
			<tr class="active">
    			<th>Surname</th>
    			<th>Name</th>
    			<th>Patronymic</th>
    			<th>Mobile phone</th>
    			<th>Home phone</th>
    			<th>Address</th>
    			<th>Email</th>
    			<th>Edit</th>
    			<th>Delete</th>
   			</tr>
    		<c:forEach items="${contacts}" var="contact">
    			<tr class="active">
    				<td>${contact.getSurname()}</td>
    				<td>${contact.getName()}</td>
    				<td>${contact.getPatronymic()}</td>
    				<td>${contact.getMobilePhone()}</td>
    				<td>${contact.getHomePhone()}</td>
    				<td>${contact.getAddress()}</td>
    				<td>${contact.getEmail()}</td>
    				<td><a href = "${contextPath}/user/editContact/${contact.getIdContact()}" >Edit</a></td>
    				<td><a href = "${contextPath}/user/deleteContact/${contact.getIdContact()}" >Delete</a></td>
    			</tr>
    		</c:forEach>
    	</table>
    </c:if> 
	</div>
	</div>
</body>
</html>