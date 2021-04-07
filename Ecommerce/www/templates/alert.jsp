<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "flash" uri = "http://www.exolab.it/customtags" %>
<flash:has attribute="err">
<div class="alert alert-danger">
	<flash:get attribute="err"/>
</div>
</flash:has>

