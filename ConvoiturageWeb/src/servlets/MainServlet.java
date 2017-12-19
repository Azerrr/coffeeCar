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
import entities.Etape;

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
		String task = request.getParameter("task");
		String reservation = request.getParameter("reservation");
		
		if(task != null) {
			switch(task) {
				case "rechercheTrajet":
					findTrajet(request, response);
					break;	
			}
		}
		
				
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

					request.getRequestDispatcher("/WEB-INF/Accueil.jsp").forward(request, response);
				}else {
					request.getRequestDispatcher("/WEB-INF/Accueil.jsp").forward(request, response);
				}
				
			}else {			
				request.getRequestDispatcher("/WEB-INF/Accueil.jsp").forward(request, response);	
				
			}	
			return;
		}
		
		
		
		///////////////////////////////////////////
		
		if(reservation != null && currentLogin != null) {
			facade.reserverTrajet(Integer.parseInt(reservation), currentLogin);
		}
		
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
				request.setAttribute("trajetReserve", facade.getReservation(currentLogin));
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
	}
	
	
	private void addTrajet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currentLogin = (String) request.getSession().getAttribute("login");
		String modele = request.getParameter("modeleVehicule");
		String type = request.getParameter("typesVehicules");
		String date = request.getParameter("date");
		String heure = request.getParameter("time");
		String vDepart = request.getParameter("villeDepart");
		String etape1 = request.getParameter("etape1");
		String etape2 = request.getParameter("etape2");
		String etape3 = request.getParameter("etape3");
		String tarif1 = request.getParameter("tarif1");
		String tarif2 = request.getParameter("tarif2");
		String tarif3 = request.getParameter("tarif3");
		Etape etp1 = new Etape();
		etp1.setEtape(etape1);
		etp1.setTarif(Integer.parseInt(tarif1));
		Etape etp2 = new Etape();
		etp2.setEtape(etape2);
		etp2.setTarif(Integer.parseInt(tarif2));
		Etape etp3 = new Etape();
		etp3.setEtape(etape3);
		etp3.setTarif(Integer.parseInt(tarif3));
		List<Etape> etapes = new ArrayList<>();
		etapes.add(etp1);
		etapes.add(etp2);
		etapes.add(etp3);
		
		int nbplaces = Integer.parseInt(request.getParameter("placesLibres"));
		
		if(currentLogin != null) {
			facade.addTrajet(currentLogin, vDepart, etapes, date, heure, nbplaces, type, modele);
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
