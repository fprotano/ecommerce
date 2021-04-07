<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<c:if test="${param.show_cart==null || param.show_cart=='true'}">
	<c:if test="${cart!=null}">
	</div>
	</div>
	</c:if>
</c:if>


</div>
<footer  class="container">
<p>&copy; Exolab 2021 
<c:if test="${user==null && admin==null}">
| <a href="${base_path}/user/login">Login</a>
| <a href="${base_path}/user/register">Registrazione</a>
</c:if>
<c:if test="${user!=null}">
| <a href="${base_path}/order/my-orders">Ordini</a>
</c:if>
<c:if test="${admin==null}">
| <a href="${base_path}/admin/login">Amministra</a>
</c:if>
</p>
</footer>
</body>
</html>