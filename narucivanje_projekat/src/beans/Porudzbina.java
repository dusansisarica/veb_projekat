package beans;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Porudzbina implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idPorudzbine;
	private HashMap<String, Integer> artikli = new HashMap<String, Integer>();
	private String idRestoran;
	private Date datumVreme;
	private double cena;
	private String idKupac;
	private TipPorudzbine tipPorudzbine;
	
	public Porudzbina() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Porudzbina(String idPorudzbine,String idRestoran, HashMap<String, Integer> artikli, String idKupac, Date datumVreme, double cena, TipPorudzbine tipPorudzbine) {
		super();
		this.idPorudzbine = idPorudzbine;
		this.idRestoran = idRestoran;
		this.artikli = artikli;
		this.idKupac = idKupac;
		this.datumVreme = datumVreme;
		this.cena = cena;
		this.tipPorudzbine = tipPorudzbine;
	}
	public String getIdPorudzbine() {
		return idPorudzbine;
	}
	public void setIdPorudzbine(String idPorudzbine) {
		this.idPorudzbine = idPorudzbine;
	}
	public HashMap<String, Integer> getArtikli() {
		return artikli;
	}
	public void setArtikli(HashMap<String, Integer> artikli) {
		this.artikli = artikli;
	}
	public String getIdRestoran() {
		return idRestoran;
	}
	public void setIdRestoran(String idRestoran) {
		this.idRestoran = idRestoran;
	}
	public Date getDatumVreme() {
		return datumVreme;
	}
	public void setDatumVreme(Date datumVreme) {
		this.datumVreme = datumVreme;
	}
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	public String getIdKupac() {
		return idKupac;
	}
	public void setIdKupac(String idKupac) {
		this.idKupac = idKupac;
	}
	public TipPorudzbine getTipPorudzbine() {
		return tipPorudzbine;
	}
	public void setTipPorudzbine(TipPorudzbine tipPorudzbine) {
		this.tipPorudzbine = tipPorudzbine;
	}
	

}
