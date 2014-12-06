package NovoChamadoServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.security.util.Length;

public class NovoChamadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Sistema de Chamados</title>");
		out.println("</head>");
		out.println("<h1>Preencha as informa��es do chamado</h1>");
		out.println("<hr/>");
		out.println("<form method='post'>");
		out.println("T�tulo: <br> <input type='text' name='txtTitulo'>");
		out.println("<br/>");
		out.println("Conte�do: <br> <textarea name='txtConteudo' rows='10' cols='40'></textarea>");
		out.println("<br/>");
		out.println("<input type='submit' value='Abrir Chamado'><br/>");
		out.println("<form>");
		out.println("<br/>");
		out.println("<a href='http://localhost:8080/Chamados/ListarChamados'>Listar Chamados</a>");
		out.println("<br/>");
		out.println("<a href='/logoff'>Sair</a>");
		out.println("<body>");
		out.println("</body>");
		out.println("</html>");

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String titulo = request.getParameter("txtTitulo");
		String conteudo = request.getParameter("txtConteudo");
		
		if(titulo.trim().length() <= 0){
			out.println("� obrigat�rio preencher o t�tulo");
		} else if(conteudo.trim().length() <= 0){
			out.println("� obrigat�rio preencher o conte�do");
		} else {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (Exception e) {
				out.println("Falha ao carregar o driver de conex�o");
			}
		}

	}

}
