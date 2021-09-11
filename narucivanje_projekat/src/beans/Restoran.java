package beans;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Restoran implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4810208769627288453L;
	private String id;
	private String naziv;
	private TipRestorana tipRestorana;
	private boolean statusRestorana; //0-ne radi, 1-radi
	private String logoRestorana;
	private Lokacija lokacijaRestorana;
	private double prosecnaOcena;
	private List<Artikal> artikli = new ArrayList<Artikal>();

	public Restoran() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Restoran(String naziv, TipRestorana tipRestorana, boolean statusRestorana, String logoRestorana,
			Lokacija lokacijaRestorana, List<Artikal> artikli, double prosecnaOcena) {
		super();
		this.naziv = naziv;
		this.tipRestorana = tipRestorana;
		this.statusRestorana = statusRestorana;
		this.logoRestorana = logoRestorana;
		this.lokacijaRestorana = lokacijaRestorana;
		this.artikli = artikli;
		this.setProsecnaOcena(prosecnaOcena);
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public TipRestorana getTipRestorana() {
		return tipRestorana;
	}
	public void setTipRestorana(TipRestorana tipRestorana) {
		this.tipRestorana = tipRestorana;
	}
	public boolean isStatusRestorana() {
		return statusRestorana;
	}
	public void setStatusRestorana(boolean statusRestorana) {
		this.statusRestorana = statusRestorana;
	}
	public String getLogoRestorana() {
		return logoRestorana;
	}
	public void setLogoRestorana(String logoRestorana) {
		this.logoRestorana = logoRestorana;
	}
	public Lokacija getLokacijaRestorana() {
		return lokacijaRestorana;
	}
	public void setLokacijaRestorana(Lokacija lokacijaRestorana) {
		this.lokacijaRestorana = lokacijaRestorana;
	}
	public List<Artikal> getArtikli() {
		return artikli;
	}
	public void setArtikli(List<Artikal> artikli) {
		this.artikli = artikli;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getProsecnaOcena() {
		return prosecnaOcena;
	}
	public void setProsecnaOcena(double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}
	

}
