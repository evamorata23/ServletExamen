
    
    <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.io.*,java.util.*,model.*,service.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Idiomas</title>
</head>
	<body>
	<% 
		Service service = new Service();
	 	List<Language> languages = service.listAllLang();
	 	pageContext.setAttribute("languages", languages);
	%>
		<form action="welcome" method="post" name="form1">
			<h1 align="center">Servlet Idiomas</h1>
			<table align="center" border="1" bgcolor="cyan">
				<tr>
					<td>Pais</td>
					<td><input type="text" name="country" size="8"></td>
				</tr>
				<tr>
					<td>Seleccionar Idioma: </td>
					<td>
						<select name="language1">
		  						<option value=null> </option>
		 						<%if(null != languages && !languages.isEmpty()){
										for (Language languages2 : languages) {
											String data = "<option value=\""+languages2.getLanguage()+"\">"+languages2.getLanguage()+"</option>";
											out.println(data);
										}
	
									}
								%>
						</select>
					</td>
				</tr>
				<tr>
					<td>Introducir Idioma:</td>
					<td>
						<input type="text" name="language2" size="25">
					</td>
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