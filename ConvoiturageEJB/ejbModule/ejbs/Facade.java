package ejbs;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import entities.*;

@Stateless
@LocalBean
public class Facade {
	
	@PersistenceContext(unitName="monUnite")
	EntityManager em;
	
	/*Vérifie si un utilisateur est présent dans la base des utilisateurs*/
	public boolean validUser(String login, String passwd) {
		// Requête paramétrée
		Query q = em.createQuery("from Utilisateur u where u.identifiant=:login"
				+ " and u.motDePasse=:passwd");
		q.setParameter("login", login);
		q.setParameter("passwd", passwd);
		
		return(q.getResultList().size()==1);
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> getUserRole(String login, String passwd) {
		Query q = em.createQuery("select role from Utilisateur u where u.identifiant=:login"
				+ " and u.motDePasse=:passwd");
		q.setParameter("login", login);
		q.setParameter("passwd", passwd);
		return (List<Integer>)q.getResultList();
	}
	
	/*Renvoi la liste des types de véhicules disponibles*/
	@SuppressWarnings("unchecked")
	public ArrayList<String> getTypesVehicules(){
		Query q = em.createQuery("from Vehicule v");
		return (ArrayList<String>)q.getResultList();
	}
	
	/*Rnvoi la liste des villes déservies*/
	@SuppressWarnings("unchecked")
	public ArrayList<String> getVilles(){
		Query q = em.createQuery("from Ville v");
		return (ArrayList<String>)q.getResultList();
	}
		
	@SuppressWarnings("unchecked")
	public List<Trajets> findTrajets(String date, String heure, String villeDepart, int nbPlaces) {
		// Requête paramétrée
		Query q = em.createQuery("from Trajets t where t.date=:date"
				+ " and t.heure=:heure"
				+ " and t.villeDepart=:villeDepart"
				+ " and t.nbPlaces>=:nbPlaces");
		q.setParameter("date", date);
		q.setParameter("heure", heure);
		q.setParameter("villeDepart", villeDepart);
		q.setParameter("nbPlaces", nbPlaces);
		
		return (List<Trajets>)q.getResultList();
	}
	
	public void addTrajet(Utilisateur conducteur, String villeDepart, String villeArrivee, ArrayList<String> etapes,
			String date, String heure, ArrayList<Integer> tarifs, int nbPlaces, String typeVehicule) {
		// Requête paramétrée
		Query q = em.createQuery("insert into Trajets t (conducteur, villeDepart, villeArrivee,"
				+ " etapes, date, heure, tarifs, nbPlaces, typeVehicule)"
				+ "values (:conducteur, :villeDepart, :villeArrivee,"
				+ " :etapes, :date, :heure, :tarifs, :nbPlaces, :typeVehicule)");
		q.setParameter("conducteur", conducteur);
		q.setParameter("villeDepart", villeDepart);
		q.setParameter("villeArrivee", villeArrivee);
		q.setParameter("etapes", etapes);
		q.setParameter("date", date);
		q.setParameter("heure", heure);
		q.setParameter("tarifs", tarifs);
		q.setParameter("nbPlaces", nbPlaces);
		q.setParameter("typeVehicule", typeVehicule);

		return;
	}
	
	public void reserverTrajet (Trajets trajet, Utilisateur voyageur) {
		Query q = em.createQuery("update Trajet t"
				+ " set t.passager =:voyageur,"
				+ " t.nbPlace = 0"
				+ " where t.id=:idTrajet");
		q.setParameter("voyageur", voyageur);
		q.setParameter("idTrajet", trajet.getId());
	}
	
}
