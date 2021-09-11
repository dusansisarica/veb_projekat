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

import beans.Admin;
import beans.Artikal;
import beans.Dostavljac;
import beans.KorisnikRegistracija;
import beans.Kupac;
import beans.Menadzer;
import beans.Porudzbina;
import beans.Restoran;
import dto.ArtikalDTO;
import dto.DostavljacDTO;
import dto.KorisnikSaUlogom;
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
			artikli.put(a.getIdArtikla(), a);
		}		

	}
	
	
	
	public static Artikal dobaviArtikalPoId(String id) {
		return artikli.get(id);
	}
	
	
	
	public static boolean upisiArtikal(Artikal artikal) {

		Artikal artikalUpis = new Artikal(artikal.getNaziv(), artikal.getCena(), artikal.getTipArtikla(),
				artikal.getRestoran(), artikal.getKolicina(), artikal.getOpis(), artikal.getSlikaArtikla());
		ArrayList<Artikal> sviArtikli1 = new ArrayList<Artikal>();
		for (Artikal a : artikli.values()) {
			if(a.getNaziv().equals(artikal.getNaziv())){
				return false;
			}
		}
		
		if(artikal.getNaziv() == null || artikal.getNaziv() == "") {
			return false;
		}
		if(artikal.getCena() == 0.0) {
			return false;
		}
		if(artikal.getTipArtikla() == null) {
			return false;
		}
	
		if(artikal.getSlikaArtikla() == "") {
			return false;
		}
		
		artikal.setIdArtikla(Integer.toString(artikli.size()));
		RestoraniDAO.dodajArtikalRestoranu(artikal.getRestoran(), artikal);
		
		ArrayList<Artikal> sviArtikli = new ArrayList<Artikal>();
		for (Artikal a : artikli.values()) {
			sviArtikli.add(a);
		}
		sviArtikli.add(artikal);
		artikli.put(artikal.getIdArtikla(), artikal);

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new FileOutputStream(putanja), sviArtikli);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public static Artikal dobaviArtikalPrekoId(String idArtikla) {
		return artikli.get(idArtikla);
	}
	
	public static void izmeniArtikal(ArtikalDTO artikalDto) {
		Artikal artikal = new Artikal();
		for(Artikal a : artikli.values()) {
			if(a.getIdArtikla().equals(artikalDto.getId())) {
				artikal = a;
			}
		}
		artikal.setCena(artikalDto.getCena());
		artikal.setKolicina(artikalDto.getKolicina());
		artikal.setNaziv(artikalDto.getNaziv());
		artikal.setOpis(artikalDto.getOpis());
		if(artikalDto.getSlikaArtikla() != null) {
			artikal.setSlikaArtikla(artikalDto.getSlikaArtikla());
		}
		if(artikalDto.getTipArtikla() != null) {
			artikal.setTipArtikla(artikalDto.getTipArtikla());
		}


		artikli.replace(artikalDto.getId(), artikal);
		
		List<Artikal> artikliList = new ArrayList<Artikal>();
		for(Artikal a : artikli.values()) {
			artikliList.add(a);
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new FileOutputStream(putanja), artikliList);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		Restoran r = RestoraniDAO.dobaviRestoran(artikal.getRestoran());
		RestoraniDAO.zameniArtikalRestoranu(artikal.getRestoran(), artikal);
		
	}
	
}

