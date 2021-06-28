package services;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Korisnik;
import beans.Lokacija;
import beans.Restoran;
import beans.TipRestorana;
import dao.KorisniciDAO;
import dao.RestoraniDAO;
import dto.RestoranLokacijaDTO;

@Path("/restorani")
public class RestoraniService {
	@Context
	ServletContext ctx;

	public RestoraniService() {
		
	}

	@PostConstruct
	// ctx polje je null u konstruktoru, mora se pozvati nakon konstruktora (@PostConstruct anotacija)
	public void init() {
		// Ovaj objekat se instancira vi�e puta u toku rada aplikacije
		// Inicijalizacija treba da se obavi samo jednom
		if (ctx.getAttribute("restoraniDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("restoraniDAO", new RestoraniDAO(contextPath));
		}
	}
	 

	@GET
	@Path("/dobavi-tipove")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> dobaviTipoveRestorana(@Context HttpServletRequest request) {
		return RestoraniDAO.dobaviTipoveRestorana();
	}
	
	@GET
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Restoran> dobaviRestorane(@Context HttpServletRequest request) {
		return RestoraniDAO.dobaviSveRestorane();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response napraviRestoran(RestoranLokacijaDTO restoranLokacija, @Context HttpServletRequest request) {
		RestoraniDAO.napraviRestoran(restoranLokacija);
		return Response.status(200).build();
	}
}
