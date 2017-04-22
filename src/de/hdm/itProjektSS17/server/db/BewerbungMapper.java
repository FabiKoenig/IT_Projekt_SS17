package de.hdm.itProjektSS17.server.db;

import java.util.*;

import de.hdm.itProjektSS17.shared.bo.*;

/**
 * Mapper für ein Bewerbung-Objekt
 */
public class BewerbungMapper {

    /**
     * Speicherung der einzigen Instanz dieser Mapperklasse.
     */
    private static BewerbungMapper bewerbungMapper = null;

    /**
     * Geschützter Konstruktor
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
     * @return Liefert eine Bewerbung entsprechend der übergebenen id zurück
     */
    public Bewerbung findById(int id) {
        // TODO implement here
        return null;
    }
    
    /**
     * @param b 
     * @return Liefert eine Bewerbung entsprechend des übergebenen Objekts zurück
     */
    public Bewerbung findById(Bewerbung b) {
        // TODO implement here
        return null;
    }

    /**
     * @param b 
     * @return Zielentität aus der Datenbank gemäß des übergebenen Objekts löschen.
     */
    public void delete(Bewerbung b) {
        // TODO implement here
    }

    /**
     * @param b 
     * @return Zielentität aus der Datenbank gemäß den Informationen des übergebenen Objekts aktualisieren.
     */
    public Bewerbung update(Bewerbung b) {
        // TODO implement here
        return null;
    }

    /**
     * @param b
     * @return Übergebenes Objekt als neue Entität in die Datenbank schreiben.
     */
    public Bewerbung insert(Bewerbung b) {
        // TODO implement here
        return null;
    }
    
    /**
     * @param a
     * @return Liefert ein Bewerbung-Objekt anhand des übergebenen Beziehungs-Objektes aus der Datenbank zurück.
     */
    public Bewerbung findByForeignAusschreibung(Ausschreibung a){
		return null;
    }
    
    /**
     * @param o
     * @return Liefert ein Bewerbung-Objekt anhand des übergebenen Beziehungs-Objektes aus der Datenbank zurück.
     */
    public Bewerbung findByForeignOrganisationseinheit(Organisationseinheit o){
		return null;
    }
}