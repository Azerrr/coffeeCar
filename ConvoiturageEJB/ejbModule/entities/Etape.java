package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Etape {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String etape;
	private int tarif;


	public String getEtape() {
		return etape;
	}
	public void setEtape(String etape) {
		this.etape = etape;
	}
	public int getTarif() {
		return tarif;
	}
	public void setTarif(int tarif) {
		this.tarif = tarif;
	}

}
