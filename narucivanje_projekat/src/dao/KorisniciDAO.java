package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Dostavljac;
import beans.Korisnik;
import beans.KorisnikRegistracija;
import beans.Kupac;
import beans.Menadzer;
import beans.NormalanKupac;
import beans.Porudzbina;
import dto.KorisnikSaUlogom;
import dto.KorisnikUlogaDTO;


public class KorisniciDAO {

	private static HashMap<String, KorisnikRegistracija> korisnici = new HashMap<String, KorisnikRegistracija>();
	private static String putanja = System.getProperty("catalina.base") + File.separator + "Datoteke" + File.separator + "korisnici.json";
	//private static String putanja = ".." + File.separator + "Datoteke" + File.separator + "korisnici.json";
	//private static String putanja = KorisniciDAO.class.getProtectionDomain().getCodeSource().getLocation().toString();
	//private static String putanja = "Datoteke" + File.separator + "korisnici.json";
	
	public KorisniciDAO(){
		File podaciDir = new File(System.getProperty("catalina.base") + File.separator + "Datoteke");
		if (!podaciDir.exists()) {
			podaciDir.mkdir();
		}

	}

	public KorisniciDAO(String contextPath) {
		ucitajKorisnike(contextPath);
	}

	private void ucitajKorisnike(String contextPath) {
		// TODO Auto-generated method stub
		ObjectMapper objectMapper = new ObjectMapper();

		File file = new File(putanja);
		//File file = new File(contextPath + "Datoteke/korisnici.json");

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
		
		korisnik.getKorisnik().setObrisan(false);
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
	
	public static List<KorisnikSaUlogom> dobaviSveKorisnike(){
		List<KorisnikSaUlogom> sviKorisnici = new ArrayList<KorisnikSaUlogom>();
		for(KorisnikRegistracija kr : korisnici.values()) {
			if (kr.getUloga().equals("kupac")) {
				sviKorisnici.add(new KorisnikSaUlogom(KupacDAO.nadjiKupca(kr.getId()), "kupac"));
			}
			else if(kr.getUloga().equals("menadzer")) {
				sviKorisnici.add(new KorisnikSaUlogom(MenadzeriDAO.nadjiMenadzera(kr.getId()), "menadzer"));
			}
			else if(kr.getUloga().equals("dostavljac")) {
				sviKorisnici.add(new KorisnikSaUlogom(DostavljaciDAO.nadjiDostavljaca(kr.getId()), "dostavljac"));
			}
			else if(kr.getUloga().equals("admin")) {
				sviKorisnici.add(new KorisnikSaUlogom(AdminDAO.nadjiAdmina(kr.getId()), "admin"));
			}

		}
		return sviKorisnici;
	}

	public static List<KorisnikSaUlogom> pronadjiKorisnikePoImenu(String ime){
		List<KorisnikSaUlogom> sviKorisnici = dobaviSveKorisnike();
		List<KorisnikSaUlogom> pronadjeniKorisnici = new ArrayList<KorisnikSaUlogom>();
		for(KorisnikSaUlogom ksu : sviKorisnici){
			if (ksu.getKorisnik().getIme().contains(ime)) {
				pronadjeniKorisnici.add(ksu);
			}
		}
		return pronadjeniKorisnici;
	}
	
	public static String pronadjiKorisnikuUlogu(String id) {
		List<KorisnikSaUlogom> sviKorisnici = dobaviSveKorisnike();
		for (KorisnikSaUlogom ksu : sviKorisnici) {
			if(ksu.getKorisnik() != null) {
				if (ksu.getKorisnik().getIdKorisnika().equals(id)) {
					return ksu.getUloga();
				}

			}
			
		}
		return null;
	}
	
	public static List<Kupac> dobaviSveKupce(){
		List<Kupac> kupci = new ArrayList<Kupac>();
		for (KorisnikSaUlogom krg : dobaviSveKorisnike()) {
			if (krg.getUloga() == "kupac") {
				kupci.add((Kupac)krg.getKorisnik());
			}
		}
		return kupci;
	}
	
	public static Kupac pronadjiKupcaPoId(String idKupca) {
		List<Kupac> kupci = dobaviSveKupce();
		for(Kupac kupac : kupci) {
			if (kupac.getIdKorisnika().equals(idKupca)) {
				return kupac;
			}
		}
		return null;
	}
	
	public static List<Menadzer> dobaviSveMenadzere(){
		List<Menadzer> menadzeri = new ArrayList<Menadzer>();
		for (KorisnikSaUlogom krg : dobaviSveKorisnike()) {
			if (krg.getUloga() == "menadzer") {
				menadzeri.add((Menadzer)krg.getKorisnik());
			}
		}
		return menadzeri;
	}
	
	public static Menadzer pronadjiMenadzeraPoId(String idMenadzera) {
		List<Menadzer> menadzeri = dobaviSveMenadzere();
		for(Menadzer menadzer : menadzeri) {
			if (menadzer.getIdKorisnika().equals(idMenadzera)) {
				return menadzer;
			}
		}
		return null;
	}
	
	public static List<Dostavljac> dobaviSveDostavljace(){
		List<Dostavljac> dostavljaci = new ArrayList<Dostavljac>();
		for (KorisnikSaUlogom krg : dobaviSveKorisnike()) {
			if (krg.getUloga() == "dostavljac") {
				dostavljaci.add((Dostavljac)krg.getKorisnik());
			}
		}
		return dostavljaci;
	}

	public static Dostavljac pronadjiDostavljacaPoId(String idDostavljaca) {
		List<Dostavljac> dostavljaci = dobaviSveDostavljace();
		for(Dostavljac dostavljac : dostavljaci) {
			if (dostavljac.getIdKorisnika().equals(idDostavljaca)) {
				return dostavljac;
			}
		}
		return null;
	}
	
	public static void upisiKorisnike() {
		List<KorisnikRegistracija> sviKorisnici = new ArrayList<>();
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new FileOutputStream(putanja), sviKorisnici);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}





	
}
