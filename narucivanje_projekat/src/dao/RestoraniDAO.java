package dao;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Korisnik;
import beans.Lokacija;
import beans.Restoran;
import beans.TipRestorana;
import dto.RestoranLokacijaDTO;

public class RestoraniDAO {

	private static HashMap<String, Restoran> restorani = new HashMap<String, Restoran>();
	private static String putanja = System.getProperty("catalina.base") + File.separator + "Datoteke" + File.separator + "restorani.json";

	
	public RestoraniDAO(String contextPath) {
		ucitajRestorane(contextPath);
	}
	
	private void ucitajRestorane(String contextPath) {
		ObjectMapper objectMapper = new ObjectMapper();

		File file = new File(putanja);

		ArrayList<Restoran> sviRestorani = new ArrayList<Restoran>();
		try {

			sviRestorani = objectMapper.readValue(file, new TypeReference<ArrayList<Restoran>>() {
			});

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Restoran r : sviRestorani) {
			restorani.put(r.getId(), r);
		}		
		
	}
	
	public static void napraviRestoran(RestoranLokacijaDTO restoranLokacija) {
		Restoran restoran = restoranLokacija.getRestoran();
		restoran.setLokacijaRestorana(restoranLokacija.getLokacija());
		restoran.setId(Integer.toString(restorani.size()+1));
		MenadzeriDAO.postaviMenadzeruRestoran(restoranLokacija);

		restorani.put(restoran.getId(), restoran);
		upisiRestorane(restorani);
	}
	

	private static void upisiRestorane(HashMap<String, Restoran> restorani2) {
		ArrayList<Restoran> sviRestorani = new ArrayList<Restoran>();
		for (Restoran r : restorani.values()) {
			sviRestorani.add(r);
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new FileOutputStream(putanja), sviRestorani);
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public static List<String> dobaviTipoveRestorana(){
		List<String> tipovi = new ArrayList<String>();
		TipRestorana[] tipoviNiz = TipRestorana.values();
		for (TipRestorana i : tipoviNiz) {
			tipovi.add(i.toString());
		}
		return tipovi;
	}
	
	public static List<Restoran> dobaviSveRestorane(){
		List<Restoran> sviRestorani = new ArrayList<Restoran>();
		for (Restoran r : restorani.values()) {
			sviRestorani.add(r);
		}
		return sviRestorani;
	}
}
