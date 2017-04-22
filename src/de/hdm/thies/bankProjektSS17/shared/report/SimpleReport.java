package de.hdm.thies.bankProjektSS17.shared.report;

import java.util.Date;
import java.util.Vector;


/**
 * Beschreibung hinzufügen (TODO)
 * @author Fabian
 *
 */


public class SimpleReport extends Report{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Ein Vector-Objekt, das sämtliche Zeilen enthält.
	 */
	private Vector<Row> table = new Vector<Row>();
	
	
	/**
	 * Hinzufügen eines Zeilen-Objekts.
	 * @param r die hinzuzufügende Zeile.
	 */
	public void addRow(Row r){
		this.table.addElement(r);
	}
	
	/**
	 * Entfernen eines Zeilen-Objekts.
	 * @param r die zu entfernende Zeile.
	 */
	public void removeRow(Row r){
		this.table.removeElement(r);
	}
	
	/**
	 * Auslesen sämtlicher Zeilen.
	 * @return ein Vector-Objekt mit sämtlichen Zeilen
	 */
	public Vector<Row> getRows(){
		return this.table;
	}
}
