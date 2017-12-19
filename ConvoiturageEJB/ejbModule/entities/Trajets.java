package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Trajets {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private Utilisateur conducteur;
	@ManyToOne
	@JoinTable(name="Reservations")
	private Utilisateur passagers;
	private String villeDepart;
	@OneToMany
	private List<Etape> etapes;
	private String date;
	private String heure;
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
	public List<Etape> getEtapes() {
		return etapes;
	}
	public void setEtapes(List<Etape> etapes) {
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
