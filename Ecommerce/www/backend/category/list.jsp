<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "form" uri = "http://www.exolab.it/customtags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%
String cp = request.getContextPath();
%>
<jsp:include page="../../templates/header.jsp">
<jsp:param value="Elenco categorie" name="title"/>
</jsp:include>


<div class="panel panel-default">
  <div class="panel-heading">
Elenco categorie
   </div>
  <div class="panel-body">
  
  
  
<table class="table table-striped">
<thead>
<th>Categoria</th>
<th></th>
</thead>
<tbody>
<c:forEach items="${model}" var="item">
<tr>
<td>${item.title}</td>
<td>
<a class="btn btn-info" href="${base_path}/admin-category/edit?id=${item.id}">Modifica</a>
<a class="btn btn-danger" href="${base_path}/admin-category/remove?id=${item.id}">Cancella</a>
   
</td>
</tr>
</c:forEach>
</tbody>
</table>

</div>
  <div class="panel-footer"><a class="btn btn-info"  href="${base_path}/admin-category/add">Aggiungi Categoria &raquo;&raquo;</a></div>
</div>

<br/>




<jsp:include page="../../templates/footer.jsp"></jsp:include>