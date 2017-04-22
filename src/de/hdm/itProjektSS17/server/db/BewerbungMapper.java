package de.hdm.itProjektSS17.server.db;

import java.util.*;

import de.hdm.itProjektSS17.shared.bo.*;

/**
 * Mapper f�r ein Bewerbung-Objekt
 */
public class BewerbungMapper {

    /**
     * Speicherung der einzigen Instanz dieser Mapperklasse.
     */
    private static BewerbungMapper bewerbungMapper = null;

    /**
     * Gesch�tzter Konstruktor
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
     * @return Liefert eine Bewerbung entsprechend der �bergebenen id zur�ck
     */
    public Bewerbung findById(int id) {
        // TODO implement here
        return null;
    }
    
    /**
     * @param b 
     * @return Liefert eine Bewerbung entsprechend des �bergebenen Objekts zur�ck
     */
    public Bewerbung findById(Bewerbung b) {
        // TODO implement here
        return null;
    }

    /**
     * @param b 
     * @return Zielentit�t aus der Datenbank gem�� des �bergebenen Objekts l�schen.
     */
    public void delete(Bewerbung b) {
        // TODO implement here
    }

    /**
     * @param b 
     * @return Zielentit�t aus der Datenbank gem�� den Informationen des �bergebenen Objekts aktualisieren.
     */
    public Bewerbung update(Bewerbung b) {
        // TODO implement here
        return null;
    }

    /**
     * @param b
     * @return �bergebenes Objekt als neue Entit�t in die Datenbank schreiben.
     */
    public Bewerbung insert(Bewerbung b) {
        // TODO implement here
        return null;
    }
    
    /**
     * @param a
     * @return Liefert ein Bewerbung-Objekt anhand des �bergebenen Beziehungs-Objektes aus der Datenbank zur�ck.
     */
    public Bewerbung findByForeignAusschreibung(Ausschreibung a){
		return null;
    }
    
    /**
     * @param o
     * @return Liefert ein Bewerbung-Objekt anhand des �bergebenen Beziehungs-Objektes aus der Datenbank zur�ck.
     */
    public Bewerbung findByForeignOrganisationseinheit(Organisationseinheit o){
		return null;
    }
}