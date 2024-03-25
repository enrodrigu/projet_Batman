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
		
		return json;
	}

	@GET
	@Path("/nom/{nom_scene}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getDj(@PathParam("nom_scene") String nom_scene) {
		System.out.println("test");
		Dj dj = djsDAOImpl.getDJ(nom_scene);
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String json = gson.toJson(dj);
		
		return json;
	}
	
	
	@PATCH
	@Path("/nom/{nom_scene}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editDJ(@PathParam("nom_scene") String nom_scene, String jsonBody) {
	  
		// On traite la mise à jour des données du DJ
	    System.out.println("Édition du DJ de nom : " + nom_scene);
	    System.out.println("Corps de la requête JSON : " + jsonBody);
	        
	    // On parse la String Json
	    Gson gson = new Gson();
        JsonObject json = gson.fromJson(jsonBody, JsonObject.class);
       
	    
        // Logique d'édition de DJ
        // Y a des erreurs mec, ça fonctionne pas là
        // djsDAOImpl.editDj(new Dj(json[0],json[1], json[2],json[3],json[4],json[5]));

	    // Retournez une réponse appropriée
	    return Response.ok("DJ édité avec succès").build();
	}
	
	@DELETE
	@Path("/nom/{nom_scene}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteDJ(@PathParam("nom_scene") String nom_scene) {
	  
		// On traite la suppression du DJ
	    System.out.println("Suppression du DJ de nom : " + nom_scene);
	        
	    // Logique de suppression de DJ
	    djsDAOImpl.deleteDj(nom_scene);

	    // Retournez une réponse appropriée
	    return Response.ok("DJ supprimé avec succès").build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response addDj(@FormParam("nom") String nom, 
							@FormParam("prenom") String prenom,
							@FormParam("nom_scene") String nom_scene, 
							@FormParam("date_naissance") String date_naissance, 
							@FormParam("lieu_residence") String lieu_residence, 
							@FormParam("style_musical") String style_musical) {
	  
		// On traite l'ajout du DJ
	    System.out.println("Ajout du DJ de nom : " + nom_scene);
	        
	    // Logique d'ajout de DJ
	    djsDAOImpl.addDj(new Dj(nom, prenom, nom_scene, date_naissance, lieu_residence, style_musical));

	    // Retournez une réponse appropriée
	    return Response.ok("DJ ajouté avec succès").build();
	}
}
