package dto;

import beans.Lokacija;
import beans.Restoran;

public class RestoranLokacijaDTO {
	private Restoran restoran;
	private Lokacija lokacija;
	public RestoranLokacijaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RestoranLokacijaDTO(Restoran restoran, Lokacija lokacija) {
		super();
		this.restoran = restoran;
		this.lokacija = lokacija;
	}
	public Restoran getRestoran() {
		return restoran;
	}
	public void setRestoran(Restoran restoran) {
		this.restoran = restoran;
	}
	public Lokacija getLokacija() {
		return lokacija;
	}
	public void setLokacija(Lokacija lokacija) {
		this.lokacija = lokacija;
	}

}
