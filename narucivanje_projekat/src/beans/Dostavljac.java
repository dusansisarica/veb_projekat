package beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Dostavljac extends Korisnik {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5855184826007060372L;
	private List<Porudzbina> porudzbineZaDostavljanje = new ArrayList<Porudzbina>();

	public Dostavljac() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Dostavljac(String idKorisnika, String ime, String prezime, boolean pol, Date datumRodjenja) {
		super(idKorisnika, ime, prezime, pol, datumRodjenja);
		// TODO Auto-generated constructor stub
	}

	public List<Porudzbina> getPorudzbineZaDostavljanje() {
		return porudzbineZaDostavljanje;
	}

	public void setPorudzbineZaDostavljanje(List<Porudzbina> porudzbineZaDostavljanje) {
		this.porudzbineZaDostavljanje = porudzbineZaDostavljanje;
	}
}
