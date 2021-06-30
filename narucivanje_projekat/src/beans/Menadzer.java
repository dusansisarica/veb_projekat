package beans;

import java.util.Date;

public class Menadzer extends Korisnik {
	/**
	 * 
	 */
	private static final long serialVersionUID = 493905104185636402L;
	private String restoranId;

	public String getRestoranId() {
		return restoranId;
	}

	public void setRestoran(String restoranId) {
		this.restoranId = restoranId;
	}

	public Menadzer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Menadzer(String idKorisnika, String ime, String prezime, boolean pol, Date datumRodjenja) {
		super(idKorisnika, ime, prezime, pol, datumRodjenja);
		// TODO Auto-generated constructor stub
	}
	
	public Menadzer(String idKorisnika, String ime, String prezime, boolean pol, Date datumRodjenja, String restoranId) {
		super(idKorisnika, ime, prezime, pol, datumRodjenja);
		this.restoranId = restoranId;
	}

}
