<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "form" uri = "http://www.exolab.it/customtags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../../templates/header.jsp">
<jsp:param value="Inserisci/Modifica prodotto" name="title"/>
</jsp:include>


<div class="panel panel-default">
  <div class="panel-heading">
  <c:if test="${model.id>0 }">
Modifica prodotto
</c:if>
  <c:if test="${model.id==0 }">
Inserisci prodotto
</c:if>

   </div>
  <div class="panel-body">
  
  
<form:form action="/admin-item/save" method="post" enctype="multipart/form-data" >

<c:if test="${model.id>0 }">
<input type="hidden" name="id" value="${model.id}"/>
</c:if>


<label>Titolo (*)</label><br>
		<input type="text" class="form-control" name="title" value="${model.title}"
/><br>

<label>Prezzo (*)</label><br>
		<input type="text" class="form-control" name="price" value="${model.price}"
/><br>

<label>Quantità (*)</label><br>
		<input type="text" class="form-control" name="quantity" value="${model.quantity}"
/><br>

<label>Immagine (*)</label><br>
		<input type="file" class="form-control" name="picture" value="${model.picture}"
/><br>


<label>Categoria (*)</label><br>
<select class="form-control" name="cid">
<option value="">---</option>
<c:forEach items="${categories}" var="item">
<option
<c:if test="${model.cid!=null && model.cid==item.id}">
selected
</c:if> 
value="${item.id}">${item.title}</option>
</c:forEach>
</select>
		<br>




<label>Decrizione (*)</label><br>
		<textarea rows="5" cols="80" class="form-control" name="description">${model.description}</textarea>
<br>
<hr/>
<input class="btn btn-primary" type="submit" value="Salva"/>

</form:form>

</div>
<div class="panel-footer"><a class="btn btn-info"  href="${base_path}/admin-item/list">&laquo;&laquo; Indietro</a></div>
</div>

<br/>


<jsp:include page="../../templates/footer.jsp"></jsp:include>