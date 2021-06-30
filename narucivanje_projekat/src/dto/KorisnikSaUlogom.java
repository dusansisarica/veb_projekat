package dto;

import beans.Korisnik;

public class KorisnikSaUlogom {
	private Korisnik korisnik;
	private String uloga;
	
	
	public KorisnikSaUlogom() {
		super();
		// TODO Auto-generated constructor stub
	}
	public KorisnikSaUlogom(Korisnik korisnik, String uloga) {
		super();
		this.korisnik = korisnik;
		this.uloga = uloga;
	}
	public Korisnik getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	public String getUloga() {
		return uloga;
	}
	public void setUloga(String uloga) {
		this.uloga = uloga;
	}
}
