<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Home</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/bootstrap.min.js"></script>
<link href="css/sevenwonders.css" rel="stylesheet" type="text/css"
	media="screen" />
</head>
<body>
	<h1>Partie à ${partie.getNbJoueurs()} joueurs : age ${partie.age},
		tour ${partie.tour}</h1>
	<div class="container">
		<div class="row">
			<c:forEach items="${partie.listeJoueurs}" var="joueur">
				<div class="span6">
					<c:if test="${!joueur.isBot()}">
						<form:form commandName="actionForm" method="post"
							action="partie.action">
							<form:errors />
							<form:errors path="numCarte" />
							<label>Nombre de Bots</label>
							<form:select path="typeAction" id="typeAction">
								<form:option value="Joue">Joue une carte</form:option>
								<form:option value="Defausse">Défausse</form:option>
								<form:option value="Merveille">Merveille</form:option>
								<form:option value="Special">Pouvoir spécial</form:option>
							</form:select>
	
							<div class="">
								<c:forEach items="${joueur.main}" var="carte" varStatus="status">
									<c:set var="carte" value="${carte}" scope="request" />
										<div class="cartaselectionner" onClick="alert(hidden.val());$('div.cartaselectionner').removeClass('selected');hidden.val(${status.index});">
											<jsp:include page="composant/carte.jsp" />
										</div>
								</c:forEach>
								<form:hidden path="numCarte" value="0"/>
							</div>
	
							<input type="submit" name="save" value="Effectuer l'action" />
						</form:form>
					</c:if>
					
					<c:set var="merveille" value="${joueur.merveille}" scope="request" />
					<c:set var="joueur" value="${joueur}" scope="request" />
					<jsp:include page="composant/merveille.jsp" />
					<div>Plan joueur : ${joueur.nombrePieces} Pièces d'or</div>
					<c:forEach items="${joueur.listeMedailles}" var="medaille">
						${medaille}
					</c:forEach>
					<div class="row planjoueur">
						<c:forEach items="${joueur.listeCartes}" var="carte">
							<c:set var="carte" value="${carte}" scope="request" />
							<jsp:include page="composant/carte.jsp" />
						</c:forEach>
					</div>
					
				</div>

			</c:forEach>
		</div>


	</div>
</body>
</html>
