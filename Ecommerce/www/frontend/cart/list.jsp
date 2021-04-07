<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "form" uri = "http://www.exolab.it/customtags" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="rowsCount" scope="request" value="${fn:length(cart.rows)}" />
<jsp:include page="../../templates/header.jsp">
<jsp:param value="Contenuto del carrello" name="title"/>
<jsp:param value="false" name="show_cart"/>
</jsp:include>
<c:if test="${rowsCount>0}">
<jsp:include page="../../templates/_cart.jsp">
	<jsp:param value="true" name="readonly"/>
</jsp:include>
</c:if>

<div class="panel panel-default">
  <div class="panel-heading">
  <c:if test="${rowsCount==0}">
  Carrello vuoto
   </c:if>
   
  <c:if test="${rowsCount>0}">
  Conferma ordine
   </c:if>
   </div>
  <div class="panel-body">
   
    <c:if test="${rowsCount>0}">
  <form:form action="/order/confirm-cart" method="post">
  
  <ul class="list-group">
		<c:if test="${user!=null}">
		<li class="list-group-item">Sconto applicato : <b>${user.discount.discount } %</b></li> 
		</c:if>
		<li class="list-group-item">Totale prodotti nel carrello : <b>${rowsCount}</b></li>
		<li class="list-group-item">Totale carrello: <b>
		
		<fmt:formatNumber value="${ cart.cartTotal }" maxFractionDigits = "2"  
			type = "currency"
			currencySymbol="&euro;" 
			/>
			
			
		</b> &euro;</li>
		<li class="list-group-item">Totale sconto: <b>
		
		<fmt:formatNumber value="${ cart.discountTotal }" maxFractionDigits = "2"  
			type = "currency"
			currencySymbol="&euro;" 
			/>
		</b> &euro;</li>
		<li class="list-group-item">Totale da pagare: 
		
		<b>
		<fmt:formatNumber value="${ cart.total }" maxFractionDigits = "2"  
			type = "currency"
			currencySymbol="&euro;" 
			/>
			</b>
			</li>
		</ul>
		
		

  
   <input class="btn btn-success" type="submit" value="Conferma prodotti nel carrello"/>
  
  </form:form>
		 </c:if>
  </div>
  <div class="panel-footer"><a class="btn btn-info" href="${base_path}/front/home">Aggiungi altri prodotti</a></div>
</div>

<jsp:include page="../../templates/footer.jsp"></jsp:include>