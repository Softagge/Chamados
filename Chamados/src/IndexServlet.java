import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse rep) {
		try {
			
			PrintWriter out = rep.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Sistema de Chamados</title>");
			out.println("</head>");
			out.println("<h1>Sistema de Chamados</h1>");
			out.println("<hr/>");
			out.println("<a href='/NovoChamado'>Novo Chamado</a>");
			out.println("<br/>");
			out.println("<a href='/ListarChamados'>Listar Chamados</a>");
			out.println("<br/>");
			out.println("<a href='/logoff'>Sair</a>");
			out.println("<body>");
			out.println("</body>");
			out.println("</html>");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse rep) {

	}
}
