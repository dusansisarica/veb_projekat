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

import beans.Dostavljac;
import beans.Komentar;
import dto.KorisnikUlogaDTO;
import dto.KupacRestoranDTO;

public class KomentariDAO {
	private static HashMap<String, Komentar> komentari = new HashMap<String, Komentar>();
	private static String putanja = System.getProperty("catalina.base") + File.separator + "Datoteke" + File.separator + "komentari.json";
	
	public KomentariDAO(String contextPath) {
		ucitajKomentare(contextPath);
	}

	private void ucitajKomentare(String contextPath) {
		ObjectMapper objectMapper = new ObjectMapper();

		File file = new File(putanja);

		ArrayList<Komentar> sviKomentari = new ArrayList<Komentar>();
		try {

			sviKomentari = objectMapper.readValue(file, new TypeReference<ArrayList<Komentar>>() {
			});

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Komentar d : sviKomentari) {
			komentari.put(d.getIdKomentara(), d);
		}		

	}
	
	public static boolean upisiKomentar(KupacRestoranDTO komentar) {
		Komentar komentarZaUpis = new Komentar(komentar.getKomentar().getIdKorisnika(),PorudzbineDAO.dobaviPorudzbinu(komentar.getIdNarudzbine()).getIdRestoran()
				, komentar.getKomentar().getTekst(), komentar.getKomentar().getOcena(), false, Integer.toString(komentari.size()+1));
		
		komentari.put(komentarZaUpis.getIdKomentara(), komentarZaUpis);
		ArrayList<Komentar> sviKomentari = new ArrayList<Komentar>();
		
		for (Komentar d : komentari.values()) {
			sviKomentari.add(d);
		}

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new FileOutputStream(putanja), sviKomentari);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public static List<Komentar> dobaviSveKomentare() {
		List<Komentar> sviKomentari = new ArrayList<>();
		for (Komentar k : komentari.values()) {
			sviKomentari.add(k);
		}
		return sviKomentari;
	}

	public static List<Komentar> dobaviKomentareZaRestoran(String id) {
		List<Komentar> sviKomentari = dobaviSveKomentare();
		List<Komentar> komentariZaRestoran = new ArrayList<>();

		for (Komentar k : sviKomentari) {
			if (k.getIdRestorana().equals(id)) {
				komentariZaRestoran.add(k);
			}
		}
		
		return komentariZaRestoran;
	}
	
	public static void kopirajIUpisiKomentar(Komentar k) {
		komentari.replace(k.getIdKomentara(), k);
		List<Komentar> sviKomentari = dobaviSveKomentare();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new FileOutputStream(putanja), sviKomentari);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean odobriKomentar(String id) {
		List<Komentar> sviKomentari = dobaviSveKomentare();
		for (Komentar k : sviKomentari) {
			if (k.getIdKomentara().equals(id)) {
				k.setOdobreno(true);
				kopirajIUpisiKomentar(k);
				return true;
			}
		}
		return false;
	}

	public static List<Komentar> dobaviOdobreneKomentareZaRestoran(String id) {
		List<Komentar> sviKomentariZaRestoran = dobaviKomentareZaRestoran(id);
		List<Komentar> odobreniKomentari = new ArrayList<>();
		for (Komentar k : sviKomentariZaRestoran) {
			if (k.isOdobreno()) {
				odobreniKomentari.add(k);
			}
		}
		return odobreniKomentari;
	}

}
