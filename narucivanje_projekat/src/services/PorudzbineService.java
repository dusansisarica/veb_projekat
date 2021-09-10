package services;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Artikal;
import beans.Porudzbina;
import beans.Restoran;
import dao.ArtikliDAO;
import dao.PorudzbineDAO;
import dao.RestoraniDAO;
import dto.ArtikalKolicinaDTO;
import dto.PorudzbinaDTO;
import dto.PorudzbinaDostavljacDTO;
import dto.PorudzbinaDostavljacObjekatDTO;
import dto.PorudzbinaSaStatusomDTO;
import dto.PorudzbineDTO;

@Path("/porudzbine")
public class PorudzbineService {
	@Context
	ServletContext ctx;

	public PorudzbineService() {
		
	}
	
	@PostConstruct
	// ctx polje je null u konstruktoru, mora se pozvati nakon konstruktora (@PostConstruct anotacija)
	public void init() {
		// Ovaj objekat se instancira vi�e puta u toku rada aplikacije
		// Inicijalizacija treba da se obavi samo jednom
		if (ctx.getAttribute("porudzbineDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("porudzbineDAO", new PorudzbineDAO(contextPath));
		}
	}
	
	@POST
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response kreirajPorudzbinu(@PathParam("id") String id, @Context HttpServletRequest request) {
		if(PorudzbineDAO.kreirajPorudzbinu(id)) {
			return Response.status(200).build();
		}
		else {
			return Response.status(400).build();
		}

	}
	
	@GET
	@Path("/dobaviPorudzbinu/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Porudzbina dobaviPorudzbinu(@PathParam("id") String id) {
		System.out.println("ID JE:" + id);
		PorudzbineDAO dao = (PorudzbineDAO) ctx.getAttribute("porudzbineDAO");
		return dao.dobaviPorudzbinu(id);
	}
	
	@GET
	@Path("/dobaviPorudzbineRestorana/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Porudzbina dobaviPorudzbineRestorana(@PathParam("id") String id) {
		PorudzbineDAO dao = (PorudzbineDAO) ctx.getAttribute("porudzbineDAO");
		return dao.dobaviPorudzbinu(id);
	}
	
	@GET
	@Path("/dobaviPorudzbineKupca/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Porudzbina dobaviPorudzbineKupca(@PathParam("id") String id) {
		PorudzbineDAO dao = (PorudzbineDAO) ctx.getAttribute("porudzbineDAO");
		return dao.dobaviPorudzbinu(id);
	}
	
	@POST
	@Path("/dodajArtikalUPorudzbinu")
	@Produces(MediaType.APPLICATION_JSON)
	public void dodajArtikalUPorudzbinu(PorudzbineDTO porudzbineDto) {
		PorudzbineDAO.dodajArtikalUPorudzbinu(porudzbineDto);
	}
	
	@GET
	@Path("/dobaviKorisnikovuKorpu/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ArtikalKolicinaDTO> dobaviSadrzajKorpe(@PathParam("id") String id) {
		return PorudzbineDAO.dobaviSadrzajKorpe(id);
	}
	
	@GET
	@Path("/dobaviUkupnuCenuKorpe/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public double dobaviCenu(@PathParam("id") String id) {
		return PorudzbineDAO.dobaviCenu(id);
	}
	
	@GET
	@Path("/dobaviSvePorudzbineRestorana/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PorudzbinaDTO> dobaviNarudzbineZaRestoran(@PathParam("id") String id) {
		return PorudzbineDAO.dobaviNarudzbineZaRestoran(id);
	}
	
	@POST
	@Path("/odobriPorudzbinu/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response odobriPorudzbinu(@PathParam("id") String id, @Context HttpServletRequest request) {
		if(PorudzbineDAO.odobriPorudzbinu(id)) {
			return Response.status(200).build();
		}
		else {
			return Response.status(400).build();
		}

	}
	
	@POST
	@Path("/cekaDostavljaca/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cekaDostavljaca(@PathParam("id") String id, @Context HttpServletRequest request) {
		if(PorudzbineDAO.cekaDostavljaca(id)) {
			return Response.status(200).build();
		}
		else {
			return Response.status(400).build();
		}

	}
	
	@GET
	@Path("/dobaviSvePorudzbineKojeNemajuDostavljaca")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PorudzbinaDTO> dobaviPorudzbineBezDostavljaca() {
		return PorudzbineDAO.dobaviPorudzbineBezDostavljaca();
	}
	
	@POST
	@Path("/posaljiZahtevZaDostavljaca")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response posaljiZahtevMenadzeru(PorudzbinaDostavljacDTO dto) {
		if(PorudzbineDAO.posaljiZahtevMenadzeru(dto)) {
			return Response.status(200).build();
		}
		else {
			return Response.status(400).build();
		}
	}
	
	@GET
	@Path("/dobaviSvePorudzbineZaOdobrenjeDostavljaca/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PorudzbinaDostavljacObjekatDTO> dobaviSvePorudzbineZaOdobrenjeDostavljaca(@PathParam("id") String id) {
		return PorudzbineDAO.dobaviSvePorudzbineZaOdobrenjeDostavljaca(id);
	}
	
	@POST
	@Path("/odobriPorudzbinuDostavljacu")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response odobriPorudzbinuDostavljacu(PorudzbinaDostavljacDTO dto) {
		if(PorudzbineDAO.odobriPorudzbinuDostavljacu(dto)) {
			return Response.status(200).build();
		}
		else {
			return Response.status(400).build();
		}
	}
	
	@GET
	@Path("/dobaviPorudzbineZaKojeJeZaduzenDostavljac/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PorudzbinaDTO> dobaviPorudzbineZaKojeJeZaduzenDostavljac(@PathParam("id") String id) {
		return PorudzbineDAO.dobaviPorudzbineZaKojeJeZaduzenDostavljac(id);
	}
	
	@POST
	@Path("/dostavljenaPorudzbina")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response dostavljenaPorudzbina(PorudzbinaDostavljacDTO dto) {
		if(PorudzbineDAO.dostavljenaPorudzbina(dto)) {
			return Response.status(200).build();
		}
		else {
			return Response.status(400).build();
		}
	}
	
	@GET
	@Path("/dobaviSvePorudzbineKupca/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PorudzbinaSaStatusomDTO> dobaviSvePorudzbineKupca(@PathParam("id") String id) {
		return PorudzbineDAO.dobaviSvePorudzbineKupca(id);
	}
	
}
