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
	
	public void initdbTrajets() {
		ArrayList<Integer> tarifs = new ArrayList<Integer>();
		tarifs.add(5);
		addTrajet("eric", "Bourges", "Paris", null, "2017-12-06", "04:04", tarifs, 1, "Urbaine", "twingo");
		addTrajet("remi", "Bourges", "Paris", null, "2017-12-06", "05:04", tarifs, 2, "Urbaine", "twingo");
		addTrajet("maxime", "Bourges", "Paris", null, "2017-12-06", "06:04", tarifs, 3, "Urbaine", "twingo");
		addTrajet("guillaume", "Bourges", "Paris", null, "2017-12-06", "07:04", tarifs, 4, "Urbaine", "twingo");
		addTrajet("eric", "Bourges", "Paris", null, "2017-12-06", "10:04", tarifs, 5, "Urbaine", "twingo");
		addTrajet("eric", "Bourges", "Paris", null, "2017-12-06", "09:04", tarifs, 6, "Urbaine", "twingo");
		
	}
	
	public List<Trajets> getReservation(String login){
		Query res =em.createQuery("from Utilisateur u where u.identifiant=:login");
		res.setParameter("login", login);
		
		Utilisateur u = (Utilisateur)res.getSingleResult();
		List<Trajets> trajets = (List<Trajets>)u.getTrajets();
		trajets.size();
		return trajets;
	}
	
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
	
	public void addVilles(String ville) {
		Ville v = new Ville();
		v.setVille(ville);
		em.persist(v);
	}
	
	public void addGabarit(String type) {
		Vehicule v = new Vehicule();
		v.setType(type);
		em.persist(v);
	}
	
		
	@SuppressWarnings("unchecked")
	public ArrayList<String> findTrajets(String date, String villeDepart, String villeArrive) {
		// Requête paramétrée
		Query q = em.createQuery("from Trajets t where t.date=:date"
				+ " and t.villeDepart=:villeDepart"
				+ " and t.villeArrive=:villeArrive"
				+ " order by t.heure");
		q.setParameter("date", date);
		q.setParameter("villeDepart", villeDepart);
		q.setParameter("villeArrive", villeArrive);
		return (ArrayList<String>)q.getResultList();
	}
	
	public void addTrajet(String login, String villeDepart, String villeArrive, ArrayList<String> etapes,
			String date, String heure, ArrayList<Integer> tarifs, int nbPlaces, String typeVehicule, String modele) {
		// Requête paramétrée
		Query q = em.createQuery("from Utilisateur u where u.identifiant=:login");
		q.setParameter("login", login);
		Utilisateur u = (Utilisateur)q.getSingleResult();
		Trajets t = new Trajets();
		t.setConducteur(u);
		t.setVilleDepart(villeDepart);
		t.setVilleArrive(villeArrive);
		t.setEtapes(etapes);
		t.setDate(date);
		t.setHeure(heure);
		t.setTarifs(tarifs);
		t.setNbPlaces(nbPlaces);
		t.setTypeVehicule(typeVehicule);
		t.setModele(modele);
		em.persist(t);

	}
	
	public void reserverTrajet (int idTrajet, String loginUser) {
		Query trajetfind = em.createQuery("from Trajets t where t.id=:id");
		trajetfind.setParameter("id", idTrajet);
		
		Query userfind = em.createQuery("from Utilisateur u where u.identifiant=:login");
		userfind.setParameter("login", loginUser);
		
		Utilisateur u = (Utilisateur)userfind.getSingleResult();
		Trajets t = (Trajets)trajetfind.getSingleResult();
		
		t.setPassagers(u);
		t.setNbPlaces(0);
		em.persist(t);
	}
		

	
}
