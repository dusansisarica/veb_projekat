package dto;

import beans.TipArtikla;

public class ArtikalDTO {
	
	private String id;
	private String naziv;
	private double cena;
	private TipArtikla tipArtikla;
	private double kolicina;
	private String opis;
	private String slikaArtikla;
	
	public ArtikalDTO(String naziv, double cena, TipArtikla tipArtikla, double kolicina, String opis,
			String slikaArtikla) {
		super();
		this.naziv = naziv;
		this.cena = cena;
		this.tipArtikla = tipArtikla;
		this.kolicina = kolicina;
		this.opis = opis;
		this.slikaArtikla = slikaArtikla;
	}
	public ArtikalDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
