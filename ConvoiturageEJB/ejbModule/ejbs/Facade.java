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
	
	
	public List<Trajets> getReservation(String login){
		Query res =em.createQuery("from Utilisateur u where u.identifiant=:login");
		res.setParameter("login", login); 
		Utilisateur u = (Utilisateur)res.getSingleResult();
		List<Trajets> trajets = (List<Trajets>)u.getTrajets();
		trajets.size();
		for (Trajets tr : trajets) {
			tr.getEtapes().size();
		}
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
	public ArrayList<Trajets> findTrajets(String date, String villeDepart, String villeArrive) {
		// Requête paramétrée
		Query q = em.createQuery("from Trajets t where t.date=:date"
				+ " and t.villeDepart=:villeDepart"
				+ " and t.nbPlaces > 0"
				+ " order by t.heure");
		q.setParameter("date", date);
		q.setParameter("villeDepart", villeDepart);		
		ArrayList<Trajets> traj = (ArrayList<Trajets>)q.getResultList();
		ArrayList<Trajets> res = new ArrayList<>();
				
		for (Trajets tr : traj) {
			for (int i = 0; i < tr.getEtapes().size(); i++) {
				if(tr.getEtapes().get(i).getEtape().equals(villeArrive)) { // Si le trajet contient l'étape souhaitée
					res.add(tr);	// On l'ajoute aux résultats
				}
			}
		}
		return (ArrayList<Trajets>)res;
	}
	
	public void addTrajet(String login, String villeDepart, List<Etape> etapes,
			String date, String heure, int nbPlaces, String typeVehicule, String modele) {
		// Récupérations des étapes en BDD
		List<Etape> etps = new ArrayList<>();
		for (int i = 0; i < etapes.size(); i++) {
			Etape etp = etapes.get(i);
			System.out.println(etp.getEtape());
			if(!etp.getEtape().equals("default")) {
				em.persist(etp);
				etps.add(etp);
			}
		}
		
		// Requête paramétrée
		Query q = em.createQuery("from Utilisateur u where u.identifiant=:login");
		q.setParameter("login", login);
		Utilisateur u = (Utilisateur)q.getSingleResult();
		Trajets t = new Trajets();
		t.setConducteur(u);
		t.setVilleDepart(villeDepart);
		t.setEtapes(etps);
		t.setDate(date);
		t.setHeure(heure);
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
		t.setNbPlaces(t.getNbPlaces()-1);
		em.persist(t);
	}
		

	
}
