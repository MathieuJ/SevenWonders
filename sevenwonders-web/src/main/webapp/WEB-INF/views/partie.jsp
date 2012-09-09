<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
	<head>
		<title>Home</title>
		<link href="css/sevenwonders.css" rel="stylesheet" type="text/css" media="screen" />
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<script src="js/bootstrap.min.js"></script>
	</head>
<body>
	<h1>Partie à ${partie.getNbJoueurs()} joueurs : age ${partie.age},
		tour ${partie.tour}</h1>
<div class="container">
	<div class="row">
	<c:forEach items="${partie.listeJoueurs}" var="joueur">
		<div class="span6">
			<c:set var="merveille" value="${joueur.merveille}" scope="request" />
			<c:set var="joueur" value="${joueur}" scope="request" />
			<jsp:include page="composant/merveille.jsp" />
			<jsp:include page="composant/planJoueur.jsp" />
		</div>
	</c:forEach>
	</div>
</div>
</body>
</html>
