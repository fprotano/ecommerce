<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "form" uri = "http://www.exolab.it/customtags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../templates/header.jsp">
<jsp:param value="Homepage" name="title"/>
</jsp:include>
<div class="panel panel-primary">
  <div class="panel-heading">Il nostro catalogo</div>
  <div class="panel-body">
  
  
  
<table class="table table-striped">
<thead>
<th></th>
<th>Prodotto</th>
<th>Qta. disponibile</th>
<th>Prezzo</th>
<th></th>
<th></th>
</thead>
<tbody>
<c:forEach items="${model}" var="item">
<form:form action="/cart/insert" method="post" onsubmit="return formValidate(this);">
<input type="hidden" value="${item.id}" name="iid"/>
<input type="hidden" value="${item.quantity}" name="quantity_available"/>
<tr>
<td>
<c:if test="${item.picture!=null}">
<a href="${base_path}/front/get-image?picture=${item.picture}" target="_blank"><img src="${base_path}/front/get-image?picture=${item.picture}" style="width:80px" class="img"/></a>
</c:if>
</td>
<td>${item.title}</td>
<td>${item.quantity}</td>
<td>
<b>
<fmt:formatNumber value="${ item.price }" maxFractionDigits = "2"  
			type = "currency"
			currencySymbol="&euro;" 
			/>
	</b>		
</td>
<td><input class="form-control" type="text" value="0" name="quantity" onfocus="this.value=1;"/></td>
<td><input class="btn btn-primary" type="submit" value="Aggiungi"/></td>
</tr>


</form:form>
</c:forEach>
</tbody>
</table>
</div>
</div>

<script>
function formValidate(o){
	var quantity_available = o.quantity_available.value;
	var quantity = o.quantity.value;
	if(isNaN(quantity)){
		alert("quantità errata");
		return false;
	}
	if(quantity<=0){
		alert("Quantità minima 1");
		return false;
	}
		
	if(parseInt(quantity)>parseInt(quantity_available)){
		alert("Quantità massima "+ quantity_available);
		return false;
	}


	return true;
}
</script>

<jsp:include page="../templates/footer.jsp"></jsp:include>
