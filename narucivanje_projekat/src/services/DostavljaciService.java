package services;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.AdminDAO;
import dao.DostavljaciDAO;
import dao.KorisniciDAO;
import dao.KupacDAO;
import dao.MenadzeriDAO;
import dto.KorisnikUlogaDTO;

@Path("/dostavljaci")
public class DostavljaciService {
	@Context
	ServletContext ctx;

	public DostavljaciService() {
		
	}

	@PostConstruct
	// ctx polje je null u konstruktoru, mora se pozvati nakon konstruktora (@PostConstruct anotacija)
	public void init() {
		// Ovaj objekat se instancira vi�e puta u toku rada aplikacije
		// Inicijalizacija treba da se obavi samo jednom
		if (ctx.getAttribute("menadzeriDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("menadzeriDAO", new MenadzeriDAO(contextPath));
		}
		if (ctx.getAttribute("korisniciDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("korisniciDAO", new KorisniciDAO(contextPath));
		}
		if (ctx.getAttribute("kupciDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("kupciDAO", new KupacDAO(contextPath));
		}
		if (ctx.getAttribute("adminiDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("adminiDAO", new AdminDAO(contextPath));
		}
		if (ctx.getAttribute("dostavljaciDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("dostavljaciDAO", new DostavljaciDAO(contextPath));
		}
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response napraviDostavljaca(KorisnikUlogaDTO korisnik, @Context HttpServletRequest request) {
		if(KorisniciDAO.upisiKorisnika(korisnik)) {
			return Response.status(200).build();
		}
		else {
			return Response.status(400).build();
		}

	}
}
