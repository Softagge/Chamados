package NovoChamadoServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;


public class ListarChamados extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("login") == null){
			response.sendRedirect("http://localhost:8080/Chamados/Login");
		}
		out.println(session.getAttribute("info"));
		out.println("<a href='http://localhost:8080/Chamados/Login?msg=logoff'>Sair</a>");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
						
			try {					
				Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/chamados_rlsys", "root", "root");
				
				if(request.getParameter("id") != null){
					int ID = Integer.parseInt(request.getParameter("id"));
					String sql = "DELETE FROM chamados WHERE id = ?";
					PreparedStatement pstm = conn.prepareStatement(sql);
					pstm.setInt(1, ID);
					pstm.execute();
				}
				
				String sql = "SELECT * FROM chamados";
				
				Statement stm = conn.createStatement();
								
				ResultSet rs = stm.executeQuery(sql);
				
				out.println("<table width='100%'>");
				
				out.println("<tr bgcolor='#c0c0c0'>");
				out.println("<td>ID</td>");
				out.println("<td>Título</td>");
				out.println("<td>Editar</td>");
				out.println("<td>Excluir</td>");
				out.println("</tr>");
							
				while(rs.next()){
					out.println("<tr>");
					out.println("<td>" + rs.getInt("id") + "</td>");
					out.println("<td>" + rs.getString("titulo") + "</td>");
					out.println("<td><a href='http://localhost:8080/Chamados/EditarChamado?id="+ rs.getInt("id") +"'>[EDITAR]</a></td>");
					out.println("<td><a href='http://localhost:8080/Chamados/ListarChamados?id="+ rs.getInt("id") +"'>[APAGAR]</a></td>");
					out.println("</tr>");
				}			
				
				out.println("</table>");
				
				stm.close();
				
				conn.close();
			} catch (SQLException e) {
				out.println("Falha ao realizar o cadastro");
			}
						
		} catch (Exception e) {
			out.println("Falha ao carregar o driver de conexão");
		}
	}

}
