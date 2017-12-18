package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class Utilisateur {
	
	@Id
	private String identifiant;
	private String motDePasse;
	
	@OneToMany(mappedBy="passagers")
	private List<Trajets> trajets = new ArrayList<>();
	private int role; // (1=ADMIN, 2=UTILISATEUR)
	

	public List<Trajets> getTrajets() {
		return trajets;
	}
	public void setTrajets(List<Trajets> trajets) {
		this.trajets = trajets;
	}
	
	public String getIdentifiant() {
		return identifiant;
	}
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	
	

}
