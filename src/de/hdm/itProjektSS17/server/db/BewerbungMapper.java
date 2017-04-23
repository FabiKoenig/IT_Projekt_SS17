package de.hdm.itProjektSS17.server.db;

import java.util.*;

import de.hdm.itProjektSS17.shared.bo.*;

/**
 * Mapper fuer ein Bewerbung-Objekt
 */
public class BewerbungMapper {

    /**
     * Speicherung der einzigen Instanz dieser Mapperklasse.
     */
    private static BewerbungMapper bewerbungMapper = null;

    /**
     * Geschuetzter Konstruktor
     */
    protected BewerbungMapper() {
        // TODO implement here
    }

    /**
     * @return Sicherstellung der Singleton-Eigenschaft der Mapperklasse.
     */
    public static BewerbungMapper bewerbungMapper() {
        if(bewerbungMapper == null){
        	bewerbungMapper = new BewerbungMapper();
        }
        return bewerbungMapper;
    }

    /**
     * @param id 
     * @return Liefert eine Bewerbung entsprechend der uebergebenen id zurueck
     */
    public Bewerbung findById(int id) {
        // TODO implement here
        return null;
    }
    
    /**
     * @param b 
     * @return Liefert eine Bewerbung entsprechend des uebergebenen Objekts zurueck
     */
    public Bewerbung findByObject(Bewerbung b) {
        // TODO implement here
        return null;
    }

    /**
     * @param b 
     * @return Zielentitaet aus der Datenbank gemaess des uebergebenen Objekts loeschen.
     */
    public void delete(Bewerbung b) {
        // TODO implement here
    }

    /**
     * @param b 
     * @return Zielentitaet aus der Datenbank gemaess den Informationen des uebergebenen Objekts aktualisieren.
     */
    public Bewerbung update(Bewerbung b) {
        // TODO implement here
        return null;
    }

    /**
     * @param b
     * @return Uebergebenes Objekt als neue Entitaet in die Datenbank schreiben.
     */
    public Bewerbung insert(Bewerbung b) {
        // TODO implement here
        return null;
    }
    
    /**
     * @param ausschreibungId
     * @return Liefert alle Bewerbung-Objekte anhand des uebergebenen Beziehungs-Objektes aus der Datenbank zurueck.
     */
    public Vector<Bewerbung> findByForeignAusschreibungId(int ausschreibungId){
		return null;
    }
    
    /**
     * @param organisationseinheitId
     * @return Liefert alle Bewerbung-Objekte anhand des uebergebenen Beziehungs-Objektes aus der Datenbank zurueck.
     */
    public Vector<Bewerbung> findByForeignOrganisationseinheitId(int organisationseinheitId){
		return null;
    }
}