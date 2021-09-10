package dto;

import java.util.ArrayList;
import java.util.List;

import beans.Porudzbina;

public class PorudzbinaSaStatusomDTO {
	private List<ArtikalKolicinaDTO> artikalKolicina = new ArrayList<ArtikalKolicinaDTO>();
	private Porudzbina porudzbina;
	
	
	public PorudzbinaSaStatusomDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PorudzbinaSaStatusomDTO(List<ArtikalKolicinaDTO> artikalKolicina, Porudzbina porudzbina) {
		super();
		this.artikalKolicina = artikalKolicina;
		this.porudzbina = porudzbina;
	}

	public List<ArtikalKolicinaDTO> getArtikalKolicina() {
		return artikalKolicina;
	}

	public void setArtikalKolicina(List<ArtikalKolicinaDTO> artikalKolicina) {
		this.artikalKolicina = artikalKolicina;
	}

	public Porudzbina getIdNarudzbine() {
		return porudzbina;
	}

	public void setIdNarudzbine(Porudzbina porudzbina) {
		this.porudzbina = porudzbina;
	}
	
}

