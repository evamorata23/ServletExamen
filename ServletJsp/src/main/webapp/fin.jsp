<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script language="JavaScript">
			
		</script>
	</head>
	<body onload="calcular_edad()">
		<table align="center">
				<tr>
					<td>Nombre Pais:</td>
					<td><b><%= request.getParameter("nombre") %></b></td>
				</tr>
				<tr>
					<td>Seleccionar Idioma:</td>
					
				</tr>
				<tr>
					<td>Introducir Idioma:</td>
					<td><b><%= request.getParameter("idioma") %></b></td>
				</tr>
				<tr>
					<td><input type="button" value="volver" onclick="history.back()"></td>
					<td>
						<form method="post" action="hello2">
							<input type="submit" value="Eliminar Idioma">
							<input type="hidden" value="<%=request.getParameter("idioma")%>" name="idioma">
						</form>
					</td>
				</tr>
			</table>
	</body>
</html>