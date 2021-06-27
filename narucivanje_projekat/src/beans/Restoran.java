package beans;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Restoran {
	private String naziv;
	private TipRestorana tipRestorana;
	private boolean statusRestorana; //0-ne radi, 1-radi
	private BufferedImage logoRestorana;
	private Lokacija lokacijaRestorana;
	private List<Artikal> artikli = new ArrayList<Artikal>();

	public Restoran() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Restoran(String naziv, TipRestorana tipRestorana, boolean statusRestorana, BufferedImage logoRestorana,
			Lokacija lokacijaRestorana, List<Artikal> artikli) {
		super();
		this.naziv = naziv;
		this.tipRestorana = tipRestorana;
		this.statusRestorana = statusRestorana;
		this.logoRestorana = logoRestorana;
		this.lokacijaRestorana = lokacijaRestorana;
		this.artikli = artikli;
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
	public BufferedImage getLogoRestorana() {
		return logoRestorana;
	}
	public void setLogoRestorana(BufferedImage logoRestorana) {
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
	

}
