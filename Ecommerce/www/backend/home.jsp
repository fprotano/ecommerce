<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../templates/header.jsp">
<jsp:param value="Home admin" name="title"/>
</jsp:include>
<div class="panel panel-default">
  <div class="panel-heading">La tua home</div>
  <div class="panel-body">
  
  
  
Questa è la tua home ${admin.email}


  </div>
 
  </div>
  
<jsp:include page="../templates/footer.jsp"></jsp:include>