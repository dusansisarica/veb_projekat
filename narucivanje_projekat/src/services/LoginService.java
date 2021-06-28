package services;

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
import java.util.Objects;

import beans.Korisnik;
import dao.KorisniciDAO;

@Path("")
public class LoginService {
	
	@Context
	ServletContext ctx;
	
	public LoginService() {
		
	}
	
	@PostConstruct
	// ctx polje je null u konstruktoru, mora se pozvati nakon konstruktora (@PostConstruct anotacija)
	public void init() {
		// Ovaj objekat se instancira vi�e puta u toku rada aplikacije
		// Inicijalizacija treba da se obavi samo jednom
		if (ctx.getAttribute("korisniciDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("korisniciDAO", new KorisniciDAO(contextPath));
		}
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(Korisnik korisnik, @Context HttpServletRequest request) {
		KorisniciDAO korisniciDAO = (KorisniciDAO) ctx.getAttribute("korisniciDAO");
		Korisnik ulogovaniKorisnik = KorisniciDAO.find(korisnik.getKorisnickoIme(), korisnik.getLozinka());
		if (ulogovaniKorisnik == null) {
			return Response.status(400).build();
		}
		else if (ulogovaniKorisnik.getUloga().equals("kupac")){
			request.getSession().setAttribute("user", ulogovaniKorisnik);
			return Response.status(200).entity("/pocetna").build();
		}
		else if (ulogovaniKorisnik.getUloga().equals("admin")){
			request.getSession().setAttribute("user", ulogovaniKorisnik);
			return Response.status(200).entity("/pocetna/admin").build();
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
	public Korisnik login(@Context HttpServletRequest request) {
		return (Korisnik) request.getSession().getAttribute("user");
	}
	
	@POST
	@Path("/registracija")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registrujKorisnika(Korisnik korisnik, @Context HttpServletRequest request) {
		if(KorisniciDAO.upisiKorisnika(korisnik)) {
			return Response.status(200).entity("Uspe�no ste se registrovali!").build();
		}
		else {
			return Response.status(Response.Status.BAD_REQUEST).entity("Podaci nisu valjani").build();
		}
		
	}
}
