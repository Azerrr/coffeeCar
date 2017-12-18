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
	
	public void reserverTrajet (Trajets trajet, Utilisateur voyageur) {
		Query q = em.createQuery("update Trajet t"
				+ " set t.passager =:voyageur,"
				+ " t.nbPlace = 0"
				+ " where t.id=:idTrajet");
		q.setParameter("voyageur", voyageur);
		q.setParameter("idTrajet", trajet.getId());
	}
	
}
