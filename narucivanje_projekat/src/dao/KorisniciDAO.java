package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.validation.OverridesAttribute.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Korisnik;
import beans.NormalanKupac;


public class KorisniciDAO {

	private static HashMap<String, Korisnik> korisnici = new HashMap<String, Korisnik>();
	private static String putanja = System.getProperty("catalina.base") + File.separator + "Datoteke" + File.separator + "korisnici.json";
	
	public KorisniciDAO(String contextPath) {
		ucitajKorisnike(contextPath);
	}

	private void ucitajKorisnike(String contextPath) {
		// TODO Auto-generated method stub
		ObjectMapper objectMapper = new ObjectMapper();

		File file = new File(putanja);

		ArrayList<Korisnik> sviKorisnici = new ArrayList<Korisnik>();
		try {

			sviKorisnici = objectMapper.readValue(file, new TypeReference<ArrayList<Korisnik>>() {
			});
			int i = 0;
			for (Korisnik k : sviKorisnici) {
				System.out.println("KORISNICI" + i+1);
				System.out.println(k.getKorisnickoIme());
				i++;
			}

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Korisnik k : sviKorisnici) {
			korisnici.put(k.getKorisnickoIme(), k);
		}		
	}
	
	public static boolean upisiKorisnika(Korisnik korisnik) {

		
		if(korisnici.containsKey(korisnik.getKorisnickoIme())) {
			return false;
		}
		if(korisnik.getKorisnickoIme() == null || korisnik.getKorisnickoIme() == "") {
			return false;
		}
		if(korisnik.getLozinka() == null || korisnik.getLozinka() == "") {
			return false;
		}
//		if(korisnik.getDatumRodjenja() == null) {
//			return false;
//		}
		if(korisnik.getIme() == null || korisnik.getIme() == "") {
			return false;
		}
		if(korisnik.getPrezime() == null || korisnik.getPrezime() == "") {
			return false;
		}
		
		korisnik.setTipKupca(new NormalanKupac());
		korisnik.setIdKorisnika(Integer.toString(korisnici.size()));
		korisnik.setUloga("kupac");
		ArrayList<Korisnik> sviKorisnici = new ArrayList<Korisnik>();
		for (Korisnik k : korisnici.values()) {
			sviKorisnici.add(k);
		}
		sviKorisnici.add(korisnik);

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new FileOutputStream(putanja), sviKorisnici);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public static Korisnik find(String username, String password) {
		if (!korisnici.containsKey(username)) {
			return null;
		}
		Korisnik korisnik = korisnici.get(username);
		if (!korisnik.getLozinka().equals(password)) {
			return null;
		}
		return korisnik;
	}
	


	
}
