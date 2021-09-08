package dto;

import beans.Artikal;

public class ArtikalKolicinaDTO {
	private Artikal artikal;
	private int kolicina;

	public ArtikalKolicinaDTO(Artikal artikal, int cena) {
		super();
		this.artikal = artikal;
		this.kolicina = cena;
	}
	public ArtikalKolicinaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Artikal getArtikal() {
		return artikal;
	}
	public void setArtikal(Artikal artikal) {
		this.artikal = artikal;
	}
	public int getCena() {
		return kolicina;
	}
	public void setCena(int cena) {
		this.kolicina = cena;
	}
}
