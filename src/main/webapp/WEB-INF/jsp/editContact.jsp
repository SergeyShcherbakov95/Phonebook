<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page session="false" %>
<html>
  <head>
    <title>Add Contact</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"></link>
	<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet"></link>
  </head>
  <body>
  	<div class="wrap">
  		<form class="form-horizontal" action="${contextPath}/user/editContact/${contact.getIdContact()}" method = "POST" >
  			<c:if test = "${not empty error}">
  				<div class="alert alert-info">
    				<p>${error}</p>
    			</div>
    		</c:if>
	  		<div class="form-group">
				<label class="col-sm-2 control-label">Surname</label>
				<div class="col-sm-6">
		  			<input type="text" class="form-control" name="surname" value = '${contact.getSurname()}'>
				</div>
	  		</div>
	  		<div class="form-group">
				<label class="col-sm-2 control-label">Name</label>
				<div class="col-sm-6">
		  			<input type="text" class="form-control" name="name" value = '${contact.getName()}'>
				</div>
	  		</div>
	  		<div class="form-group">
				<label class="col-sm-2 control-label">Patronymic</label>
				<div class="col-sm-6">
		  			<input type="text" class="form-control" name="patronymic" value = '${contact.getPatronymic()}'>
				</div>
	  		</div>
	  		<div class="form-group">
				<label class="col-sm-2 control-label">Mobile phone</label>
				<div class="col-sm-6">
		  			<input type="text" class="form-control" name="mobilePhone - +380(97)1234567" value = '${contact.getMobilePhone()}'>
				</div>
	  		</div>
	  		<div class="form-group">
				<label class="col-sm-2 control-label">Home phone</label>
				<div class="col-sm-6">
		  			<input type="text" class="form-control" name="homePhone - +380(97)1234567" value = '${contact.getHomePhone()}'>
				</div>
	  		</div>
	  		<div class="form-group">
				<label class="col-sm-2 control-label">Address</label>
				<div class="col-sm-6">
		  			<input type="text" class="form-control" name="address" value = '${contact.getAddress()}'>
				</div>
	  		</div>
	  		<div class="form-group">
				<label class="col-sm-2 control-label">Email</label>
				<div class="col-sm-6">
		  			<input type="text" class="form-control" name="email" value = '${contact.getEmail()}'>
				</div>
	  		</div>
	  		
	  		<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
		  			<input class="btn btn-primary" type="submit" value="Edit contact">
		  			<a href="${contextPath}/user/contacts"><input class="btn btn-primary" type="button" value="Contacts"></a>
		  			<a href="${contextPath}/logout"><input class="btn btn-primary" type="button" value="Logout"></a>
				</div>
	  		</div>
		</form>    
    </div>
  </body>
</html>