package fr.enssat.batman;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import fr.enssat.batman.dj.Dj;
import fr.enssat.batman.dj.DjsDAO;

@Path("/djs")
public class DjController {
	
	DjsDAO djsDAOImpl = new DjsDAO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String allDjs() {
		List<Dj> djs = djsDAOImpl.getAllDJs();
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String json = gson.toJson(djs);
		System.out.println(json);
		return json;
	}

	@GET
	@Path("/nom/{nomScene}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getDj(@PathParam("nomScene") String nomScene) {
		System.out.println("test");
		Dj dj = djsDAOImpl.getDJ(nomScene);
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String json = gson.toJson(dj);
		
		return json;
	}
	
	
	@PATCH
	@Path("/nom/{nomScene}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editDJ(@PathParam("nomScene") String nomScene, String jsonBody) {
	  
		// On traite la mise à jour des données du DJ
	    System.out.println("Édition du DJ de nom : " + nomScene);
	    System.out.println("Corps de la requête JSON : " + jsonBody);
	        
	    // On parse la String Json
	    Gson gson = new Gson();
        JsonObject json = gson.fromJson(jsonBody, JsonObject.class);
       
	    
        // Logique d'édition de DJ
        djsDAOImpl.editDj(new Dj(json.get("nom").getAsString(),
        							json.get("prenom").getAsString(), 
        							json.get("nomScene").getAsString(),
        							json.get("dateNaissance").getAsString(),
        							json.get("lieuResidence").getAsString(),
        							json.get("styleMusical").getAsString()));

	    // Retournez une réponse appropriée
	    return Response.ok("DJ édité avec succès").build();
	}
	
	@DELETE
	@Path("/nom/{nomScene}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteDJ(@PathParam("nomScene") String nomScene) {
	  
		// On traite la suppression du DJ
	    System.out.println("Suppression du DJ de nom : " + nomScene);
	        
	    // Logique de suppression de DJ
	    djsDAOImpl.deleteDj(nomScene);

	    // Retournez une réponse appropriée
	    return Response.ok("DJ supprimé avec succès").build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response addDj(@FormParam("nom") String nom, 
							@FormParam("prenom") String prenom,
							@FormParam("nomScene") String nomScene, 
							@FormParam("dateNaissance") String dateNaissance, 
							@FormParam("lieuResidence") String lieuResidence, 
							@FormParam("styleMusical") String styleMusical) {
	  
		// On traite l'ajout du DJ
	    System.out.println("Ajout du DJ de nom de scène : " + nomScene);
	        
	    // Logique d'ajout de DJ
	    djsDAOImpl.addDj(new Dj(nom, prenom, nomScene, dateNaissance, lieuResidence, styleMusical));

	    // Retournez une réponse appropriée
	    return Response.ok("DJ ajouté avec succès").build();
	}
}
