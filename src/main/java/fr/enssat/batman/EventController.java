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

@Path("/evenements")
public class EventController {
	EvenementsDAO eventsDAO = new EvenementsDAO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String calendar() {
		List<Evenement> calendar = eventsDAO.getAllEvents();
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String json = gson.toJson(calendar);
		
		return json;
	}
	
	@GET
	@Path("/{date}/{nomScene}")
	@Produces(MediaType.TEXT_PLAIN)
	public String event(@PathParam("date") String date,
						@PathParam("nomScene") String nomScene) {
		
		System.out.println(date + nomScene);
		Evenement event = eventsDAO.getEventById(date, nomScene);
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String json = gson.toJson(event);
		
		return json;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response addevent(@FormParam("dateDebut") String dateDebut, 
							 @FormParam("dateFin") String dateFin,
							 @FormParam("dj") String dj,
							 @FormParam("lieu") String lieu) {
		
		//Creation de l'event et insertion dans la bdd grace à la DAO
		String[] parts = lieu.split("_");
		String nom_lieu = parts[0];
		String nom_ville = parts[1];
		
		eventsDAO.addEvent(new Evenement(dateDebut, dateFin, dj, nom_lieu, nom_ville));
		
		return Response.ok("Évènement ajouté avec succès").build();
	}
}
