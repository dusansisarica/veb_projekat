package beans;

import java.awt.image.BufferedImage;

public class Artikal {
	private String naziv;
	private double cena;
	private TipArtikla tipArtikla;
	private Restoran restoran;
	private double kolicina;
	private String opis;
	private BufferedImage slikaArtikla;
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	public TipArtikla getTipArtikla() {
		return tipArtikla;
	}
	public void setTipArtikla(TipArtikla tipArtikla) {
		this.tipArtikla = tipArtikla;
	}
	public Restoran getRestoran() {
		return restoran;
	}
	public void setRestoran(Restoran restoran) {
		this.restoran = restoran;
	}
	public double getKolicina() {
		return kolicina;
	}
	public void setKolicina(double kolicina) {
		this.kolicina = kolicina;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public BufferedImage getSlikaArtikla() {
		return slikaArtikla;
	}
	public void setSlikaArtikla(BufferedImage slikaArtikla) {
		this.slikaArtikla = slikaArtikla;
	}
	public Artikal(String naziv, double cena, TipArtikla tipArtikla, Restoran restoran, double kolicina, String opis,
			BufferedImage slikaArtikla) {
		super();
		this.naziv = naziv;
		this.cena = cena;
		this.tipArtikla = tipArtikla;
		this.restoran = restoran;
		this.kolicina = kolicina;
		this.opis = opis;
		this.slikaArtikla = slikaArtikla;
	}
	public Artikal() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
