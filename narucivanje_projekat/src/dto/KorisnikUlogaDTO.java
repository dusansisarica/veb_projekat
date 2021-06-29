package dto;

import beans.Korisnik;
import beans.KorisnikRegistracija;

public class KorisnikUlogaDTO {
	private KorisnikRegistracija korisnikRegistracija;
	private Korisnik korisnik;
	public KorisnikUlogaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public KorisnikRegistracija getKorisnikRegistracija() {
		return korisnikRegistracija;
	}
	public void setKorisnikRegistracija(KorisnikRegistracija korisnikRegistracija) {
		this.korisnikRegistracija = korisnikRegistracija;
	}
	public Korisnik getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
}
