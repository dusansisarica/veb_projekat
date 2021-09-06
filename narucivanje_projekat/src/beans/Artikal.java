package beans;

import java.awt.image.BufferedImage;

public class Artikal {
	private String naziv;
	private double cena;
	private TipArtikla tipArtikla;
	private String idRestoran;
	private double kolicina;
	private String opis;
	private String slikaArtikla;
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
	public String getRestoran() {
		return idRestoran;
	}
	public void setRestoran(String idRestoran) {
		this.idRestoran = idRestoran;
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
	public String getSlikaArtikla() {
		return slikaArtikla;
	}
	public void setSlikaArtikla(String slikaArtikla) {
		this.slikaArtikla = slikaArtikla;
	}
	public Artikal(String naziv, double cena, TipArtikla tipArtikla, String idRestoran, double kolicina, String opis,
			String slikaArtikla) {
		super();
		this.naziv = naziv;
		this.cena = cena;
		this.tipArtikla = tipArtikla;
		this.idRestoran = idRestoran;
		this.kolicina = kolicina;
		this.opis = opis;
		this.slikaArtikla = slikaArtikla;
	}
	public Artikal() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
