package dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Admin;
import beans.Kupac;
import beans.Menadzer;
import beans.NormalanKupac;
import dto.KorisnikUlogaDTO;

public class AdminDAO {
	private static HashMap<String, Admin> admini = new HashMap<String, Admin>();
	private static String putanja = System.getProperty("catalina.base") + File.separator + "Datoteke" + File.separator + "admini.json";
	
	public AdminDAO(String contextPath) {
		ucitajAdmine(contextPath);
	}

	private void ucitajAdmine(String contextPath) {
		// TODO Auto-generated method stub
		ObjectMapper objectMapper = new ObjectMapper();

		File file = new File(putanja);

		ArrayList<Admin> sviAdmini = new ArrayList<Admin>();
		try {

			sviAdmini = objectMapper.readValue(file, new TypeReference<ArrayList<Admin>>() {
			});

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Admin a : sviAdmini) {
			admini.put(a.getIdKorisnika(), a);
		}		

	}
	
	public static void izmeniAdmina(Admin admin) {
		admini.replace(admin.getIdKorisnika(), admin);
		
		List<Admin> listaAdmin = new ArrayList<Admin>();
		for(Admin a : admini.values()) {
			listaAdmin.add(a);
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new FileOutputStream(putanja), admini);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Admin nadjiAdmina(String id) {
		return admini.get(id);
	}

	
	

}
