package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		
		/*Gestion des listes choix pour la recherche d'un trajet*/
		//////////////////////////////////////
		request.setAttribute("villes", facade.getVilles());
		request.setAttribute("typesVehicules", facade.getTypesVehicules());
		request.setAttribute("resultatRecherche", new ArrayList<String>());
		/////////////////////////////////////		
		
		/*Gestion de l'authentification*/
		//////////////////////////////////////
		String currentLogin = (String) request.getSession().getAttribute("login");
		String todo = request.getParameter("todo");
		
		if(currentLogin == null) {
			if(todo !=null && todo.equals("connect")) {
				String login = request.getParameter("login");
				String passwd = request.getParameter("passwd");
				if(facade.validUser(login, passwd)) {
					request.getSession().setAttribute("login", login);
					List<Integer> userRole = facade.getUserRole(login, passwd);
					if(userRole.size() == 1) {
						request.getSession().setAttribute("role", (int)userRole.get(0));
					}else {
						request.getSession().setAttribute("role", null);
					}
					
					//request.getRequestDispatcher("/WEB-INF/Conducteur.jsp").forward(request, response);
					request.getRequestDispatcher("/WEB-INF/Accueil.jsp").forward(request, response);
				}
				
			}
			else {
				request.getRequestDispatcher("/WEB-INF/Accueil.jsp").forward(request, response);
			}
			return;
		}
		
		///////////////////////////////////////////
		
		if(todo == null) {
			request.getRequestDispatcher("/WEB-INF/Accueil.jsp").forward(request, response);
		}else {
			switch(todo) {
			case "admin":
				request.getRequestDispatcher("/WEB-INF/Administrateur.jsp").forward(request, response);
				break;
			case "conducteur":
				request.getRequestDispatcher("/WEB-INF/Conducteur.jsp").forward(request, response);
				break;
			case "user" :
				request.getRequestDispatcher("/WEB-INF/Utilisateur.jsp").forward(request, response);
				break;
			case "retourAcceuil":
				request.getRequestDispatcher("/WEB-INF/Accueil.jsp").forward(request, response);
				break;
			case "disconnect":
				request.getSession().setAttribute("login", null);
				request.getRequestDispatcher("/WEB-INF/Accueil.jsp").forward(request, response);
				break;
			case "ajoutGabaritButton":
				String type = request.getParameter("ajoutGabarit");
				facade.addGabarit(type);
				request.getRequestDispatcher("/WEB-INF/Administrateur.jsp").forward(request, response);
				break;
			case "ajoutVilleButton":
				String ville = request.getParameter("ajoutVille");
				facade.addVilles(ville);
				request.getRequestDispatcher("/WEB-INF/Administrateur.jsp").forward(request, response);
				break;
			case "addTrajet":
				addTrajet(request, response);
				break;
			case "rechercheTrajet":
				findTrajet(request, response);
				break;
			default:
				request.getRequestDispatcher("/WEB-INF/Accueil.jsp").forward(request, response);
				break;
			}
			
		}
		
		
		
	}
	
	private void findTrajet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String date = request.getParameter("date");
		String villeDepart = request.getParameter("villeDepart");
		String villeArrive = request.getParameter("villeArrive");		
		request.setAttribute("resultatRecherche", facade.findTrajets(date, villeDepart, villeArrive));
		request.getRequestDispatcher("/WEB-INF/Accueil.jsp").forward(request, response);
	}
	
	
	private void addTrajet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currentLogin = (String) request.getSession().getAttribute("login");
		String modele = request.getParameter("modeleVehicule");
		String type = request.getParameter("typesVehicules");
		String date = request.getParameter("date");
		String heure = request.getParameter("time");
		String vDepart = request.getParameter("villeDepart");
		String vArrive = request.getParameter("villeArrive");
		ArrayList<Integer> tarif = new ArrayList<Integer>();
		tarif.add(Integer.parseInt(request.getParameter("tarif")));
		int nbplaces = Integer.parseInt(request.getParameter("placesLibres"));
		
		if(currentLogin != null) {
			facade.addTrajet(currentLogin, vDepart, vArrive, null, date, heure, tarif, nbplaces, type, modele);
			request.getRequestDispatcher("/WEB-INF/Accueil.jsp").forward(request, response);
		}
						
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
