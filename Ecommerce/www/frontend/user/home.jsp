<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../templates/header.jsp">
<jsp:param value="La tua homepage" name="title"/>
</jsp:include>
<div class="panel panel-default">
  <div class="panel-heading">Benvenuto ${user.name}</div>
  <div class="panel-body">
  
  <div class="row">
	  <div class="col-sm-6 text-center">
		  <div class="alert alert-info">
		  <h1>${user.discount.discount } %</h1>
		  hai uno sconto del ${user.discount.discount } %
		  </div>
	  </div>
	  <div class="col-sm-6 text-center">
		  <div class="alert alert-warning">
		  <h1>${orders }</h1>
		  hai ${orders } <a href="${base_path}/order/my-orders">ordini</a>
		  </div>
	  </div>
	  
  </div>
  
  
  
    
  </div>
  
  </div>
  
<jsp:include page="../../templates/footer.jsp"></jsp:include>