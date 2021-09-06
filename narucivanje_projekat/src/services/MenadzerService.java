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

import beans.Menadzer;
import beans.Restoran;
import dao.AdminDAO;
import dao.DostavljaciDAO;
import dao.KorisniciDAO;
import dao.KupacDAO;
import dao.MenadzeriDAO;
import dao.RestoraniDAO;
import dto.KorisnikUlogaDTO;
import dto.RestoranLokacijaDTO;

@Path("/menadzeri")
public class MenadzerService {
	@Context
	ServletContext ctx;

	public MenadzerService() {
		
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
	public Response napraviMenadzera(KorisnikUlogaDTO korisnik, @Context HttpServletRequest request) {
		if(KorisniciDAO.upisiKorisnika(korisnik)) {
			return Response.status(200).build();
		}
		else {
			return Response.status(400).build();
		}

	}
	
	@GET
	@Path("/slobodni-menadzeri")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Menadzer> slobodniMenadzeri(@Context HttpServletRequest request){
		return MenadzeriDAO.dobaviSlobodneMenadzere();
	}
	
	@GET
	@Path("/pronadjiMenadzera/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Menadzer pronadjiMenadzera(@PathParam("id") String id) {
		MenadzeriDAO dao = (MenadzeriDAO) ctx.getAttribute("menadzeriDAO");
		System.out.println("Trazim menadzera sa id:" + id);
		return dao.nadjiMenadzera(id);
	}
	
	
	
}
