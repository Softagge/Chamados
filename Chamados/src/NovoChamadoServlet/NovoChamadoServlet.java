package NovoChamadoServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

public class NovoChamadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Sistema de Chamados</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Preencha as informações do chamado</h1>");
		out.println("<hr/>");
		out.println("<form method='post'>");
		out.println("Título: <br> <input type='text' name='txtTitulo'>");
		out.println("<br/>");
		out.println("Conteúdo: <br> <textarea name='txtConteudo' rows='10' cols='40'></textarea>");
		out.println("<br/>");
		out.println("<input type='submit' value='Abrir Chamado'><br/>");
		out.println("<form>");
		out.println("<br/>");
		out.println("<a href='http://localhost:8080/Chamados/ListarChamados'>Listar Chamados</a>");
		out.println("<br/>");
		out.println("<a href='/logoff'>Sair</a>");		
		out.println("</body>");
		out.println("</html>");

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String titulo = request.getParameter("txtTitulo");
		String conteudo = request.getParameter("txtConteudo");
		
		if(titulo.trim().length() <= 0){
			out.println("É obrigatório preencher o título");
		} else if(conteudo.trim().length() <= 0){
			out.println("É obrigatório preencher o conteúdo");
		} else {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				
				String sql = "INSERT INTO chamados (titulo, conteudo, data) VALUES (?, ?, ?)";
				
				try {					
					Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/chamados_rlsys", "root", "root");
					
					PreparedStatement pstm = conn.prepareStatement(sql);
					
					Date dateSql = new Date( new java.util.Date().getTime());
					
					pstm.setString(1, titulo);
					pstm.setString(2, conteudo);
					pstm.setDate(3, dateSql);
					
					pstm.execute();
					
					pstm.close();
					
					conn.close();
					
					response.sendRedirect("http://localhost:8080/Chamados/ListarChamados");
					
					out.println("Cadastro realizado com sucesso");
				} catch (SQLException e) {
					out.println("Falha ao realizar o cadastro");
				}
							
			} catch (Exception e) {
				out.println("Falha ao carregar o driver de conexão");
			}
		}

	}

}
