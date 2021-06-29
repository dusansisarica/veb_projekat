package beans;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


public class Korisnik implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8205325603280773858L;
	private String idKorisnika;
	private String korisnickoIme;
	private String lozinka;
	private String ime;
	private String prezime;
	private String uloga;
	private boolean pol;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date datumRodjenja;
	private Object tipKupca;
	//TODO: Sve porudzbine, verovatno lista porudzbina, ako je Kupac; to bi moglo preko interfejsa mozda
	//TODO: Restoran, ako je Menadzer
	//TODO: Porudzbine koje treba da dostavi, ako je dostavljac
	//TODO: Broj sakupljenih bodova ako je Kupac
	public Korisnik() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Korisnik(String idKorisnika, String korisnickoIme, String lozinka, String ime, String prezime, String uloga,
			boolean pol, Date datumRodjenja, Object tipKupca) {
		super();
		this.idKorisnika = idKorisnika;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.uloga = uloga;
		this.pol = pol;
		this.datumRodjenja = datumRodjenja;
		this.tipKupca = tipKupca;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	
	public String getIdKorisnika() {
		return idKorisnika;
	}
	public void setIdKorisnika(String idKorisnika) {
		this.idKorisnika = idKorisnika;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public boolean isPol() {
		return pol;
	}
	public void setPol(boolean pol) {
		this.pol = pol;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ime == null) ? 0 : ime.hashCode());
		result = prime * result + ((korisnickoIme == null) ? 0 : korisnickoIme.hashCode());
		result = prime * result + ((lozinka == null) ? 0 : lozinka.hashCode());
		result = prime * result + (pol ? 1231 : 1237);
		result = prime * result + ((prezime == null) ? 0 : prezime.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Korisnik other = (Korisnik) obj;
		if (ime == null) {
			if (other.ime != null)
				return false;
		} else if (!ime.equals(other.ime))
			return false;
		if (korisnickoIme == null) {
			if (other.korisnickoIme != null)
				return false;
		} else if (!korisnickoIme.equals(other.korisnickoIme))
			return false;
		if (lozinka == null) {
			if (other.lozinka != null)
				return false;
		} else if (!lozinka.equals(other.lozinka))
			return false;
		if (pol != other.pol)
			return false;
		if (prezime == null) {
			if (other.prezime != null)
				return false;
		} else if (!prezime.equals(other.prezime))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Korisnik [korisnickoIme=" + korisnickoIme + ", lozinka=" + lozinka + ", ime=" + ime + ", prezime="
				+ prezime + ", pol=" + pol + "]";
	}

	public Date getDatumRodjenja() {
		return datumRodjenja;
	}
	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	public String getUloga() {
		return uloga;
	}
	public void setUloga(String uloga) {
		this.uloga = uloga;
	}
	public Object getTipKupca() {
		return tipKupca;
	}
	public void setTipKupca(Object tipKupca) {
		this.tipKupca = tipKupca;
	}
	
	
}
