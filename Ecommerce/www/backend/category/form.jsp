<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "form" uri = "http://www.exolab.it/customtags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../../templates/header.jsp">
<jsp:param value="Inserisci/Modifica categoria" name="title"/>
</jsp:include>


<div class="panel panel-default">
  <div class="panel-heading">
  <c:if test="${model.id>0 }">
Modifica categoria
</c:if>
  <c:if test="${model.id==0 }">
Inserisci categoria
</c:if>

   </div>
  <div class="panel-body">
  
  
<form:form action="/admin-category/save" method="post" >

<c:if test="${model.id>0 }">
<input type="hidden" name="id" value="${model.id}"/>
</c:if>


<label>Titolo (*)</label><br>
		<input type="text" class="form-control" name="title" value="${model.title}"
/><br>
<br>
<hr/>
<input class="btn btn-primary" type="submit" value="Salva"/>

</form:form>

</div>
<div class="panel-footer"><a class="btn btn-info"  href="${base_path}/admin-category/list">&laquo;&laquo; Indietro</a></div>
</div>

<br/>


<jsp:include page="../../templates/footer.jsp"></jsp:include>