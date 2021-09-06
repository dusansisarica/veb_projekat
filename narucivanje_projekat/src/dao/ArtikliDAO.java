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

import beans.Artikal;
import beans.Menadzer;
import dto.KorisnikUlogaDTO;
import dto.RestoranLokacijaDTO;

public class ArtikliDAO {
	private static HashMap<String, Artikal> artikli = new HashMap<String, Artikal>();
	private static String putanja = System.getProperty("catalina.base") + File.separator + "Datoteke" + File.separator + "artikli.json";
	
	public ArtikliDAO(String contextPath) {
		ucitajArtikle(contextPath);
	}

	private void ucitajArtikle(String contextPath) {
		ObjectMapper objectMapper = new ObjectMapper();

		File file = new File(putanja);

		ArrayList<Artikal> sviArtikli = new ArrayList<Artikal>();
		try {

			sviArtikli = objectMapper.readValue(file, new TypeReference<ArrayList<Artikal>>() {
			});

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Artikal a : sviArtikli) {
			artikli.put(a.getNaziv(), a);
		}		

	}
	
	public static boolean upisiArtikal(Artikal artikal) {

		Artikal artikalUpis = new Artikal(artikal.getNaziv(), artikal.getCena(), artikal.getTipArtikla(),
				artikal.getRestoran(), artikal.getKolicina(), artikal.getOpis(), artikal.getSlikaArtikla());
		
		if(artikal.getNaziv() == null || artikal.getNaziv() == "") {
			return false;
		}
		if(artikal.getCena() == 0.0) {
			return false;
		}
		if(artikal.getKolicina() == 0.0) {
			return false;
		}
		if(artikal.getOpis() == null || artikal.getOpis() == "") {
			return false;
		}
		
		ArrayList<Artikal> sviArtikli = new ArrayList<Artikal>();
		for (Artikal a : artikli.values()) {
			sviArtikli.add(a);
		}
		sviArtikli.add(artikal);
		artikli.put(artikal.getNaziv(), artikal);

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new FileOutputStream(putanja), sviArtikli);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
}

