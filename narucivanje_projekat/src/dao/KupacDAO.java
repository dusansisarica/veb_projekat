package dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.KorisnikRegistracija;
import beans.Kupac;
import beans.NormalanKupac;
import dto.KorisnikUlogaDTO;

public class KupacDAO {
	private static HashMap<String, Kupac> kupci = new HashMap<String, Kupac>();
	private static String putanja = System.getProperty("catalina.base") + File.separator + "Datoteke" + File.separator + "kupci.json";
	
	public KupacDAO(String contextPath) {
		ucitajKupce(contextPath);
	}

	private void ucitajKupce(String contextPath) {
		ObjectMapper objectMapper = new ObjectMapper();

		File file = new File(putanja);

		ArrayList<Kupac> sviKupci = new ArrayList<Kupac>();
		try {

			sviKupci = objectMapper.readValue(file, new TypeReference<ArrayList<Kupac>>() {
			});

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Kupac k : sviKupci) {
			kupci.put(k.getIdKorisnika(), k);
		}		

	}
	
	public static boolean upisiKupca(KorisnikUlogaDTO korisnik) {

		Kupac kupac = new Kupac(korisnik.getKorisnikRegistracija().getId(), korisnik.getKorisnik().getIme(), korisnik.getKorisnik().getPrezime(),
				korisnik.getKorisnik().isPol(), korisnik.getKorisnik().getDatumRodjenja());
		
		if(korisnik.getKorisnik().getDatumRodjenja() == null) {
			return false;
		}
		if(korisnik.getKorisnik().getIme() == null || korisnik.getKorisnik().getIme() == "") {
			return false;
		}
		if(korisnik.getKorisnik().getPrezime() == null || korisnik.getKorisnik().getPrezime() == "") {
			return false;
		}
		
		kupac.setTipKupca(new NormalanKupac());

		ArrayList<Kupac> sviKupci = new ArrayList<Kupac>();
		for (Kupac k : kupci.values()) {
			sviKupci.add(k);
		}
		sviKupci.add(kupac);
		kupci.put(kupac.getIdKorisnika(), kupac);

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new FileOutputStream(putanja), sviKupci);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public static void kopirajKupcaIUpisi(Kupac kupac) {
		List<Kupac> sviKupci = new ArrayList<>();
		for (Kupac k : kupci.values()) {
			sviKupci.add(k);
		}
		for (Kupac k : sviKupci) {
			if (k.getIdKorisnika().equals(kupac.getIdKorisnika())) {
				kupci.replace(k.getIdKorisnika(), kupac);
			}
		}
		List<Kupac> sviKupciUpis = new ArrayList<>();

		for (Kupac k : kupci.values()) {
			sviKupciUpis.add(k);
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new FileOutputStream(putanja), sviKupciUpis);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static Kupac nadjiKupca(String id) {
		return kupci.get(id);
	}
	
	public static void izmeniKupca(Kupac kupac) {
		kupci.replace(kupac.getIdKorisnika(), kupac);
		
		List<Kupac> listaKupac = new ArrayList<Kupac>();
		for(Kupac k : kupci.values()) {
			listaKupac.add(k);
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new FileOutputStream(putanja), listaKupac);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
