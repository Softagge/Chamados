<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		if(request.getParameter("msg") != null){
			out.println("Cadastrado com sucesso");
		}
	%>
	<form action="Usuario" method="post">
		Login: <br /> <input type="text" name="txtLogin">
		<br />
		Senha: <br /> <input type="password" name="txtSenha">
		<br />
		<input type="submit" value="Cadastrar" name="cadastrar">
	</form>
</body>
</html>