package dto;

import java.util.ArrayList;
import java.util.List;

import beans.Dostavljac;
import beans.Porudzbina;

public class PorudzbinaDostavljacObjekatDTO {
	private List<Dostavljac> dostavljaci = new ArrayList<>();
	private Porudzbina porudzbina;
	
	public PorudzbinaDostavljacObjekatDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PorudzbinaDostavljacObjekatDTO(List<Dostavljac> dostavljaci, Porudzbina porudzbina) {
		super();
		this.dostavljaci = dostavljaci;
		this.porudzbina = porudzbina;
	}
	public List<Dostavljac> getDostavljac() {
		return dostavljaci;
	}
	public void setDostavljac(List<Dostavljac> dostavljac) {
		this.dostavljaci = dostavljac;
	}
	public Porudzbina getPorudzbina() {
		return porudzbina;
	}
	public void setPorudzbina(Porudzbina porudzbina) {
		this.porudzbina = porudzbina;
	}
	
}
