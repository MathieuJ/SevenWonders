<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Hello world!</h1>

	<P>The time on the server is ${serverTime}.</P>

	<a href="nouvellePartie">Nouvelle Partie</a>

	<form:form commandName="nouvellePartie" method="post"
		action="nouvellePartie">
		<form:hidden path="id" id="id" />
		<table>
			<tr>
				<td><label>Nombre de Bots</label></td>
				<form:input path="nbBots" id="nbBots" />
			</tr>
			<tr>
				<td><input type="submit" name="save" value="save" /></td>
			</tr>
		</table>
	</form:form>

</body>
</html>
