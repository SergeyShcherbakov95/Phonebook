<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page session="false" %>
<html>
  <head>
    <title>Sign In</title>
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"></link>
	<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet"></link>
  </head>
  <body>
	<div class="wrap">
		<c:url var="loginUrl" value="/j_spring_security_check"></c:url>
  		<form class="form-horizontal" action="${loginUrl}" method="POST" >
  			<c:if test = "${not empty error}">
  				<div class="alert alert-info">
    				<p>${error}</p>
    			</div>
    		</c:if>
	  		<div class="form-group">
				<label for="inputLogin" class="col-sm-2 control-label">Login</label>
				<div class="col-sm-6">
		  			<input type="text" class="form-control" id="inputLogin" placeholder="Enter your login" name="login">
				</div>
	  		</div>
	  		<div class="form-group">
				<label for="inputPassword3" name="password" class="col-sm-2 control-label">Password</label>
				<div class="col-sm-6">
		  			<input type="password" class="form-control" id="inputPassword3" placeholder=" Enter your password" name="password">
				</div>
	  		</div>
	  		<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
		  			<input class="btn btn-primary" type="submit" value="Sign in">
		  			<a href="${contextPath}/register"><input class="btn btn-primary" type="button" value="Register"></a>
				</div>
	  		</div>
		</form>    
    </div>
  </body>
</html>
