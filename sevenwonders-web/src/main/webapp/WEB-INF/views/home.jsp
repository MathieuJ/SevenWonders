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

	<form:form modelAttribute="partieForm">
		<table>
			<tr>
				<td><label>Nombre de Bots</label></td>
				<td>
				<form:select path="nombreBots" id="nombreBots" >
					<form:option value="3">3</form:option>
					<form:option value="4">4</form:option>
					<form:option value="5">5</form:option>
					<form:option value="6">6</form:option>
					<form:option value="7">7</form:option>
				</form:select>
				</td>
			</tr>
			<tr>
				<td><input type="submit" name="save" value="Lancer la partie" /></td>
			</tr>
		</table>
	</form:form>

</body>
</html>
