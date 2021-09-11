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

import beans.Komentar;
import dao.KomentariDAO;
import dao.PorudzbineDAO;
import dto.KupacRestoranDTO;
import dto.PorudzbinaDTO;
import dto.PorudzbinaDostavljacDTO;

@Path("/komentari")
public class KomentariService {
	@Context
	ServletContext ctx;

	public KomentariService() {
		
	}

	@PostConstruct
	// ctx polje je null u konstruktoru, mora se pozvati nakon konstruktora (@PostConstruct anotacija)
	public void init() {
		// Ovaj objekat se instancira vi�e puta u toku rada aplikacije
		// Inicijalizacija treba da se obavi samo jednom
		if (ctx.getAttribute("komentariDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("komentariDAO", new KomentariDAO(contextPath));
		}
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Komentar> dobaviSveKomentare() {
		return KomentariDAO.dobaviSveKomentare();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response kreirajKomentar(KupacRestoranDTO komentar) {
		if(KomentariDAO.upisiKomentar(komentar)) {
			return Response.status(200).build();
		}
		else {
			return Response.status(400).build();
		}
	}
	
	@GET
	@Path("/dobaviKomentareZaRestoran/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Komentar> dobaviKomentareZaRestoran(@PathParam("id") String id) {
		return KomentariDAO.dobaviKomentareZaRestoran(id);
	}
	
	@POST
	@Path("/odobriKomentar/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response odobriKomentar(@PathParam("id") String id, @Context HttpServletRequest request) {
		if(KomentariDAO.odobriKomentar(id)) {
			return Response.status(200).build();
		}
		else {
			return Response.status(400).build();
		}
	}
	
	@GET
	@Path("/izracunajProsecnuOcenuRestorana/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public double izracunajProsecnuOcenuRestorana(@PathParam("id") String id) {
		return KomentariDAO.izracunajProsecnuOcenuRestorana(id);
	}
	
	@GET
	@Path("/dobaviOdobreneKomentareZaRestoran/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Komentar> dobaviOdobreneKomentareZaRestoran(@PathParam("id") String id) {
		return KomentariDAO.dobaviOdobreneKomentareZaRestoran(id);
	}
}
