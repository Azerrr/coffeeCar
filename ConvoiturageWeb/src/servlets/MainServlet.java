package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ejb.EJB;
import ejbs.Facade;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	
	@EJB
	private Facade facade;
	
    /**
     * @see HttpServlet#HttpServlet()
     *
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    */

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String currentLogin = (String) request.getSession().getAttribute("login");
		String todo = request.getParameter("todo");
		
		if(todo == null) {
			request.getRequestDispatcher("/WEB-INF/Accueil.jsp").forward(request, response);
		}
		
		if(currentLogin == null && todo.equals("connect")) {
			String login = request.getParameter("login");
			String passwd = request.getParameter("passwd");
			if(facade.validUser(login, passwd)) {
				request.getSession().setAttribute("login", login);
				request.getRequestDispatcher("/WEB-INF/Conducteur.jsp").forward(request, response);
			}
		}
		
		
		request.getRequestDispatcher("/WEB-INF/Accueil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
