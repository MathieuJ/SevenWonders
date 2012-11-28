<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<div class="carte ${carte.type}">
	${carte.nomFr}
	<HR/>
	${carte.texte}  
	<HR/>
	<c:set var="cout" value="${carte.cout}" scope="request" />
	<div class="cout">
		Coût : <jsp:include page="cout.jsp" />
	</div>
</div>
