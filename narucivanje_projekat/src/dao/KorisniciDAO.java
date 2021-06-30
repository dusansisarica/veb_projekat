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
import beans.KorisnikRegistracija;
import beans.Kupac;
import beans.NormalanKupac;
import dto.KorisnikUlogaDTO;


public class KorisniciDAO {

	private static HashMap<String, KorisnikRegistracija> korisnici = new HashMap<String, KorisnikRegistracija>();
	private static String putanja = System.getProperty("catalina.base") + File.separator + "Datoteke" + File.separator + "korisnici.json";
	
	public KorisniciDAO(){
		File podaciDir = new File(System.getProperty("catalina.base") + File.separator + "Datoteke");
		if (!podaciDir.exists()) {
			podaciDir.mkdir();
		}
		//this.users = new LinkedHashMap<String, User>();
		
		// UNCOMMENT IF YOU WANT TO ADD MOCKUP DATA TO FILE addMockupData();
	}

	public KorisniciDAO(String contextPath) {
		ucitajKorisnike(contextPath);
	}

	private void ucitajKorisnike(String contextPath) {
		// TODO Auto-generated method stub
		ObjectMapper objectMapper = new ObjectMapper();

		File file = new File(putanja);

		ArrayList<KorisnikRegistracija> sviKorisnici = new ArrayList<KorisnikRegistracija>();
		try {

			sviKorisnici = objectMapper.readValue(file, new TypeReference<ArrayList<KorisnikRegistracija>>() {
			});


		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (KorisnikRegistracija k : sviKorisnici) {
			korisnici.put(k.getKorisnickoIme(), k);
		}		
	}
	
	public static boolean upisiKorisnika(KorisnikUlogaDTO korisnik) {
		
		if(korisnici.containsKey(korisnik.getKorisnikRegistracija().getKorisnickoIme())) {
			return false;
		}
		if(korisnik.getKorisnikRegistracija().getKorisnickoIme() == null || korisnik.getKorisnikRegistracija().getKorisnickoIme() == "") {
			return false;
		}
		if(korisnik.getKorisnikRegistracija().getLozinka() == null || korisnik.getKorisnikRegistracija().getLozinka() == "") {
			return false;
		}
		
		korisnik.getKorisnikRegistracija().setId(Integer.toString(korisnici.size()));
		ArrayList<KorisnikRegistracija> sviKorisnici = new ArrayList<KorisnikRegistracija>();
		for (KorisnikRegistracija k : korisnici.values()) {
			sviKorisnici.add(k);
		}
		sviKorisnici.add(korisnik.getKorisnikRegistracija());
		korisnici.put(korisnik.getKorisnikRegistracija().getKorisnickoIme(), korisnik.getKorisnikRegistracija());

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new FileOutputStream(putanja), sviKorisnici);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (korisnik.getKorisnikRegistracija().getUloga().equals("kupac")) {
			return KupacDAO.upisiKupca(korisnik);
		}
		if (korisnik.getKorisnikRegistracija().getUloga().equals("menadzer")) {
			return MenadzeriDAO.upisiMenadzera(korisnik);
		}
		if (korisnik.getKorisnikRegistracija().getUloga().equals("dostavljac")) {
			return DostavljaciDAO.upisiDostavljace(korisnik);
		}
		return true;
	}
	
	public static Korisnik find(KorisnikRegistracija korisnik) {
		if (!korisnici.containsKey(korisnik.getKorisnickoIme())) {
			return null;
		}
		KorisnikRegistracija korisnikReg = korisnici.get(korisnik.getKorisnickoIme());
		if (!korisnikReg.getLozinka().equals(korisnik.getLozinka())) {
			return null;
		}
		
		Korisnik k = new Korisnik();
		switch (korisnikReg.getUloga()) {
		case "kupac":
			k = KupacDAO.nadjiKupca(korisnikReg.getId());
			break;
		case "admin":
			k = AdminDAO.nadjiAdmina(korisnikReg.getId());
			break;
		case "menadzer":
			k = MenadzeriDAO.nadjiMenadzera(korisnikReg.getId());
			break;
		case "dostavljac":
			k = DostavljaciDAO.nadjiDostavljaca(korisnikReg.getId());
			break;
		}
		
		return k;
	}


	
}
