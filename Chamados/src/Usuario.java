

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;


public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String login = request.getParameter("txtLogin");
		String senha = request.getParameter("txtSenha");
		
			try {
				Class.forName("com.mysql.jdbc.Driver");
				
				String sql = "INSERT INTO usuarios (login, senha) VALUES (?, ?)";
				
				try {					
					Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/chamados_rlsys", "root", "root");
					
					PreparedStatement pstm = conn.prepareStatement(sql);
					
					pstm.setString(1, login);
					pstm.setString(2, senha);
					
					pstm.execute();
					
					pstm.close();
					
					conn.close();
					
					response.sendRedirect("http://localhost:8080/Chamados/cadastro_usuario.jsp?msg=sucesso");

				} catch (SQLException e) {
					out.println("Falha ao realizar o cadastro");
				}
							
			} catch (Exception e) {
				out.println("Falha ao carregar o driver de conexão");
			}
	}

}
