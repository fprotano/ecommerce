<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "form" uri = "http://www.exolab.it/customtags" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${cart!=null && cart.rows!=null}">
<div class="panel panel-default">
  <div class="panel-heading">Il tuo carrello</div>
  <div class="panel-body">
  
  
	<c:set var="rowsCount" scope="request" value="${fn:length(cart.rows)}" />
	<c:if test="${rowsCount>0}">
	
		<table class="table table-striped">
		<tr>
		<td></td>
		<td>Prodotto</td>
		<td>Prezzo</td>
		<td>Qta</td>
		<td>Tot</td>
		<td></td>
		</tr>
		<c:forEach items="${cart.rows}" var="item">
		<tr style="font-size:11px">
		<td >
<c:if test="${item.item.picture!=null}">
<a href="${base_path}/front/get-image?picture=${item.item.picture}" target="_blank"><img src="${base_path}/front/get-image?picture=${item.item.picture}" style="width:16px" class="img"/>
</a>
</c:if>
</td>
			<td>${item.item.title}</td>
			<td>
			
			<fmt:formatNumber value="${ item.price }" maxFractionDigits = "2"  
			type = "currency"
			currencySymbol="&euro;" 
			/>
			
			</td>
			<td>${item.quantity}</td>
			<td>
			<fmt:formatNumber value="${ item.quantity*item.price }" maxFractionDigits = "2"  
			type = "currency"
			currencySymbol="&euro;" 
			/>
			</td>
			<td>
			<c:if test="${param.readonly==null || param.readonly!='true'}">
			<a class="btn btn-danger" href="${base_path}/cart/remove?id=${item.id}">X</a>
			</c:if>
			</td>
		</tr>
		
		</c:forEach>
		</table>
		
		
	
	</c:if>
	<c:if test="${rowsCount==0}">
	<div class="alert alert-info">Il carrello è vuoto!</div>
	</c:if>
</div>
<c:if test="${param.readonly==null || param.readonly!='true'}">
	<c:if test="${rowsCount>0}">
		<div class="panel-footer">
		<ul class="list-group">
		<c:if test="${user!=null}">
		<li class="list-group-item">Sconto applicato : <b>${user.discount.discount } %</b></li> 
		</c:if>
		<li class="list-group-item">Totale carrello: <b>${cart.cartTotal}</b> &euro;</li>
		<li class="list-group-item">Totale sconto: <b>${cart.discountTotal}</b> &euro;</li>
		<li class="list-group-item">Totale da pagare: <b>${cart.total}</b> &euro;</li>
		</ul>
		
		
		<a class="btn btn-success" href="${base_path}/cart/list">Conferma carrello</a>
		
		
		
		</div>
	</c:if>
	</c:if>
	
</div>

</c:if>

