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

import beans.Artikal;
import beans.Kupac;
import beans.Porudzbina;
import dto.PorudzbineDTO;

public class PorudzbineDAO {
	private static HashMap<String, Porudzbina> porudzbine = new HashMap<String, Porudzbina>();
	private static String putanja = System.getProperty("catalina.base") + File.separator + "Datoteke" + File.separator + "porudzbine.json";
	
	public PorudzbineDAO(String contextPath) {
		ucitajPorudzbine(contextPath);
	}
	
	private void ucitajPorudzbine(String contextPath) {
		ObjectMapper objectMapper = new ObjectMapper();

		File file = new File(putanja);

		ArrayList<Porudzbina> svePorudzbine = new ArrayList<Porudzbina>();
		try {

			svePorudzbine = objectMapper.readValue(file, new TypeReference<ArrayList<Porudzbina>>() {
			});

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Porudzbina p : svePorudzbine) {
			porudzbine.put(p.getIdPorudzbine(), p);
		}		

	}
	
	public static boolean kreirajPorudzbinu(Porudzbina porudzbina) {

				
		ArrayList<Porudzbina> svePorudzbine = new ArrayList<Porudzbina>();
		for (Porudzbina p : porudzbine.values()) {
			svePorudzbine.add(p);
		}
		
		int idP = svePorudzbine.size() + 1;
		Porudzbina porudzbinaUpis = new Porudzbina(Integer.toString(idP),porudzbina.getIdRestoran(), porudzbina.getArtikli(), porudzbina.getIdKupac(), porudzbina.getDatumVreme(), porudzbina.getCena(), porudzbina.getTipPorudzbine());

		svePorudzbine.add(porudzbinaUpis);
		porudzbine.put(porudzbinaUpis.getIdPorudzbine(), porudzbinaUpis);

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new FileOutputStream(putanja), svePorudzbine);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public static Porudzbina dobaviPorudzbinu(String id) {
		return porudzbine.get(id);
	}
	
	public static List<Porudzbina> dobaviPorudzbineKupca(String idKupca) {
		ArrayList<Porudzbina> porudzbine = new ArrayList<Porudzbina>();
		for (Porudzbina p : porudzbine) {
			if(p.getIdKupac() == idKupca) {
				porudzbine.add(p);
			}
		}
		return porudzbine;
	}
	
	public static List<Porudzbina> dobaviPorudzbineRestorana(String idRestorana) {
		ArrayList<Porudzbina> porudzbine = new ArrayList<Porudzbina>();
		for (Porudzbina p : porudzbine) {
			if(p.getIdRestoran() == idRestorana) {
				porudzbine.add(p);
			}
		}
		return porudzbine;
	}
	
	public static void dodajArtikalUPorudzbinu(PorudzbineDTO porudzbineDto) {
		Kupac kupac = KorisniciDAO.pronadjiKupcaPoId(porudzbineDto.getKorisnikId());
		if (kupac.getKorpa() == null) {
			kupac.setKorpa(new Porudzbina());
		}
		Porudzbina porudzbina = kupac.getKorpa();
		if (!porudzbina.getArtikli().containsKey(porudzbineDto.getArtikalId())) {
			porudzbina.getArtikli().put(porudzbineDto.getArtikalId(), porudzbineDto.getKolicina());
		}
		else {
			int kol = porudzbina.getArtikli().get(porudzbineDto.getArtikalId()) + porudzbineDto.getKolicina();
			porudzbina.getArtikli().replace(porudzbineDto.getArtikalId(), kol);
		}
		Artikal artikal = ArtikliDAO.dobaviArtikalPrekoId(porudzbineDto.getArtikalId());
		porudzbina.setCena(porudzbina.getCena() + porudzbineDto.getKolicina()*artikal.getCena());
		kupac.setKorpa(porudzbina);
	}
}
