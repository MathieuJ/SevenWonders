<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<div class="merveille" style="background-image:url('img/${merveille.image}');">

	<h3>${merveille.nomFr}</h3>
	
	
	<c:forEach items="${merveille.coutsAge}" var="cout" varStatus="status">
		<c:set var="cout" value="${cout}" scope="request" />
		<div class="etage" style="background-color:rgba(120, 120, 0, 0.3);">
			<div class="cout">
				<jsp:include page="cout.jsp" />
				<BR/>&nbsp;
				<span style="background:white;margin:3px;"> Age ${(status.index + 1)}</span>
			</div>
		</div>
	</c:forEach>&nbsp;
	
	<div style="background:red">
		<div class="texte">
			${merveille.texte}
		</div>
	</div>
</div>