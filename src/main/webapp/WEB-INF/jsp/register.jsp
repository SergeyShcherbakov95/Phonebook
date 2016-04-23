<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page session="false" %>
<html>
  <head>
    <title>Sign Up</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"></link>
	<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet"></link>
  </head>
  <body>
  	<div class="wrap">
  		<form class="form-horizontal" action="${contextPath}/register" method = "POST" >
  			<c:if test = "${not empty error}">
  				<div class="alert alert-info">
    				<p>${error}</p>
    			</div>
    		</c:if>
	  		<div class="form-group">
				<label for="inputLogin" class="col-sm-2 control-label">Login</label>
				<div class="col-sm-6">
		  			<input type="text" class="form-control" id="inputLogin" placeholder="Enter your login" name="login" value = '${user.getLogin()}'>
				</div>
	  		</div>
	  		<div class="form-group">
				<label for="inputPassword3" name="password" class="col-sm-2 control-label">Password</label>
				<div class="col-sm-6">
		  			<input type="password" class="form-control" id="inputPassword3" placeholder=" Enter your password" name="password" value = '${user.getUserPassword()}'>
				</div>
	  		</div>
	  		<div class="form-group">
				<label name="fullName" class="col-sm-2 control-label">Full Name</label>
				<div class="col-sm-6">
		  			<input type="text" class="form-control" placeholder="Enter your full name" name="fullName" value = '${user.getFullName()}'>
				</div>
	  		</div>
	  		<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
		  			<input class="btn btn-primary" type="submit" value="Register">
		  			<a href="${contextPath}/login"><input class="btn btn-primary" type="button" value="Sign In"></a>
				</div>
	  		</div>
		</form>    
    </div>
  </body>
</html>