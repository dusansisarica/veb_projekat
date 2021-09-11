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
	private String ime;
	private String prezime;
	private boolean pol;
	private boolean obrisan;
	private boolean banovan;
	public boolean isBanovan() {
		return banovan;
	}

	public void setBanovan(boolean banovan) {
		this.banovan = banovan;
	}

	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date datumRodjenja;
	//TODO: Sve porudzbine, verovatno lista porudzbina, ako je Kupac; to bi moglo preko interfejsa mozda
	//TODO: Restoran, ako je Menadzer
	//TODO: Porudzbine koje treba da dostavi, ako je dostavljac
	//TODO: Broj sakupljenih bodova ako je Kupac
	//TODO: Tip kupca : ime tipa, popust, trazeni broj bodova za sledeci nivo
	public Korisnik() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Korisnik(String idKorisnika, String ime, String prezime,
			boolean pol, Date datumRodjenja) {
		super();
		this.idKorisnika = idKorisnika;
		this.ime = ime;
		this.prezime = prezime;
		this.pol = pol;
		this.datumRodjenja = datumRodjenja;
	}

	public String getIdKorisnika() {
		return idKorisnika;
	}
	public void setIdKorisnika(String idKorisnika) {
		this.idKorisnika = idKorisnika;
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
		
		if (pol != other.pol)
			return false;
		if (prezime == null) {
			if (other.prezime != null)
				return false;
		} else if (!prezime.equals(other.prezime))
			return false;
		return true;
	}


	public Date getDatumRodjenja() {
		return datumRodjenja;
	}
	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	
	
	
}
