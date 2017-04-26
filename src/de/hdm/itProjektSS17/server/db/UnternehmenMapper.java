package de.hdm.itProjektSS17.server.db;

import java.util.*;

import de.hdm.itProjektSS17.shared.bo.Unternehmen;

/**
 * Mapper fuer ein Unternehmen-Objekt
 */
public class UnternehmenMapper extends OrganisationseinheitMapper{

    /**
     * Speicherung der einzigen Instanz dieser Mapperklasse.
     */
    private static UnternehmenMapper unternehmenMapper = null;

    /**
     * Geschuetzter Konstruktor
     */
    protected UnternehmenMapper() {
        // TODO implement here
    }

    /**
     * @return unternehmenMapper - Sicherstellung der Singleton-Eigenschaft der Mapperklasse.
     */
    public static UnternehmenMapper unternehmenMapper() {
        if(unternehmenMapper == null){
        	unternehmenMapper = new UnternehmenMapper();
        }
        return unternehmenMapper;
    }

    /**
     * @param id 
     * @return Liefert ein Unternehmen entsprechend der �bergebenen id zurueck
     */
    public Unternehmen findById(int id) {
        // TODO implement here
        return null;
    }
    
    /**
     * 
     * @param u
     * @return Liefert ein Unternehmen entsprechend des übergebenen Objekts zurueck.
     */
    public Unternehmen findByObject(Unternehmen u){
		return u;
    	
    }

    /**
     * @param u 
     * @return Zielentitaet aus der Datenbank gemaess des uebergebenen Objekts loeschen.
     */
    public void delete(Unternehmen u) {
        // TODO implement here
    }

    /**
     * @param u 
     * @return Zielentitaet aus der Datenbank gemaess den Informationen des uebergebenen Objekts aktualisieren.
     */
    public Unternehmen update(Unternehmen u) {
        // TODO implement here
        return null;
    }

    /**
     * @param u 
     * @return Uebergebenes Objekt als neue Entitaet in die Datenbank schreiben.
     */
    public Unternehmen insert(Unternehmen u) {
        // TODO implement here
        return null;
    }

}