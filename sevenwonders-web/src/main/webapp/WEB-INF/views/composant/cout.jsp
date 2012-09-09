<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>


<c:forEach items="${cout.listeRessources}" var="ressource">
	<img src="img/${ressource.name().toLowerCase()}.jpg" height="32px" width="32px"/>
</c:forEach>