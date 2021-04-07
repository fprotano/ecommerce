<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="${pageContext.servletContext.contextPath}/js/jquery-3.6.0.min.js"></script> 
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/bootstrap/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/bootstrap/css/bootstrap-theme.min.css">

<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/style.css">


<!-- Latest compiled and minified JavaScript -->
<script src="${pageContext.servletContext.contextPath}/bootstrap/js/bootstrap.min.js"></script>

<script src="${pageContext.servletContext.contextPath}/ckeditor/ckeditor.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${param.title}</title>



</head>
<body>


<nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="${base_path}/front/home">Ecommerce</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
          <c:if test="${admin==null}">
          
          <li><a href="${base_path}/cart/list">Carrello
            	<c:if test="${items_in_cart!=null}">
            	(${items_in_cart})
            	</c:if>
            	</a></li>
          </c:if>  	
          <c:if test="${user!=null}">
            	<li class="active"><a href="${base_path}/user/home">Home</a></li>
            	
            	
            	<li><a href="${base_path}/user/logout">Logout</a></li>
            </c:if>
            
			<c:if test="${admin!=null}">
			  	<li class="active"><a href="${base_path}/admin/home">Home</a></li>
			  	<li><a href="${base_path}/admin-order/list">Ordini</a></li>
          		<li><a href="${base_path}/admin-item/list">Prodotti</a></li>
          		<li><a href="${base_path}/admin-category/list">Categorie</a></li>
          		<li><a href="${base_path}/admin-discount/list">Sconti</a></li>
				<li><a href="${base_path}/admin/logout">Logout</a></li>
			</c:if>
            
            
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
    


<div class="container" style="margin-top:10px">
<br/><br/>&nbsp;
<jsp:include page="./alert.jsp"></jsp:include>
<br/>&nbsp;
<c:if test="${param.show_cart==null || param.show_cart=='true'}">

	<c:if test="${cart!=null}">
	<div class="row">
	<div class="col-sm-4"><jsp:include page="./_cart.jsp"></jsp:include></div>
	<div class="col-sm-8">
	
	</c:if>


</c:if>