<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../templates/header.jsp">
<jsp:param value="Login" name="title"/>
</jsp:include>
<div class="panel panel-default">
  <div class="panel-heading">Login Admin</div>
  <div class="panel-body">
  
  
  
<form action="do-login" method="post">
<label>Email</label><br/>
<input class="form-control" type="text" name="email" value="${model.email}" readonly
onfocus="this.removeAttribute('readonly')"
/><br/>
<label>Password</label><br/>
<input class="form-control" type="password" name="password" value="${model.password}"

 readonly
onfocus="this.removeAttribute('readonly')"

/><br/>
<hr/>
<input class="btn btn-primary" type="submit" value="Entra"/>
</form>


  </div>
  	<div class="panel-footer"></div>
  </div>
  
<jsp:include page="../templates/footer.jsp"></jsp:include>