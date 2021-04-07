<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "form" uri = "http://www.exolab.it/customtags" %>
<jsp:include page="../templates/header.jsp">
<jsp:param value="Errore" name="title"/>
</jsp:include>
<div class="panel panel-primary">
  <div class="panel-heading">Errore ${status}</div>
  <div class="panel-body">

<c:choose>
<c:when test="${status=='500' }">
Impossibile elaborare la richiesta
</c:when>
<c:when test="${status=='404' }">
Pagina non trovata su questo server
</c:when>
</c:choose>  
  

</div>
</div>


<jsp:include page="../templates/footer.jsp"></jsp:include>
