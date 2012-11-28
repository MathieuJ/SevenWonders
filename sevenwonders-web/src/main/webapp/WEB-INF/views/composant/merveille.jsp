<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<div class="merveille" style="background-image:url('img/${merveille.image}');">

	<h3><img src="img/${merveille.ressource.name().toLowerCase()}.jpg" height="32px" width="32px"/> ${merveille.nomFr}</h3>
	
	
	<c:forEach items="${merveille.coutsAge}" var="cout" varStatus="status">
		<c:set var="cout" value="${cout}" scope="request" />
		<c:if test="${joueur.etageMerveille <= status.index}">
		<div class="etage" style="background-color:rgba(120, 120, 0, 0.3);">
			<div class="cout">
				<jsp:include page="cout.jsp" />
				<BR/>&nbsp;
				<span style="background:white;margin:3px;"> Age ${(status.index + 1)}</span>
			</div>
		</div>
		</c:if>
		<c:if test="${joueur.etageMerveille > status.index}">
		<div class="etage" style="background-color:rgba(120, 120, 120, 0.8);">
			<div class="cout">
				<jsp:include page="cout.jsp" />
				<BR/>&nbsp;
				<span style="background:white;margin:3px;"> Construit</span>
			</div>
		</div>
		</c:if>
		
	</c:forEach>&nbsp;
	
	<div style="background:red">
		<div class="texte">
			${merveille.texte}
		</div>
	</div>
</div>