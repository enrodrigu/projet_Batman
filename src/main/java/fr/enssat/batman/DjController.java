package fr.enssat.batman;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("/djs")
public class DjController {
	
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String event() {
		return "Récupération des djs";
	}
}
