package beans;

public class ZlatniKupac{
	public String imeTipa = "Zlatni kupac";

	public double izracunajCenu(double cena) {
		return cena - cena*0.05;
	}
}
