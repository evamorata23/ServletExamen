<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head></head>
	<body>
	<form action="borrado" method="post">
		<p><h2> ¿Desea eliminarlo realmente?</h2></p>
		<input type="hidden" value="${param.id}" name="languageDelete">
		<input type="submit" Value="Yes">
		<input type="button" Value="No" onclick="window.location.href='/ServletJsp/end.jsp'">
		</form>
	</body>
</html>