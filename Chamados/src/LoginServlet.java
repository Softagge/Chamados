

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//Apagando cookie
		/*Cookie[] ck2 = request.getCookies();
		
		if(ck2 != null){
			for(Cookie cookie : ck2){
				if(cookie.getName().equals("login_name")){
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}*/
		
		PrintWriter out = response.getWriter();		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Sistema de Chamados</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Preencha as login e senha</h1>");
		out.println("<hr/>");
		
		if(request.getParameter("msg") != null){
			if(request.getParameter("msg").equals("logoff")) {				
				HttpSession session = request.getSession();
				session.removeAttribute("login");
				out.println("<span style='color: red'>Deslogado com sucesso</span>");
			}
		}
		
		if(request.getParameter("msg") != null){
			if(request.getParameter("msg").equals("error")){
				out.println("<span style='color: red'>Login ou senha inválidos</span>");
			}
		}
		
		//Lendo cookie
		String login_name = "";
		Cookie[] ck = request.getCookies();
		
		if(ck != null){
			for(Cookie cookie : ck){
				if(cookie.getName().equals("login_name")){
					login_name = cookie.getValue();
				}
			}
		}
		
		out.println("<form method='post'>");
		out.println("Título: <br> <input type='text' name='txtLogin' value='"+ login_name +"'>");
		out.println("<br/>");
		out.println("Título: <br> <input type='password' name='txtSenha'>");
		out.println("<br/>");
		out.println("<input type='submit' value='Logar'><br/>");
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
		String login = request.getParameter("txtLogin");
		String senha = request.getParameter("txtSenha");
		
		//Criando cookie
		Cookie ck = new Cookie("login_name", login);
		ck.setMaxAge(60*60*24);
		response.addCookie(ck);

			try {
				Class.forName("com.mysql.jdbc.Driver");
				
				String sql = "SELECT * FROM usuarios WHERE login = ? AND senha = ?";
				
				try {					
					Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/chamados_rlsys", "root", "root");
					
					PreparedStatement pstm = conn.prepareStatement(sql);
					
					pstm.setString(1, login);
					pstm.setString(2, senha);
					
					ResultSet rs = pstm.executeQuery();
					
					if(rs.next()){
						pstm.close();					
						conn.close();
						
						HttpSession session = request.getSession();
						session.setAttribute("login", login);
						session.setAttribute("info", request.getRemoteAddr());
												
						response.sendRedirect("http://localhost:8080/Chamados/ListarChamados");
					} else {
						pstm.close();					
						conn.close();
						
						response.sendRedirect("http://localhost:8080/Chamados/Login?msg=error");
					}
					
					
					
					out.println("Cadastro realizado com sucesso");
				} catch (SQLException e) {
					out.println("Falha ao realizar o cadastro");
				}
							
			} catch (Exception e) {
				out.println("Falha ao carregar o driver de conexão");
			}

	}
}
