package beans;

public class Komentar {
	private String idKomentara;
	private String idKorisnika;
	private String idRestorana;
	private String tekst;
	private int ocena;
	private boolean odobreno;
	public Komentar() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getIdKomentara() {
		return idKomentara;
	}
	public void setIdKomentara(String idKomentara) {
		this.idKomentara = idKomentara;
	}
	public Komentar(String idKorisnika, String idRestorana, String tekst, int ocena, boolean odobreno, String idKomentara) {
		super();
		this.idKorisnika = idKorisnika;
		this.idRestorana = idRestorana;
		this.tekst = tekst;
		this.ocena = ocena;
		this.odobreno = odobreno;
		this.idKomentara = idKomentara;
	}
	public String getIdKorisnika() {
		return idKorisnika;
	}
	public void setIdKorisnika(String idKorisnika) {
		this.idKorisnika = idKorisnika;
	}
	public String getIdRestorana() {
		return idRestorana;
	}
	public void setIdRestorana(String idRestorana) {
		this.idRestorana = idRestorana;
	}
	public String getTekst() {
		return tekst;
	}
	public void setTekst(String tekst) {
		this.tekst = tekst;
	}
	public int getOcena() {
		return ocena;
	}
	public void setOcena(int ocena) {
		this.ocena = ocena;
	}
	public boolean isOdobreno() {
		return odobreno;
	}
	public void setOdobreno(boolean odobreno) {
		this.odobreno = odobreno;
	}
}
