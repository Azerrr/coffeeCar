package entities;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Trajets {

	@Id @GeneratedValue
	private int id;
	@ManyToOne
	private Utilisateur conducteur;
	@OneToOne
	private Utilisateur passagers;
	private String villeDepart;
	private String villeArrive;
	private ArrayList<String> etapes;
	private String date;
	private String heure;
	private ArrayList<Integer> tarifs;
	private int nbPlaces;
	private String typeVehicule;
	private String modele;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Utilisateur getConducteur() {
		return conducteur;
	}
	public void setConducteur(Utilisateur conducteur) {
		this.conducteur = conducteur;
	}
	public String getModele() {
		return modele;
	}
	public void setModele(String modele) {
		this.modele = modele;
	}
	public Utilisateur getPassagers() {
		return passagers;
	}
	public void setPassagers(Utilisateur passagers) {
		this.passagers = passagers;
	}
	public String getVilleDepart() {
		return villeDepart;
	}
	public void setVilleDepart(String villeDepart) {
		this.villeDepart = villeDepart;
	}
	public String getVilleArrive() {
		return villeArrive;
	}
	public void setVilleArrive(String villeArrive) {
		this.villeArrive = villeArrive;
	}
	public ArrayList<String> getEtapes() {
		return etapes;
	}
	public void setEtapes(ArrayList<String> etapes) {
		this.etapes = etapes;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getHeure() {
		return heure;
	}
	public void setHeure(String heure) {
		this.heure = heure;
	}
	public ArrayList<Integer> getTarifs() {
		return tarifs;
	}
	public void setTarifs(ArrayList<Integer> tarifs) {
		this.tarifs = tarifs;
	}
	public int getNbPlaces() {
		return nbPlaces;
	}
	public void setNbPlaces(int nbPlaces) {
		this.nbPlaces = nbPlaces;
	}
	public String getTypeVehicule() {
		return typeVehicule;
	}
	public void setTypeVehicule(String typeVehicule) {
		this.typeVehicule = typeVehicule;
	}
	
}
