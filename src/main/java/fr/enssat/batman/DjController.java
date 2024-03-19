package fr.enssat.batman;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("/dj-management")
public class DjController {
	
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/events")
	public String events() {
		return "hello world!";
	}
}
