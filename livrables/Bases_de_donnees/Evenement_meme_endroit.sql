DELIMITER $$

CREATE TRIGGER Evenement_meme_endroit
BEFORE INSERT ON Evenement
FOR EACH ROW
BEGIN
	DECLARE same_date_event INT;
    
    -- Compter le nombre d'événements récents pour ce DJ sur le même continent
    SELECT COUNT(*)
    INTO same_date_event
    FROM Evenement e
    JOIN Lieu l ON (lieu_evenement = nom_lieu AND ville = ville_evenement)
    WHERE lieu_evenement = NEW.lieu_evenement AND ville_evenement = NEW.ville_evenement
    AND (  -- On vérifie si le délai de 24h est respecté avant et après
		NEW.date_debut_evenement BETWEEN e.date_debut_evenement AND e.date_fin_evenement
	 	OR NEW.date_fin_evenement BETWEEN e.date_debut_evenement AND e.date_fin_evenement
     );
    
	IF same_date_event > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = "Impossible d'ajouter l'événement. Un autre DJ est déjà prévu ici sur ces horaires.";
    END IF;

END $$

DELIMITER ;