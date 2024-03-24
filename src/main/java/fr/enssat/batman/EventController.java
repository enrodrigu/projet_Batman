package fr.enssat.batman;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.enssat.batman.evenement.Evenement;
import fr.enssat.batman.evenement.EvenementsDAO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/events")
public class EventController {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String calendar() {
		EvenementsDAO eventsDAO = new EvenementsDAO();
		List<Evenement> calendar = eventsDAO.getAllEvents();
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String json = gson.toJson(calendar);
		
		return json;
	}
	
	@GET
	@Path("/{date}/{dj_name}")
	@Produces(MediaType.TEXT_PLAIN)
	public String event(@PathParam("date") String date,
						@PathParam("dj_name") String dj_name) {
		return "Évènement récupéré";
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response addevent(@FormParam("date") String date, 
							 @FormParam("h_debut") int h_debut,
							 @FormParam("h_fin") int h_fin,
							 @FormParam("dj_name") String dj_name,
							 @FormParam("place_id") int place_id) {
		
		//Creation de l'event et insertion dans la bdd grace à la DAO
		
		String responseMsg = "Évènement créé";
		return Response.ok().entity(responseMsg).build();
	}
}
