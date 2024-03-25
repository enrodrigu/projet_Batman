package fr.enssat.batman;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Path("/djs")
public class DjController {
	
	DjsDAO DjsDAOImpl = new DjsDAO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Dj> AllDjs() {
	    return DjsDAO.getAllDJs();
	}

	@GET
	@Path("/nom/{nom_scene}")
	@Produces(MediaType.APPLICATION_JSON)
	public Dj getDj(@PathParam("nom_scene") String nom_scene) {
	    return DjsDAO.getDj(nom_scene);
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
        // Pas sur que ça fonctionne à tester
        DjsDAOImpl.editDj(new Dj(json[0],json[1], json[2],json[3],json[4],json[5]));

	    // Retournez une réponse appropriée
	    return Response.ok("DJ édité avec succès").build();
	}
	
	@DELETE
	@Path("/nom/{nom_scene}")
	@Consumes(MediaType.APPLICATION_APPLICATION_JSON)
	public Response deleteDJ(@PathParam("nom_scene") String nom_scene) {
	  
		// On traite la suppression du DJ
	    System.out.println("Suppression du DJ de nom : " + nom_scene);
	        
	    // Logique de suppression de DJ
	    DjsDAOImpl.deleteDj(nom_scene);

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
	    DjsDAOImpl.addDj(new Dj(nom, prenom, nom_scene, date_naissance, lieu_residence, style_musical));

	    // Retournez une réponse appropriée
	    return Response.ok("DJ ajouté avec succès").build();
	}
}
