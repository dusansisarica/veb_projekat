package dto;

import beans.Korisnik;
import beans.KorisnikRegistracija;

public class PorudzbineDTO {
	private String korisnikId;
	private String artikalId;
	public String getKorisnikId() {
		return korisnikId;
	}
	public void setKorisnikId(String korisnikId) {
		this.korisnikId = korisnikId;
	}
	public String getArtikalId() {
		return artikalId;
	}
	public void setArtikalId(String artikalId) {
		this.artikalId = artikalId;
	}
	public int getKolicina() {
		return kolicina;
	}
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}
	private int kolicina;
	public PorudzbineDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
}
