package dto;

public class PromenaArtiklaUPorudzbiniDTO {
	private String idKorisnika;
	private String idArtikla;
	private int kolicina;
	
	public PromenaArtiklaUPorudzbiniDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PromenaArtiklaUPorudzbiniDTO(String idKorisnika, String idArtikla, int kolicina) {
		super();
		this.idKorisnika = idKorisnika;
		this.idArtikla = idArtikla;
		this.kolicina = kolicina;
	}

	public String getIdKorisnika() {
		return idKorisnika;
	}

	public void setIdKorisnika(String idKorisnika) {
		this.idKorisnika = idKorisnika;
	}

	public String getIdArtikla() {
		return idArtikla;
	}

	public void setIdArtikla(String idArtikla) {
		this.idArtikla = idArtikla;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}
	
}
