<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "form" uri = "http://www.exolab.it/customtags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../../templates/header.jsp">
<jsp:param value="Dettaglio Ordine" name="title"/>
</jsp:include>


<div class="panel panel-default">
  <div class="panel-heading">Dettaglio Ordine</div>
  <div class="panel-body">
  
  
<form:form action="/admin-order/update" method="post" >

<input type="hidden" name="id" value="${model.id}"/>


<div class="row">
	<div class="col-sm-2">N. ordine</div>
	<div class="col-sm-2"><b>${model.order_no}</b></div>
	
	<div class="col-sm-2">Totale</div>
	<div class="col-sm-2">
	
	<b>
			<fmt:formatNumber value="${model.total}" maxFractionDigits = "2"  
			type = "currency"
			currencySymbol="&euro;" 
			/>
			</b>
			
			</div>
			
	<div class="col-sm-2">Stato ordine /data modifica</div>
	<div class="col-sm-2">
	<c:if test="${model.isConfermato}">
	Ordine confermato
	</c:if>
	<c:if test="${model.isPresoInCarico}">
	Ordine preso in carico
	</c:if>
	<c:if test="${model.isSpedito}">
	Ordine spedito
	</c:if>
	<c:if test="${model.isConsegnato}">
	Ordine consegnato
	</c:if>
	
	/ 
	<fmt:formatDate type = "date" value = "${model.updated_at}" />
	
	</div>
				
	
</div>

<div class="row">
	<div class="col-sm-3">Cliente</div>
	<div class="col-sm-3"><b>${model.user.fullname}</b></div>
	
	<div class="col-sm-3">Email cliente</div>
	<div class="col-sm-3"><b>${model.user.email}</b></div>
	
</div>
<div class="row">
<div class="col-sm-12">

<div class="panel panel-default">
  <div class="panel-heading">Righe dell'ordine</div>
  <div class="panel-body">
<br/>

<table  class="table table-striped">
		<tr>
		<td>Prodotto</td>
		<td>Prezzo<br/>prodotto</td>
		<td>Quantità<br/>magazzino</td>
		<td>Prezzo<br/>vendita</td>
		<td>Quantità<br/>richiesta</td>
		</tr>
		<c:forEach items="${model.rows}" var="item">
		<tr>
			<td>${item.item.title}</td>
			
			
			<td>
			<b>
			<fmt:formatNumber value="${item.item.price }" maxFractionDigits = "2"  
			type = "currency"
			currencySymbol="&euro;" 
			/>
			</b>
			</td>
			<td>${item.item.quantity}</td>
			<td>
			
			<b>
			<fmt:formatNumber value="${item.price }" maxFractionDigits = "2"  
			type = "currency"
			currencySymbol="&euro;" 
			/>
			</b>
			
			</td>
			<td>${item.quantity}</td>
			
			
		</tr>
		</c:forEach>
		</table>
		
		
		</div>
		</div>
		</div>
</div>


<hr/>
<c:if test="${model.isConfermato}">
<input class="btn btn-primary" name="setStatusPresoInCarico" 
type="submit" value="Ordine preso in carico"/>
</c:if>

<c:if test="${model.isPresoInCarico}">
<input class="btn btn-primary" name="setStatusSpedito" 
type="submit" value="Ordine Spedito!"/>
</c:if>

<c:if test="${model.isSpedito}">
<input class="btn btn-primary" name="setStatusConsegnato" 
type="submit" value="Ordine Consegnato!"/>
</c:if>

</form:form>

</div>
<div class="panel-footer"><a class="btn btn-info"  href="${base_path}/admin-order/list">&laquo;&laquo; Indietro</a></div>
</div>

<br/>


<jsp:include page="../../templates/footer.jsp"></jsp:include>