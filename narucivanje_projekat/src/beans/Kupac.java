package beans;

import java.util.Date;

public class Kupac extends Korisnik {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9112324163100963187L;

	private Object tipKupca;

	public Object getTipKupca() {
		return tipKupca;
	}

	public void setTipKupca(Object tipKupca) {
		this.tipKupca = tipKupca;
	}

	public Kupac() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Kupac(String idKorisnika, String ime, String prezime, boolean pol, Date datumRodjenja) {
		super(idKorisnika, ime, prezime, pol, datumRodjenja);
		// TODO Auto-generated constructor stub
	}
	
}
