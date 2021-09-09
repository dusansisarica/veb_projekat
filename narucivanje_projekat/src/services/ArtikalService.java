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
import dao.AdminDAO;
import dao.ArtikliDAO;
import dao.DostavljaciDAO;
import dao.KorisniciDAO;
import dao.KupacDAO;
import dao.MenadzeriDAO;
import dao.PorudzbineDAO;
import dto.KorisnikUlogaDTO;

@Path("/artikli")
public class ArtikalService {
	@Context
	ServletContext ctx;

	public ArtikalService() {
		
	}

	@PostConstruct
	// ctx polje je null u konstruktoru, mora se pozvati nakon konstruktora (@PostConstruct anotacija)
	public void init() {
		// Ovaj objekat se instancira vi�e puta u toku rada aplikacije
		// Inicijalizacija treba da se obavi samo jednom
		if (ctx.getAttribute("artikliDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("artikliDAO", new ArtikliDAO(contextPath));
		}
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response napraviArtikal(Artikal artikal, @Context HttpServletRequest request) {
		if(ArtikliDAO.upisiArtikal(artikal)) {
			return Response.status(200).build();
		}
		else {
			return Response.status(400).build();
		}

	}
	
}

