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
import beans.Dostavljac;
import beans.Kupac;
import beans.Menadzer;
import beans.Porudzbina;
import beans.TipPorudzbine;
import dto.ArtikalKolicinaDTO;
import dto.PorudzbinaDTO;
import dto.PorudzbinaDostavljacDTO;
import dto.PorudzbinaDostavljacObjekatDTO;
import dto.PorudzbinaSaStatusomDTO;
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
													kupac.getKorpa().getCena(),  TipPorudzbine.obrada );

		porudzbinaUpis.setIdDostavljaca(null);
		kupac.setBrojSakupljenihBodova(kupac.getBrojSakupljenihBodova() + (porudzbinaUpis.getCena()/1000 * 133));
		kupac.setKorpa(null);
		KupacDAO.kopirajKupcaIUpisi(kupac);
		
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
		if (kupac.getKorpa().getIdRestoran() != null) {
			if (!kupac.getKorpa().getIdRestoran().equals(ArtikliDAO.dobaviArtikalPrekoId(porudzbineDto.getArtikalId()).getRestoran())) {
				kupac.setKorpa(new Porudzbina());
			}
		}
		else {
			kupac.getKorpa().setIdRestoran(ArtikliDAO.dobaviArtikalPrekoId(porudzbineDto.getArtikalId()).getRestoran());
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

	public static List<PorudzbinaDTO> dobaviPorudzbineBezDostavljaca() {
		List<PorudzbinaDTO> porudzbineZaDostavljaca = new ArrayList<>();
		for (Porudzbina p : porudzbine.values()) {
			List<ArtikalKolicinaDTO> artikliUPorudzbini = new ArrayList<>();
			if (p.getTipPorudzbine() == TipPorudzbine.cekaDostavljaca) {
				for (String a : p.getArtikli().keySet()) {
					artikliUPorudzbini.add(new ArtikalKolicinaDTO(ArtikliDAO.dobaviArtikalPrekoId(a), p.getArtikli().get(a)));
				}
				porudzbineZaDostavljaca.add(new PorudzbinaDTO(artikliUPorudzbini, p.getIdPorudzbine()));
			}
		}
		return porudzbineZaDostavljaca;
	}

	public static boolean posaljiZahtevMenadzeru(PorudzbinaDostavljacDTO dto) {
		Porudzbina porudzbina = dobaviPorudzbinu(dto.getIdPorudzbine());
		List<String> dostavljaci;
		if (porudzbina.getZainteresovaniDostavljaci().size() == 0) {
			dostavljaci = new ArrayList<>();
		}
		else {
			dostavljaci = porudzbina.getZainteresovaniDostavljaci();
		}
		dostavljaci.add(dto.getIdDostavljaca());
		porudzbina.setZainteresovaniDostavljaci(dostavljaci);
		kopirajPorudzbinuIUpisi(porudzbina);
		return true;
	}

	public static List<PorudzbinaDostavljacObjekatDTO> dobaviSvePorudzbineZaOdobrenjeDostavljaca(String id) {
		List<Porudzbina> porudzbineSve = porudzbineSaZainteresovanimDostavljacima(id);
		List<PorudzbinaDostavljacObjekatDTO> por = new ArrayList<>();
		for (Porudzbina p : porudzbineSve) {
			List<Dostavljac> dostavljaci = new ArrayList<>();
			for (String idD : p.getZainteresovaniDostavljaci()) {
				dostavljaci.add(DostavljaciDAO.nadjiDostavljaca(idD));
			}
			por.add(new PorudzbinaDostavljacObjekatDTO(dostavljaci, p));
		}
		return por;
	}
	
	public static List<Porudzbina> porudzbineSaZainteresovanimDostavljacima(String id) {
		List<Porudzbina> porudzbineSve = new ArrayList<>();
		for (Porudzbina p : porudzbine.values()) {
			if (p.getZainteresovaniDostavljaci().size() > 0 && p.getIdRestoran().equals(id)) {
				porudzbineSve.add(p);
			}
		}
		
		return porudzbineSve;
	}

	public static boolean odobriPorudzbinuDostavljacu(PorudzbinaDostavljacDTO dto) {
		Porudzbina porudzbina = dobaviPorudzbinu(dto.getIdPorudzbine());
		porudzbina.setIdDostavljaca(dto.getIdDostavljaca());
		porudzbina.setTipPorudzbine(TipPorudzbine.uTransportu);
		porudzbina.setZainteresovaniDostavljaci(new ArrayList<>());
		porudzbine.replace(porudzbina.getIdPorudzbine(), porudzbina);
		List<Porudzbina> svePorudzbine = new ArrayList<>();
		for (Porudzbina p : porudzbine.values()) {
			svePorudzbine.add(p);
		}
		Dostavljac dostavljac = KorisniciDAO.pronadjiDostavljacaPoId(dto.getIdDostavljaca());
		dostavljac.getPorudzbineZaDostavljanje().add(porudzbina);
		DostavljaciDAO.kopirajIupisiDostavljaca(dostavljac);
		upisiPorudzbine(svePorudzbine);
		return true;
	}

	public static List<PorudzbinaDTO> dobaviPorudzbineZaKojeJeZaduzenDostavljac(String id) {
		Dostavljac dostavljac = KorisniciDAO.pronadjiDostavljacaPoId(id);
		List<PorudzbinaDTO> dostavljacevePorudzbine = new ArrayList<>();
		for (Porudzbina n : dostavljac.getPorudzbineZaDostavljanje()) {
			List<ArtikalKolicinaDTO> artikalKolicina = new ArrayList<>();
			for (String s : n.getArtikli().keySet()) {
				artikalKolicina.add(new ArtikalKolicinaDTO(ArtikliDAO.dobaviArtikalPrekoId(s), n.getArtikli().get(s)));
			}
			dostavljacevePorudzbine.add(new PorudzbinaDTO(artikalKolicina, n.getIdPorudzbine()));
		}
		return dostavljacevePorudzbine;
	}

	public static boolean dostavljenaPorudzbina(PorudzbinaDostavljacDTO dto) {
		Porudzbina porudzbina = dobaviPorudzbinu(dto.getIdPorudzbine());
		Dostavljac dostavljac = KorisniciDAO.pronadjiDostavljacaPoId(dto.getIdDostavljaca());
		List<Porudzbina> porudzbine1 = new ArrayList<>();

		for (Porudzbina p : dostavljac.getPorudzbineZaDostavljanje()) {
			porudzbine1.add(p);
		}
		
		for (Porudzbina p : dostavljac.getPorudzbineZaDostavljanje()) {
			if (p.getIdPorudzbine().equals(porudzbina.getIdPorudzbine())) {
				porudzbine1.remove(p);
			}
		}
		dostavljac.setPorudzbineZaDostavljanje(porudzbine1);
		//dostavljac.getPorudzbineZaDostavljanje().remove(porudzbina);
		porudzbina.setTipPorudzbine(TipPorudzbine.dostavljena);
		DostavljaciDAO.kopirajIupisiDostavljaca(dostavljac);
		kopirajPorudzbinuIUpisi(porudzbina);
		return true;
	}
	
	public static List<Porudzbina> dobaviSvePorudzbine(){
		List<Porudzbina> svePorudzbine = new ArrayList<>();
		for (Porudzbina p : porudzbine.values()) {
			svePorudzbine.add(p);
		}
		return svePorudzbine;
	}

	public static List<PorudzbinaSaStatusomDTO> dobaviSvePorudzbineKupca(String id) {
		List<Porudzbina> svePorudzbine = dobaviSvePorudzbine();
		List<PorudzbinaSaStatusomDTO> kupcevePorudzbine = new ArrayList<>();
		for (Porudzbina p : svePorudzbine) {
			if (p.getIdKupac().equals(id)) {
				List<ArtikalKolicinaDTO> artikliKolicina = new ArrayList<>();
				for (String s : p.getArtikli().keySet()) {
					artikliKolicina.add(new ArtikalKolicinaDTO(ArtikliDAO.dobaviArtikalPrekoId(s), p.getArtikli().get(s)));
				}
				kupcevePorudzbine.add(new PorudzbinaSaStatusomDTO(artikliKolicina, p));
			}
		}
		return kupcevePorudzbine;
	}
	


	


}
