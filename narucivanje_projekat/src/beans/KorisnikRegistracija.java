package beans;

public class KorisnikRegistracija {
	private String id;
	private String korisnickoIme;
	private String lozinka;
	private String uloga;
	public KorisnikRegistracija() {
		super();
		// TODO Auto-generated constructor stub
	}
	public KorisnikRegistracija(String id, String korisnickoIme, String lozinka, String uloga) {
		super();
		this.id = id;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.uloga = uloga;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getUloga() {
		return uloga;
	}
	public void setUloga(String uloga) {
		this.uloga = uloga;
	}
	
}
