package de.hdm.itProjektSS17.server.db;

import java.util.*;

import de.hdm.itProjektSS17.shared.bo.Unternehmen;

/**
 * Mapper für ein Unternehmen-Objekt
 */
public class UnternehmenMapper {

    /**
     * Speicherung der einzigen Instanz dieser Mapperklasse.
     */
    private static UnternehmenMapper unternehmenMapper = null;

    /**
     * Geschützter Konstruktor
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
     * @return Liefert ein Unternehmen entsprechend der übergebenen id zurück
     */
    public Unternehmen findById(int id) {
        // TODO implement here
        return null;
    }

    /**
     * @param u 
     * @return Zielentität aus der Datenbank gemäß des übergebenen Objekts löschen.
     */
    public void delete(Unternehmen u) {
        // TODO implement here
    }

    /**
     * @param u 
     * @return Zielentität aus der Datenbank gemäß den Informationen des übergebenen Objekts aktualisieren.
     */
    public Unternehmen update(Unternehmen u) {
        // TODO implement here
        return null;
    }

    /**
     * @param u 
     * @return Übergebenes Objekt als neue Entität in die Datenbank schreiben.
     */
    public Unternehmen insert(Unternehmen u) {
        // TODO implement here
        return null;
    }

}