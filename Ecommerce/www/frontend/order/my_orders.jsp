<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../../templates/header.jsp">
<jsp:param value="I miei ordini" name="title"/>
</jsp:include>

<div class="panel panel-default">
  <div class="panel-heading">I tuoi ordini</div>
  <div class="panel-body">
  
  
  
		<table  class="table table-striped">
		<tr>
		<td>N. ordine</td>
		<td>Stato</td>
		<td>Data<br/>ult.agg.</td>
		<td>Totale</td>
		<td></td>
		</tr>
		<c:forEach items="${model}" var="item">
		<tr>
			<td><a href="${base_path}/order/my-orders/view?id=${item.id}">${item.order_no}</a></td>
			<td>
			
			<c:if test="${item.isConfermato}">
				Ordine confermato
				</c:if>
				
			<c:if test="${item.isPresoInCarico}">
				 
				Ordine preso in carico
				</c:if>
				
				<c:if test="${item.isSpedito}">
				Ordine Spedito
				</c:if>
				
				<c:if test="${item.isConsegnato}">
				 
				Ordine Consegnato
				</c:if>
			</td>
			<td>
			<fmt:formatDate type = "date" value = "${item.updated_at}" />
			</td>
			
			<td>
			<b>
			<fmt:formatNumber value="${ item.total }" maxFractionDigits = "2"  
			type = "currency"
			currencySymbol="&euro;" 
			/>
			</b>
			</td>
			<td><a class="btn btn-primary" href="${base_path}/order/my-orders/view?id=${item.id}">Dettagli</a></td>
			
		</tr>
		</c:forEach>
		</table>
</div>
</div>

<jsp:include page="../../templates/footer.jsp"></jsp:include>