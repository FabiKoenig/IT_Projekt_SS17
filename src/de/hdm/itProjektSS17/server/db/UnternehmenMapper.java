package de.hdm.itProjektSS17.server.db;

import java.util.*;

import de.hdm.itProjektSS17.shared.bo.Unternehmen;

/**
 * Mapper f�r ein Unternehmen-Objekt
 */
public class UnternehmenMapper {

    /**
     * Speicherung der einzigen Instanz dieser Mapperklasse.
     */
    private static UnternehmenMapper unternehmenMapper = null;

    /**
     * Gesch�tzter Konstruktor
     */
    protected UnternehmenMapper() {
        // TODO implement here
    }

    /**
     * @return Sicherstellung der Singleton-Eigenschaft der Mapperklasse.
     */
    public static UnternehmenMapper unternehmenMapper() {
        if(unternehmenMapper == null){
        	unternehmenMapper = new UnternehmenMapper();
        }
        return unternehmenMapper;
    }

    /**
     * @param id 
     * @return Liefert ein Unternehmen entsprechend der �bergebenen id zur�ck
     */
    public Unternehmen findById(int id) {
        // TODO implement here
        return null;
    }

    /**
     * @param u 
     * @return Zielentit�t aus der Datenbank gem�� des �bergebenen Objekts l�schen.
     */
    public void delete(Unternehmen u) {
        // TODO implement here
    }

    /**
     * @param u 
     * @return Zielentit�t aus der Datenbank gem�� den Informationen des �bergebenen Objekts aktualisieren.
     */
    public Unternehmen update(Unternehmen u) {
        // TODO implement here
        return null;
    }

    /**
     * @param u 
     * @return �bergebenes Objekt als neue Entit�t in die Datenbank schreiben.
     */
    public Unternehmen insert(Unternehmen u) {
        // TODO implement here
        return null;
    }

}