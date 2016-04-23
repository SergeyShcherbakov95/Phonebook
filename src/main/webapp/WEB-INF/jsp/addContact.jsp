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
  		<form class="form-horizontal" action="${contextPath}/user/addContact" method = "POST" >
  			<c:if test = "${not empty error}">
  				<div class="alert alert-info">
    				<p>${error}</p>
    			</div>
    		</c:if>
	  		<div class="form-group">
				<label class="col-sm-2 control-label">Surname</label>
				<div class="col-sm-6">
		  			<input type="text" class="form-control" placeholder="Enter surname" name="surname" value = '${contact.getSurname()}'>
				</div>
	  		</div>
	  		<div class="form-group">
				<label class="col-sm-2 control-label">Name</label>
				<div class="col-sm-6">
		  			<input type="text" class="form-control" placeholder="Enter name" name="name" value = '${contact.getName()}'>
				</div>
	  		</div>
	  		<div class="form-group">
				<label class="col-sm-2 control-label">Patronymic</label>
				<div class="col-sm-6">
		  			<input type="text" class="form-control" placeholder="Enter patronymic" name="patronymic" value = '${contact.getPatronymic()}'>
				</div>
	  		</div>
	  		<div class="form-group">
				<label class="col-sm-2 control-label">Mobile phone</label>
				<div class="col-sm-6">
		  			<input type="text" class="form-control" placeholder="Enter mobile phone - +380(97)1234567" name="mobilePhone" value = '${contact.getMobilePhone()}'>
				</div>
	  		</div>
	  		<div class="form-group">
				<label class="col-sm-2 control-label">Home phone</label>
				<div class="col-sm-6">
		  			<input type="text" class="form-control" placeholder="Enter home phone - +380(97)1234567" name="homePhone" value = '${contact.getHomePhone()}'>
				</div>
	  		</div>
	  		<div class="form-group">
				<label class="col-sm-2 control-label">Address</label>
				<div class="col-sm-6">
		  			<input type="text" class="form-control" placeholder="Enter address" name="address" value = '${contact.getAddress()}'>
				</div>
	  		</div>
	  		<div class="form-group">
				<label class="col-sm-2 control-label">Email</label>
				<div class="col-sm-6">
		  			<input type="text" class="form-control" placeholder="Enter email" name="email" value = '${contact.getEmail()}'>
				</div>
	  		</div>
	  		
	  		<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
		  			<input class="btn btn-primary" type="submit" value="Add contact">
		  			<a href="${contextPath}/user/contacts"><input class="btn btn-primary" type="button" value="Contacts"></a>
		  			<a href="${contextPath}/logout"><input class="btn btn-primary" type="button" value="Logout"></a>
				</div>
	  		</div>
		</form>    
    </div>
  </body>
</html>