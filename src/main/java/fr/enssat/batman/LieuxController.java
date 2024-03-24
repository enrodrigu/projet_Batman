package fr.enssat.batman;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.enssat.batman.lieu.Lieu;
import fr.enssat.batman.lieu.LieuxDAO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/lieux")
public class LieuxController {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String tous_les_lieux() {
		LieuxDAO lieuxDAO = new LieuxDAO();
		List<Lieu> tous_les_lieux = lieuxDAO.getAllPlaces();
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String json = gson.toJson(tous_les_lieux);
		
		return json;
	}
}
