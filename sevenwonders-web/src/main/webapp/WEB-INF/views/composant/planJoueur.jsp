<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>


</h3>
<c:forEach items="${joueur.main}" var="carte">
	<c:set var="carte" value="${carte}" scope="request" />
	<jsp:include page="carte.jsp" />
</c:forEach>