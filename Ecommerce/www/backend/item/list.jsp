<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "form" uri = "http://www.exolab.it/customtags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%
String cp = request.getContextPath();
%>
<jsp:include page="../../templates/header.jsp">
<jsp:param value="Elenco prodotti" name="title"/>
</jsp:include>


<div class="panel panel-default">
  <div class="panel-heading">
Elenco prodotti
   </div>
  <div class="panel-body">
  
  
  
<table class="table table-striped">
<thead>
<th></th>
<th>Prodotto</th>
<th>Prezzo</th>
<th>Qta disp.</th>
<th></th>
</thead>
<tbody>
<c:forEach items="${model}" var="item">
<tr
<c:if test="${item.quantity==0}">
class="alert-danger"
</c:if>
>
<td>
<c:if test="${item.picture!=null}">
<img src="${base_path}/front/get-image?picture=${item.picture}" style="width:80px" class="img"/>
</c:if>
</td>
<td>${item.title}</td>
<td>${item.price}</td>
<td>${item.quantity}</td>
<td>
<a class="btn btn-info" href="${base_path}/admin-item/edit?id=${item.id}">Modifica</a>
<a class="btn btn-danger" href="${base_path}/admin-item/remove?id=${item.id}">Cancella</a>
   
</td>
</tr>
</c:forEach>
</tbody>
</table>

</div>
  <div class="panel-footer"><a class="btn btn-info"  href="${base_path}/admin-item/add">Aggiungi Prodotto &raquo;&raquo;</a></div>
</div>

<br/>




<jsp:include page="../../templates/footer.jsp"></jsp:include>