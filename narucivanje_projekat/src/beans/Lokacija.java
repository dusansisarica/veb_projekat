package beans;

public class Lokacija {
	private float geografskaDuzina;
	private float geografskaSirina;
	private String adresa;
	public Lokacija() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Lokacija(float geografskaDuzina, float geografskaSirina, String adresa) {
		super();
		this.geografskaDuzina = geografskaDuzina;
		this.geografskaSirina = geografskaSirina;
		this.adresa = adresa;
	}
	public float getGeografskaDuzina() {
		return geografskaDuzina;
	}
	public void setGeografskaDuzina(float geografskaDuzina) {
		this.geografskaDuzina = geografskaDuzina;
	}
	public float getGeografskaSirina() {
		return geografskaSirina;
	}
	public void setGeografskaSirina(float geografskaSirina) {
		this.geografskaSirina = geografskaSirina;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	
}
