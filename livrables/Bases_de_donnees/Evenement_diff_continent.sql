DELIMITER $$

CREATE TRIGGER Evenement_diff_continent
BEFORE INSERT ON Evenement
FOR EACH ROW
BEGIN
	DECLARE dj_count_diff_continent INT;

	-- Comtper le nombre d'événements récents pour ce DJ sur un autre continent
    SELECT COUNT(*)
    INTO dj_count_diff_continent
    FROM Evenement e
    JOIN Lieu l ON lieu_evenement = nom_lieu AND ville = ville_evenement
    WHERE e.DJ = NEW.DJ
    AND (  -- On vérifie si le délai de 24h est respecté avant et après
	 	e.date_debut_evenement BETWEEN DATE_SUB(NEW.date_debut_evenement, INTERVAL 48 HOUR) AND NEW.date_debut_evenement
	 	OR e.date_fin_evenement BETWEEN NEW.date_fin_evenement AND DATE_ADD(NEW.date_fin_evenement, INTERVAL 48 HOUR)
        OR e.date_fin_evenement BETWEEN DATE_SUB(NEW.date_debut_evenement, INTERVAL 48 HOUR) AND NEW.date_debut_evenement
	 	OR e.date_debut_evenement BETWEEN NEW.date_fin_evenement AND DATE_ADD(NEW.date_fin_evenement, INTERVAL 48 HOUR)
     );
    
	IF dj_count_diff_continent > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = "Impossible d'ajouter l'événement Le DJ a déjà un événement sur un autre continent dans les 48 heures.";
    END IF;
    
END $$

DELIMITER ;