package beans;

public class SrebrniKupac{
	public String imeTipa = "Srebrni kupac";

	public static double izracunajCenu(double cena) {
		return cena - cena*0.03;
	}
}
