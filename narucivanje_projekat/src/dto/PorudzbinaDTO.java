package dto;

import java.util.ArrayList;
import java.util.List;

public class PorudzbinaDTO {
	private List<ArtikalKolicinaDTO> artikalKolicina = new ArrayList<ArtikalKolicinaDTO>();
	private String idNarudzbine;
	
	public PorudzbinaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PorudzbinaDTO(List<ArtikalKolicinaDTO> artikalKolicina, String idNarudzbine) {
		super();
		this.artikalKolicina = artikalKolicina;
		this.idNarudzbine = idNarudzbine;
	}

	public List<ArtikalKolicinaDTO> getArtikalKolicina() {
		return artikalKolicina;
	}

	public void setArtikalKolicina(List<ArtikalKolicinaDTO> artikalKolicina) {
		this.artikalKolicina = artikalKolicina;
	}

	public String getIdNarudzbine() {
		return idNarudzbine;
	}

	public void setIdNarudzbine(String idNarudzbine) {
		this.idNarudzbine = idNarudzbine;
	}
	
}

