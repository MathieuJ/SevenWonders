<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<h3>Plan joueur : </h3>
<c:forEach items="${joueur.listeCartes}" var="carte">
	<jsp:include page="carte.jsp" />
</c:forEach>