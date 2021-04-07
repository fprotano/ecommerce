<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "form" uri = "http://www.exolab.it/customtags" %>
<jsp:include page="../../templates/header.jsp">
<jsp:param value="Registrazione" name="title"/>
</jsp:include>

<div class="panel panel-default">
  <div class="panel-heading">Registrazione</div>
  <div class="panel-body">
  
		  
		  
		<form:form action="/user/do-register" method="post">
		<label>Email (*)</label><br>
		<input type="text" class="form-control" name="email" value="${model.email}"
		
		 readonly
onfocus="this.removeAttribute('readonly')"
/><br>
		<label>Password (*)</label><br>
		<input type="password" class="form-control" name="password" value="${model.password}"
		
		 readonly
onfocus="this.removeAttribute('readonly')"
		/><br>
		
		<label>Nome (*)</label><br>
		<input type="text" class="form-control"  name="name" value="${model.name}"/><br>
		<label>Cognome (*)</label><br>
		<input type="text" class="form-control"  name="surname" value="${model.surname}"/><br>
		
		<label>Telefono</label><br>
		<input type="text" class="form-control" name="phone" value="${model.phone}"/><br>
		
		<label>Indirizzo</label><br>
		<input type="text" class="form-control"  name="address" value="${model.address}"/><br>
		
		<label>Sconto (*)</label><br>
		<input type="text" class="form-control"  name="coupon" value="${model.discount.coupon}"/><br>
		<hr/>
		<input type="submit"  class="btn btn-primary" value="Registra"/><br>
		
		
		</form:form>

</div>
  
  <div class="panel-footer">
  * campi obbligatori
  <br/><a class="btn btn-info" href="${base_path}/user/login">Sono già registrato</a>
  </div>
  </div>
<jsp:include page="../../templates/footer.jsp"></jsp:include>
