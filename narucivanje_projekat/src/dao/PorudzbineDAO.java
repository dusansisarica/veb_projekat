package dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.PathParam;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Artikal;
import beans.Kupac;
import beans.Menadzer;
import beans.Porudzbina;
import beans.TipPorudzbine;
import dto.ArtikalKolicinaDTO;
import dto.PorudzbinaDTO;
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
	
	public static boolean kreirajPorudzbinu(@PathParam("id") String id) {

				
		ArrayList<Porudzbina> svePorudzbine = new ArrayList<Porudzbina>();
		for (Porudzbina p : porudzbine.values()) {
			svePorudzbine.add(p);
		}
		Kupac kupac = KorisniciDAO.pronadjiKupcaPoId(id);
		Artikal a = ArtikliDAO.dobaviArtikalPrekoId(kupac.getKorpa().getArtikli().keySet().stream().findFirst().get());
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		
		Porudzbina porudzbinaUpis = new Porudzbina(Long.toString(generisiId()), a.getRestoran(),
													kupac.getKorpa().getArtikli(), kupac.getIdKorisnika(), date, 
													kupac.getKorpa().getCena(), TipPorudzbine.obrada );

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
	
	public static void upisiPorudzbine(List<Porudzbina> svePorudzbine) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new FileOutputStream(putanja), svePorudzbine);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static long generisiId() {
		return (System.currentTimeMillis()/ 10) % 10000000000L;
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
	
	public static List<ArtikalKolicinaDTO> dobaviSadrzajKorpe(String id) {
		Kupac kupac = KorisniciDAO.pronadjiKupcaPoId(id);
		List<ArtikalKolicinaDTO> artikliUKorpi = new ArrayList<ArtikalKolicinaDTO>();
		if (kupac.getKorpa() == null) {
			kupac.setKorpa(new Porudzbina());
		}
		if (kupac.getKorpa().getArtikli().size() != 0) {
			for (String art : kupac.getKorpa().getArtikli().keySet()) {
				artikliUKorpi.add(new ArtikalKolicinaDTO(ArtikliDAO.dobaviArtikalPrekoId(art), kupac.getKorpa().getArtikli().get(art)));
			}
		}
		
		return artikliUKorpi;
	}

	public static double dobaviCenu(String id) {
		// TODO Auto-generated method stub
		return KorisniciDAO.pronadjiKupcaPoId(id).getKorpa().getCena();
	}

	public static List<PorudzbinaDTO> dobaviNarudzbineZaRestoran(String id) {
		Menadzer menadzer =  KorisniciDAO.pronadjiMenadzeraPoId(id);
		List<PorudzbinaDTO> porudzbineRestorana = new ArrayList<PorudzbinaDTO>();
		for (Porudzbina p : porudzbine.values()) {
			if (p.getIdRestoran().equals(menadzer.getRestoranId())) {
				List<ArtikalKolicinaDTO> artikalKolicina = new ArrayList<ArtikalKolicinaDTO>();
				for (String key : p.getArtikli().keySet()) {
					artikalKolicina.add(new ArtikalKolicinaDTO(ArtikliDAO.dobaviArtikalPrekoId(key), p.getArtikli().get(key)));
				}
				porudzbineRestorana.add(new PorudzbinaDTO(artikalKolicina, p.getIdPorudzbine()));
			}
		}
		return porudzbineRestorana;
	}
	
	public static boolean odobriPorudzbinu(String id) {
		Porudzbina porudzbina = dobaviPorudzbinu(id);
		Porudzbina porudzbinaUpis = new Porudzbina(porudzbina.getIdPorudzbine(), porudzbina.getIdRestoran(),
				porudzbina.getArtikli(), porudzbina.getIdKupac(), porudzbina.getDatumVreme(), 
				porudzbina.getCena(), TipPorudzbine.uPripremi );
		kopirajPorudzbinuIUpisi(porudzbinaUpis);
		return true;
	}
	
	public static void kopirajPorudzbinuIUpisi(Porudzbina novaPorudzbina) {
		for (Porudzbina p : porudzbine.values()) {
			if (p.getIdPorudzbine().equals(novaPorudzbina.getIdPorudzbine())) {
				porudzbine.replace(p.getIdPorudzbine(), novaPorudzbina);
			}
		}
		List<Porudzbina> svePorudzbine = new ArrayList<Porudzbina>();
		for (Porudzbina p : porudzbine.values()) {
			svePorudzbine.add(p);
		}
		
		upisiPorudzbine(svePorudzbine);
	}

	public static boolean cekaDostavljaca(String id) {
		Porudzbina porudzbina = dobaviPorudzbinu(id);
		Porudzbina porudzbinaUpis = new Porudzbina(porudzbina.getIdPorudzbine(), porudzbina.getIdRestoran(),
				porudzbina.getArtikli(), porudzbina.getIdKupac(), porudzbina.getDatumVreme(), 
				porudzbina.getCena(), TipPorudzbine.cekaDostavljaca );
		kopirajPorudzbinuIUpisi(porudzbinaUpis);
		return true;
	}
}
