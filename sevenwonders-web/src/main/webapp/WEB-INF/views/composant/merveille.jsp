<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<div class="merveille" style="background-image:url('img/${merveille.image}');">

	<h3>${merveille.nomFr}</h3>
	
	<c:forEach items="${merveille.coutsAge}" var="cout" varStatus="status">
		<c:set var="cout" value="${cout}" scope="request" />
		<div class="etage" style="background-color:transparent;">
			<div class="cout">
				<jsp:include page="cout.jsp" />
				<BR/>
				Age ${(status.index + 1)}
			</div>
		</div>
	</c:forEach>
	
	<div class="texte">
		${merveille.texte}
	</div>
</div>