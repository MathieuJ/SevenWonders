<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>

<c:if test="if ${cout.listeRessources.size() == 0 && cout.getPrix() == 0}">
Gratuit
</c:if>


<c:forEach var="entry" begin="1" end="${cout.getPrix()}">
	<img src="img/piece.jpg" height="32px" width="32px"/>
</c:forEach>

<c:forEach items="${cout.listeRessources}" var="ressource">
	<img src="img/${ressource.name().toLowerCase()}.jpg" height="24px" width="24px"/>
</c:forEach>