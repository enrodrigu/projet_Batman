DELIMITER $$

CREATE TRIGGER Evenement_meme_continent
BEFORE INSERT ON Evenement
FOR EACH ROW
BEGIN
	DECLARE dj_count_same_continent INT;
    DECLARE event_continent VARCHAR(45);
    
    -- Récupère le continent où aurait lieu l'évenement
    SELECT continent
    INTO event_continent
    FROM Evenement 
    JOIN Lieu ON (lieu_evenement = nom_lieu AND ville = ville_evenement)
    WHERE nom_lieu = NEW.lieu_evenement
    AND ville = NEW.ville_evenement;
    
    -- Compter le nombre d'événements récents pour ce DJ sur le même continent
    SELECT COUNT(*)
    INTO dj_count_same_continent
    FROM Evenement e
    JOIN Lieu l ON (lieu_evenement = nom_lieu AND ville = ville_evenement)
    WHERE e.DJ = NEW.DJ 
    AND (  -- On vérifie si le délai de 24h est respecté avant et après
	 	e.date_debut_evenement BETWEEN DATE_SUB(NEW.date_debut_evenement, INTERVAL 24 HOUR) AND NEW.date_debut_evenement
	 	OR e.date_fin_evenement BETWEEN NEW.date_fin_evenement AND DATE_ADD(NEW.date_fin_evenement, INTERVAL 24 HOUR)
        OR e.date_fin_evenement BETWEEN DATE_SUB(NEW.date_debut_evenement, INTERVAL 24 HOUR) AND NEW.date_debut_evenement
	 	OR e.date_debut_evenement BETWEEN NEW.date_fin_evenement AND DATE_ADD(NEW.date_fin_evenement, INTERVAL 24 HOUR)
     )
     AND continent = event_continent;
    
	IF dj_count_same_continent > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = "Impossible d'ajouter l'événement. Le DJ a déjà un événement sur le même continent dans les 24 heures.";
    END IF;

END $$

DELIMITER ;