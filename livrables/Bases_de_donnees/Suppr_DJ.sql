DELIMITER $$

CREATE TRIGGER Suppr_DJ
BEFORE DELETE ON DJ
FOR EACH ROW
BEGIN
	-- supprimer les évenement associé au DJ avant de le supprimer
	DELETE FROM Evenement WHERE DJ = OLD.nom_scene;
END$$

DELIMITER ;