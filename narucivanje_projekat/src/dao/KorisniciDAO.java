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
import beans.Admin;
import beans.Korisnik;
import beans.KorisnikRegistracija;
import beans.Kupac;
import beans.Menadzer;
import beans.NormalanKupac;
import beans.Porudzbina;
import dto.AdminDTO;
import dto.DostavljacDTO;
import dto.KorisnikSaUlogom;
import dto.KorisnikUlogaDTO;
import dto.KupacDTO;
import dto.MenadzerDTO;


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
	
	public static void izmeniKupca(KupacDTO kupacDto) {
		KorisnikRegistracija korisnikRegistracija = new KorisnikRegistracija();
		for(KorisnikRegistracija kr : korisnici.values()) {
			if(kr.getId().equals(kupacDto.getIdKorisnika())) {
				korisnikRegistracija = kr;
			}
		}
		korisnikRegistracija.setKorisnickoIme(kupacDto.getKorisnickoIme());
		korisnici.replace(kupacDto.getIdKorisnika(), korisnikRegistracija);
		
		List<KorisnikRegistracija> korisniciRegistracija = new ArrayList<KorisnikRegistracija>();
		for(KorisnikRegistracija kr : korisnici.values()) {
			korisniciRegistracija.add(kr);
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new FileOutputStream(putanja), korisniciRegistracija);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Kupac kupac = KupacDAO.nadjiKupca(korisnikRegistracija.getId());
		kupac.setIme(kupacDto.getIme());
		kupac.setPrezime(kupacDto.getPrezime());
		kupac.setDatumRodjenja(kupacDto.getDatumRodjenja());
		KupacDAO.izmeniKupca(kupac);
		
	}
	
	public static void izmeniDostavljaca(DostavljacDTO dostavljacDto) {
		KorisnikRegistracija korisnikRegistracija = new KorisnikRegistracija();
		for(KorisnikRegistracija kr : korisnici.values()) {
			if(kr.getId().equals(dostavljacDto.getIdKorisnika())) {
				korisnikRegistracija = kr;
			}
		}
		korisnikRegistracija.setKorisnickoIme(dostavljacDto.getKorisnickoIme());
		korisnici.replace(dostavljacDto.getIdKorisnika(), korisnikRegistracija);
		
		List<KorisnikRegistracija> korisniciRegistracija = new ArrayList<KorisnikRegistracija>();
		for(KorisnikRegistracija kr : korisnici.values()) {
			korisniciRegistracija.add(kr);
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new FileOutputStream(putanja), korisniciRegistracija);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Dostavljac dostavljac = DostavljaciDAO.nadjiDostavljaca(korisnikRegistracija.getId());
		dostavljac.setIme(dostavljacDto.getIme());
		dostavljac.setPrezime(dostavljacDto.getPrezime());
		dostavljac.setDatumRodjenja(dostavljacDto.getDatumRodjenja());
		DostavljaciDAO.izmeniDostavljaca(dostavljac);
		
	}
	
	public static void izmeniMenadzera(MenadzerDTO menadzerDto) {
		KorisnikRegistracija korisnikRegistracija = new KorisnikRegistracija();
		for(KorisnikRegistracija kr : korisnici.values()) {
			if(kr.getId().equals(menadzerDto.getIdKorisnika())) {
				korisnikRegistracija = kr;
			}
		}
		korisnikRegistracija.setKorisnickoIme(menadzerDto.getKorisnickoIme());
		korisnici.replace(menadzerDto.getIdKorisnika(), korisnikRegistracija);
		
		List<KorisnikRegistracija> korisniciRegistracija = new ArrayList<KorisnikRegistracija>();
		for(KorisnikRegistracija kr : korisnici.values()) {
			korisniciRegistracija.add(kr);
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new FileOutputStream(putanja), korisniciRegistracija);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Menadzer menadzer = MenadzeriDAO.nadjiMenadzera(korisnikRegistracija.getId());
		menadzer.setIme(menadzerDto.getIme());
		menadzer.setPrezime(menadzerDto.getPrezime());
		menadzer.setDatumRodjenja(menadzerDto.getDatumRodjenja());
		MenadzeriDAO.izmeniMenadzera(menadzer);
		
	}
	
	public static void upisiSveKorisnike() {
		ArrayList<KorisnikRegistracija> sviKorisnici = new ArrayList<KorisnikRegistracija>();
		for (KorisnikRegistracija k : korisnici.values()) {
			sviKorisnici.add(k);
		}
		List<Menadzer> menadzeri = new ArrayList<>();
		List<Dostavljac> dostavljaci = new ArrayList<>();
		List<Kupac> kupci = new ArrayList<>();
		List<Admin> admini = new ArrayList<>();

		for(KorisnikRegistracija k : sviKorisnici) {
			if (k.getUloga().equals("kupac")) {
				kupci.add(KupacDAO.nadjiKupca(k.getId()));
			}
			else if (k.getUloga().equals("menadzer")) {
				menadzeri.add(MenadzeriDAO.nadjiMenadzera(k.getId()));
			}
			else if (k.getUloga().equals("dostavljac")) {
				dostavljaci.add(DostavljaciDAO.nadjiDostavljaca(k.getId()));
			}
		}
		
		MenadzeriDAO.upisiMenadzere(menadzeri);
		DostavljaciDAO.upisiDostavljace(dostavljaci);
		KupacDAO.upisiKupce(kupci);
				
	}
	
	public static void izmeniAdmina(AdminDTO adminDto) {
		KorisnikRegistracija korisnikRegistracija = new KorisnikRegistracija();
		for(KorisnikRegistracija kr : korisnici.values()) {
			if(kr.getId().equals(adminDto.getIdKorisnika())) {
				korisnikRegistracija = kr;
			}
		}
		korisnikRegistracija.setKorisnickoIme(adminDto.getKorisnickoIme());
		korisnici.replace(adminDto.getIdKorisnika(), korisnikRegistracija);
		
		List<KorisnikRegistracija> korisniciRegistracija = new ArrayList<KorisnikRegistracija>();
		for(KorisnikRegistracija kr : korisnici.values()) {
			korisniciRegistracija.add(kr);
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new FileOutputStream(putanja), korisniciRegistracija);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Admin admin = AdminDAO.nadjiAdmina(korisnikRegistracija.getId());
		admin.setIme(adminDto.getIme());
		admin.setPrezime(adminDto.getPrezime());
		admin.setDatumRodjenja(adminDto.getDatumRodjenja());
		AdminDAO.izmeniAdmina(admin);
		
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
	
	public static KorisnikRegistracija dobaviKorisnikRegistracija(String id) {
		for(KorisnikRegistracija kr : korisnici.values()) {
			if(kr.getId().equals(id)) {
				return kr;
			}
		}
		return null;
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
		korisnik.getKorisnik().setBanovan(false);
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

	public static boolean obrisiKorisnika(String id) {
		List<KorisnikSaUlogom> sviKorisnici = dobaviSveKorisnike();
		for(KorisnikSaUlogom k : sviKorisnici) {
			if (k.getKorisnik().getIdKorisnika().equals(id)) {
				k.getKorisnik().setObrisan(true);
				korisnici.replace(dobaviKorisnikRegistracija(k.getKorisnik().getIdKorisnika()).getKorisnickoIme(), dobaviKorisnikRegistracija(k.getKorisnik().getIdKorisnika()));
			}
		}
		upisiSveKorisnike();
		return true;
	}

	public static boolean banujKorisnika(String id) {
		List<KorisnikSaUlogom> sviKorisnici = dobaviSveKorisnike();
		for(KorisnikSaUlogom k : sviKorisnici) {
			if (k.getKorisnik().getIdKorisnika().equals(id)) {
				if(k.getUloga() == "admin") {
					return false;
				}
				k.getKorisnik().setBanovan(true);
				korisnici.replace(dobaviKorisnikRegistracija(k.getKorisnik().getIdKorisnika()).getKorisnickoIme(), dobaviKorisnikRegistracija(k.getKorisnik().getIdKorisnika()));
			}
		}
		upisiSveKorisnike();
		return true;

	}

	public static KorisnikSaUlogom dobaviKorisnika(String id) {
		return KorisniciDAO.dobaviKorisnika(id);
	}





	
}
