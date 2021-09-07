package services;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;
import java.util.Objects;

import beans.Admin;
import beans.Korisnik;

import beans.KorisnikRegistracija;
import beans.Kupac;
import beans.Menadzer;
import dao.AdminDAO;
import dao.DostavljaciDAO;
import dao.KorisniciDAO;
import dao.KupacDAO;
import dao.MenadzeriDAO;
import dto.KorisnikSaUlogom;
import dto.KorisnikUlogaDTO;

@Path("")
public class LoginService {
	
	@Context
	ServletContext ctx;
	
	public LoginService() {
		
	}
	
	@PostConstruct
	// ctx polje je null u konstruktoru, mora se pozvati nakon konstruktora (@PostConstruct anotacija)
	public void init() {
		// Ovaj objekat se instancira vie puta u toku rada aplikacije
		// Inicijalizacija treba da se obavi samo jednom
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
		if (ctx.getAttribute("menadzeriDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("menadzeriDAO", new MenadzeriDAO(contextPath));
		}

	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(KorisnikRegistracija korisnik, @Context HttpServletRequest request) {
		Korisnik ulogovaniKorisnik = KorisniciDAO.find(korisnik);
		if (ulogovaniKorisnik == null) {
			return Response.status(400).build();
		}
		KorisnikSaUlogom korisnikUlogovani = new KorisnikSaUlogom(ulogovaniKorisnik, 
				KorisniciDAO.pronadjiKorisnikuUlogu(ulogovaniKorisnik.getIdKorisnika()));
		
		if (ulogovaniKorisnik.getClass() == Kupac.class){
			request.getSession().setAttribute("user", korisnikUlogovani);
			return Response.status(200).entity("/").build();
		}
		else if (ulogovaniKorisnik.getClass() == Admin.class){
			request.getSession().setAttribute("user", korisnikUlogovani);
			return Response.status(200).entity("/pocetna/admin").build();
		}
		else if (ulogovaniKorisnik.getClass() == Menadzer.class){
			request.getSession().setAttribute("user", korisnikUlogovani);
			return Response.status(200).entity("/pocetna/menadzer").build();
		}
		return Response.status(400).build();
	}
	
	
	
	@POST
	@Path("/logout")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void logout(@Context HttpServletRequest request) {
		request.getSession().invalidate();
	}
	
	@GET
	@Path("/currentUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public KorisnikSaUlogom login(@Context HttpServletRequest request) {
		return (KorisnikSaUlogom) request.getSession().getAttribute("user");
	}
	
	@POST
	@Path("/registracija")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registrujKorisnika(KorisnikUlogaDTO korisnik, @Context HttpServletRequest request) {
		if(KorisniciDAO.upisiKorisnika(korisnik)) {
			return Response.status(200).build();
		}
		else {
			return Response.status(400).build();
		}
	}
	
	@GET
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<KorisnikSaUlogom> dobaviSveKorisnike(@Context HttpServletRequest request) {
		return KorisniciDAO.dobaviSveKorisnike();
	}

	@GET
	@Path("/pretraga")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<KorisnikSaUlogom> pretraziKorisnika(@QueryParam("ime") String ime){
//		if (ime == null || ime == "null" || ime.isEmpty()) {
//			return KorisniciDAO.dobaviSveKorisnike();
//		}
		return KorisniciDAO.pronadjiKorisnikePoImenu(ime);
	}

}
