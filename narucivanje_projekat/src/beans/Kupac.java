package beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Kupac extends Korisnik {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9112324163100963187L;

	private Object tipKupca;
	private List<Porudzbina> svePorudzbine = new ArrayList<Porudzbina>();
	private Porudzbina korpa;
	private double brojSakupljenihBodova;
	private int sumnjiviKupac;

	public int getSumnjiviKupac() {
		return sumnjiviKupac;
	}

	public void setSumnjiviKupac(int sumnjiviKupac) {
		this.sumnjiviKupac = sumnjiviKupac;
	}

	public Porudzbina getKorpa() {
		return korpa;
	}

	public void setKorpa(Porudzbina korpa) {
		this.korpa = korpa;
	}

	public double getBrojSakupljenihBodova() {
		return brojSakupljenihBodova;
	}

	public void setBrojSakupljenihBodova(double brojSakupljenihBodova) {
		this.brojSakupljenihBodova = brojSakupljenihBodova;
	}

	public List<Porudzbina> getSvePorudzbine() {
		return svePorudzbine;
	}

	public void setSvePorudzbine(List<Porudzbina> svePorudzbine) {
		this.svePorudzbine = svePorudzbine;
	}

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
