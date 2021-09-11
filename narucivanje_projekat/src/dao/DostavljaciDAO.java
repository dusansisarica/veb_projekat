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

import beans.Admin;
import beans.Dostavljac;
import beans.Kupac;
import beans.NormalanKupac;
import dto.KorisnikUlogaDTO;

public class DostavljaciDAO {
	private static HashMap<String, Dostavljac> dostavljaci = new HashMap<String, Dostavljac>();
	private static String putanja = System.getProperty("catalina.base") + File.separator + "Datoteke" + File.separator + "dostavljaci.json";
	
	public DostavljaciDAO(String contextPath) {
		ucitajDostavljace(contextPath);
	}

	private void ucitajDostavljace(String contextPath) {
		ObjectMapper objectMapper = new ObjectMapper();

		File file = new File(putanja);

		ArrayList<Dostavljac> sviDostavljaci = new ArrayList<Dostavljac>();
		try {

			sviDostavljaci = objectMapper.readValue(file, new TypeReference<ArrayList<Dostavljac>>() {
			});

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Dostavljac d : sviDostavljaci) {
			dostavljaci.put(d.getIdKorisnika(), d);
		}		

	}
	
	public static boolean upisiDostavljace(KorisnikUlogaDTO korisnik) {

		Dostavljac dostavljac = new Dostavljac(korisnik.getKorisnikRegistracija().getId(), korisnik.getKorisnik().getIme(), korisnik.getKorisnik().getPrezime(),
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
		
		ArrayList<Dostavljac> sviDostavljaci = new ArrayList<Dostavljac>();
		for (Dostavljac d : dostavljaci.values()) {
			sviDostavljaci.add(d);
		}
		sviDostavljaci.add(dostavljac);
		dostavljaci.put(dostavljac.getIdKorisnika(), dostavljac);

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new FileOutputStream(putanja), sviDostavljaci);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public static boolean kopirajIupisiDostavljaca(Dostavljac dostavljac) {

//		Dostavljac dostavljac = new Dostavljac(korisnik.getKorisnikRegistracija().getId(), korisnik.getKorisnik().getIme(), korisnik.getKorisnik().getPrezime(),
//				korisnik.getKorisnik().isPol(), korisnik.getKorisnik().getDatumRodjenja());
		
//		if(korisnik.getKorisnik().getDatumRodjenja() == null) {
//			return false;
//		}
//		if(korisnik.getKorisnik().getIme() == null || korisnik.getKorisnik().getIme() == "") {
//			return false;
//		}
//		if(korisnik.getKorisnik().getPrezime() == null || korisnik.getKorisnik().getPrezime() == "") {
//			return false;
//		}
//		
//		ArrayList<Dostavljac> sviDostavljaci = new ArrayList<Dostavljac>();
//		for (Dostavljac d : dostavljaci.values()) {
//			sviDostavljaci.add(d);
//		}
		for (Dostavljac d : dostavljaci.values()) {
			if (d.getIdKorisnika().equals(dostavljac.getIdKorisnika())) {
				dostavljaci.replace(dostavljac.getIdKorisnika(), dostavljac);
			}
		}
		List<Dostavljac> sviDostavljaci = new ArrayList<>();
		for (Dostavljac d : dostavljaci.values()) {
			sviDostavljaci.add(d);
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new FileOutputStream(putanja), sviDostavljaci);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public static void upisiDostavljace(List<Dostavljac> dostavljaciZaUpis) {

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new FileOutputStream(putanja), dostavljaciZaUpis);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static Dostavljac nadjiDostavljaca(String id) {
		return dostavljaci.get(id);
	}
	
	public static void izmeniDostavljaca(Dostavljac dostavljac) {
		dostavljaci.replace(dostavljac.getIdKorisnika(), dostavljac);
		
		List<Dostavljac> listaDostavljac = new ArrayList<Dostavljac>();
		for(Dostavljac d : dostavljaci.values()) {
			listaDostavljac.add(d);
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new FileOutputStream(putanja), listaDostavljac);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
