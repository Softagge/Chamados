<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" errorPage="error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<td>ID</td>
			<td>Login</td>
		</tr>
		<%
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost/chamados_rlsys", "root", "root");

			String sql = "SELECT * FROM usuarios";

			Statement stm = conn.createStatement();

			ResultSet rs = stm.executeQuery(sql);

			while (rs.next()) {
		%>

		<tr>
			<td><%=rs.getInt("id")%></td>
			<td><%=rs.getString("login")%></td>
		</tr>

		<%
			}
			stm.close();

			conn.close();
		%>
	</table>
</body>
</html>