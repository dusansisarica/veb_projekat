package dto;

import beans.Komentar;

public class KupacRestoranDTO {
	private String idNarudzbine;
	private Komentar komentar;
	public KupacRestoranDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public KupacRestoranDTO(String idNarudzbine, Komentar komentar) {
		super();
		this.idNarudzbine = idNarudzbine;
		this.komentar = komentar;
	}
	public String getIdNarudzbine() {
		return idNarudzbine;
	}
	public void setIdNarudzbine(String idNarudzbine) {
		this.idNarudzbine = idNarudzbine;
	}
	public Komentar getKomentar() {
		return komentar;
	}
	public void setKomentar(Komentar komentar) {
		this.komentar = komentar;
	}
	
}
