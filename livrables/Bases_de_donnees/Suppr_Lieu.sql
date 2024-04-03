DELIMITER $$

CREATE TRIGGER Suppr_Lieu
BEFORE DELETE ON Lieu
FOR EACH ROW
BEGIN
	-- supprimer les évenement associé au DJ avant de le supprimer
	DELETE FROM Evenement WHERE lieu_evenement = OLD.nom_lieu;
END$$

DELIMITER ;