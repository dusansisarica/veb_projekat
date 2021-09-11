package dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Kupac;
import beans.Menadzer;
import dto.KorisnikUlogaDTO;
import dto.RestoranLokacijaDTO;

public class MenadzeriDAO {
	private static HashMap<String, Menadzer> menadzeri = new HashMap<String, Menadzer>();
	private static String putanja = System.getProperty("catalina.base") + File.separator + "Datoteke" + File.separator + "menadzeri.json";
	
	public MenadzeriDAO(String contextPath) {
		ucitajMenadzere(contextPath);
	}

	private void ucitajMenadzere(String contextPath) {
		ObjectMapper objectMapper = new ObjectMapper();

		File file = new File(putanja);

		ArrayList<Menadzer> sviMenadzeri = new ArrayList<Menadzer>();
		try {

			sviMenadzeri = objectMapper.readValue(file, new TypeReference<ArrayList<Menadzer>>() {
			});

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Menadzer m : sviMenadzeri) {
			menadzeri.put(m.getIdKorisnika(), m);
		}		

	}
	
	public static boolean upisiMenadzera(KorisnikUlogaDTO korisnik) {

		Menadzer menadzer = new Menadzer(korisnik.getKorisnikRegistracija().getId(), korisnik.getKorisnik().getIme(), korisnik.getKorisnik().getPrezime(),
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
		
		ArrayList<Menadzer> sviMenadzeri = new ArrayList<Menadzer>();
		for (Menadzer m : menadzeri.values()) {
			sviMenadzeri.add(m);
		}
		sviMenadzeri.add(menadzer);
		menadzeri.put(menadzer.getIdKorisnika(), menadzer);

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new FileOutputStream(putanja), sviMenadzeri);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public static Menadzer nadjiMenadzera(String id) {
		return menadzeri.get(id);
	}
	
	public static List<Menadzer> dobaviSlobodneMenadzere(){
		List<Menadzer> slobodniMenadzeri = new ArrayList<Menadzer>();
		for(Menadzer m : menadzeri.values()) {
			if (m.getRestoranId() == null) {
				slobodniMenadzeri.add(m);
			}
		}
		return slobodniMenadzeri;
	}
	
	public static void azurirajMenadzera(Menadzer menadzer) {
		for (Menadzer m : menadzeri.values()) {
			if (m.getIdKorisnika().equals(menadzer.getIdKorisnika())) {
				m.setIdKorisnika(menadzer.getIdKorisnika());
				m.setDatumRodjenja(menadzer.getDatumRodjenja());
				m.setIme(menadzer.getIme());
				m.setPrezime(menadzer.getPrezime());
				m.setPol(menadzer.isPol());
				m.setRestoran(menadzer.getRestoranId());
			}
		}
		for (Menadzer m : menadzeri.values()) {
			System.out.println(m.getRestoranId());
		}
		upisiMenadzere(menadzeri.values());
	}
	
	public static void postaviMenadzeruRestoran(RestoranLokacijaDTO restoranLokacija) {
		Menadzer menadzer = nadjiMenadzera(restoranLokacija.getMenadzerId());
		menadzer.setRestoran(restoranLokacija.getRestoran().getId());
		azurirajMenadzera(menadzer);
	}
	
	public static void izmeniMenadzera(Menadzer menadzer) {
		menadzeri.replace(menadzer.getIdKorisnika(), menadzer);
		
		List<Menadzer> listaMenadzer = new ArrayList<Menadzer>();
		for(Menadzer m : menadzeri.values()) {
			listaMenadzer.add(m);
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new FileOutputStream(putanja), menadzeri);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void upisiMenadzere(Collection<Menadzer> menadzeri) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new FileOutputStream(putanja), menadzeri);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
