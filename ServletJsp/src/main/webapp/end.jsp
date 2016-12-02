<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.io.*,java.util.*,model.*" %>


<!--  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>-->


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="cargarListado" method="post">
	<input type="submit" value="ver listado">
</form>
<% 
 List<Form> forms = (List<Form>)request.getAttribute("listAllForms");
 pageContext.setAttribute("forms", forms);
%>
<table border="1">
	<thead>
		<tr>
			<td>Country</td>
			<td>Language</td>
		</tr>
	</thead>
	<tbody>
	<%
		if(null != forms && !forms.isEmpty()){
			for (Form form2 : forms) {
				out.println("<tr>");
				out.println("<td>");
				out.println(form2.getCountry());
				out.println("</td>");
				out.println("<td>");
				out.println(form2.getLanguage());
				out.println("</td>");
				out.println("<td>");
				out.println("<a href='warning.jsp?id="+form2.getLanguage()+"'>x</a>");
				out.println("</td>");
				out.println("</tr>");
			}	
		}
	%>
	</tbody>
</table>
</body>
</html>