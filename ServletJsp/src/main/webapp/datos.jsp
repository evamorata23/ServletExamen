<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
	<body>
		<form action="hello" method="post" name="form1">
			<h1 align="center">Servlet</h1>
			<table align="center" border="1" bgcolor="cyan">
				<tr>
					<td>Pais:</td>
					<td><input type="text" name="country" size="20"></td>
				</tr>
				<tr>
					<td>Seleccionar Idioma:</td>
					<td>
						<select name="language1">
		  						<option value=""></option>
						</select>
					</td>
				</tr>
				<tr>
					<td>Introducir Idioma:</td>
					<td><input type="text" name="language2" size="20"></td>
				</tr>
				<tr>
					<td align="center">
						<input type="submit">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>