CREATE OR REPLACE TRIGGER majKilometrage
    AFTER UPDATE
    ON DOSSIER
    REFERENCING
        NEW AS location
    FOR EACH ROW
BEGIN
    UPDATE VEHICULE V SET V.KILOMETRES =:location.KIL_RETOUR WHERE V.NO_IMM = :location.NO_IMM;
END;

CREATE OR REPLACE TRIGGER logAudit
    AFTER INSERT
    ON DOSSIER
    REFERENCING
        NEW AS location
    FOR EACH ROW
DECLARE
    v_DUREE  NUMBER(3);
    v_NOMCLI CLIENT.NOM%TYPE;
    v_MARQUE VEHICULE.MARQUE%TYPE;
    v_MODELE VEHICULE.MODELE%TYPE;
BEGIN
    v_DUREE := :location.DATE_RETOUR - :location.DATE_RETRAIT + 1;
    IF v_DUREE > 7 THEN
        SELECT NOM INTO v_NOMCLI FROM CLIENT WHERE CODE_CLI = :location.CODE_CLI;
        SELECT MARQUE, MODELE INTO v_MARQUE, v_MODELE FROM VEHICULE WHERE NO_IMM = : location.NO_IMM;
        INSERT INTO "AUDIT"
        VALUES (:location.NO_DOSSIER, (SELECT SYSDATE FROM DUAL), location.CODE_CLI, v_NOMCLI, v_MARQUE, v_MODELE);
    END IF;
END;