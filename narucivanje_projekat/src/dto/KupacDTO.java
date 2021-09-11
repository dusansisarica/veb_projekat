package dto;

import java.util.Date;

import beans.Artikal;

public class KupacDTO {
	private String idKorisnika;
	private String ime;
	private String prezime;
	private Date datumRodjenja;
	private String korisnickoIme;
	private String lozinka;
	
	public KupacDTO(String idKorisnika, String ime, String prezime, Date datumRodjenja, String korisnickoIme, String lozinka) {
		super();
		this.idKorisnika = idKorisnika;
		this.ime = ime;
		this.prezime = prezime;
		this.datumRodjenja = datumRodjenja;
		this.korisnickoIme = korisnickoIme;
		this.setLozinka(lozinka);
	}
	public KupacDTO() {
		super();
		// TODO Auto-generated constructor stub
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
	public Date getDatumRodjenja() {
		return datumRodjenja;
	}
	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	
}
