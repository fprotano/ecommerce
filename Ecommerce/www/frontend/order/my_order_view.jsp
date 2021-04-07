<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../../templates/header.jsp">
<jsp:param value="Dettaglio ordine" name="title"/>
</jsp:include>

<div class="panel panel-default">
  <div class="panel-heading">Ordine no. <b>${model.order_no}</b></div>
  <div class="panel-body">
  
  
		<div class="alert alert-warning">
		  è stato praticato uno sconto del ${model.discount} % su tutti i prodotti acquistati
		  </div>


<table  class="table table-striped">
<tr>
<td>Prodotto</td>
<td>Prezzo</td>
<td>Quantità</td>
<td>Totale</td>
</tr>
<c:forEach items="${model.rows}" var="item">
<tr>
	<td>${item.item.title}</td>
	<td>
	
	<fmt:formatNumber value="${ item.price }" maxFractionDigits = "2"  
			type = "currency"
			currencySymbol="&euro;" 
			/>
			
			</td>
	<td>${item.quantity}</td>
	<td>
	
	<fmt:formatNumber value="${ item.price*item.quantity }" maxFractionDigits = "2"  
			type = "currency"
			currencySymbol="&euro;" 
			/>
			
			</td>
	
</tr>
</c:forEach>
</table>
</div>
<div class="panel-footer"><a class="btn btn-primary" href="${base_path}/order/my-orders">Indietro</a></div>
</div>

<jsp:include page="../../templates/footer.jsp"></jsp:include>