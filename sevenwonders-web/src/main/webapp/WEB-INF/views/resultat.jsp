<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Home</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery-1.6.4.min.js"></script>
<link href="css/sevenwonders.css" rel="stylesheet" type="text/css"
	media="screen" />
</head>
<body>

	<h1>Partie à ${partie.getNbJoueurs()} joueurs : Résultats :</h1>
	<div class="container">
		<div class="row">
			<table border="1">
				<thead>
					<TR>
						<TH>Numéro du joueur</TH>
						<TH>Victoires militaires</TH>
						<TH>Trésor</TH>
						<TH>Merveille</TH>
						<TH>Civil</TH>
						<TH>Science</TH>
						<TH>Commerce</TH>
						<TH>Guildes</TH>
						<TH>Total</TH>
					</TR>
				</thead>
			<c:forEach items="${partie.listeJoueurs}" var="joueur">
				<TR <c:if test="${!joueur.isBot()}">
					style="background-color:yellow"
					</c:if>>
					<TD>${joueur.getPlace()}</TD>
					<TD>${resultat.get(joueur).get(0)}</TD>
					<TD>${resultat.get(joueur).get(1)}</TD>
					<TD>${resultat.get(joueur).get(2)}</TD>
					<TD>${resultat.get(joueur).get(3)}</TD>
					<TD>${resultat.get(joueur).get(4)}</TD>
					<TD>${resultat.get(joueur).get(5)}</TD>
					<TD>${resultat.get(joueur).get(6)}</TD>
					<TD>${resultat.get(joueur).get(7)}</TD>
				</div>
				</TR>
			</c:forEach>
			</table>
		</div>


	</div>
</body>
</html>
