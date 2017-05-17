package de.hdm.itProjektSS17.shared.report;

import java.io.Serializable;
import java.util.Vector;

public class Row implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Vector<Column> columns = new Vector<Column>();
	
	
	/**
	 * Hinzufügen einer Spalte. 
	 * @param c
	 */
	
	public void addColum(Column c){
		this.columns.addElement(c);
	}
	
	/**
	 * Löschen einer Spalte.
	 * 
	 * @param c
	 */
	public void removeColumn(Column c){
		this.columns.removeElement(c);
	}
	
	/**
	 * Auslesen sämtlicher Spalten.
	 * @return ein Vector-Obejekt mit sämtlichen Spalten
	 */
	public Vector<Column> getColumns(){
		return this.columns;
	}
	
	/**
	 * Auslesen der Anzahl sämtlicher Spalten.
	 * @return int Anzahl der Spalten
	 */
	public int getColumnsSize(){
		return this.columns.size();
	}
	
	/**
	 * Auslesen eines einzelnen Spalten-Objekts.
	 * @param i der Index der auszulesenden Spalte.
	 * @return das gewünschte Spaltenobjekt.
	 */
	public Column getColumnByIndex(int i){
		return this.columns.elementAt(i);
	}
}
