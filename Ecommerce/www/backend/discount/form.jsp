<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "form" uri = "http://www.exolab.it/customtags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../../templates/header.jsp">
<jsp:param value="Inserisci/Modifica sconto" name="title"/>
</jsp:include>


<div class="panel panel-default">
  <div class="panel-heading">
  <c:if test="${model.id>0 }">
Modifica sconto
</c:if>
  <c:if test="${model.id==0 }">
Inserisci sconto
</c:if>

   </div>
  <div class="panel-body">
  
  
<form:form action="/admin-discount/save" method="post" >

<c:if test="${model.id>0 }">
<input type="hidden" name="id" value="${model.id}"/>
</c:if>


<label>Coupon (*)</label><br>
<input type="text" class="form-control" name="coupon" value="${model.coupon}"/><br>

<label>Sconto (*)</label><br>
<input type="text" class="form-control" name="discount" value="${model.discount}"/><br>


<hr/>
<input class="btn btn-primary" type="submit" value="Salva"/>

</form:form>

</div>
<div class="panel-footer"><a class="btn btn-info"  href="${base_path}/admin-discount/list">&laquo;&laquo; Indietro</a></div>
</div>

<br/>


<jsp:include page="../../templates/footer.jsp"></jsp:include>