package de.hdm.itProjektSS17.shared.report;

import java.io.Serializable;


/**
 * 
 * 
 * Column bildet eine Spalte eines Row Objekts ab. Columns Objekte implementieren 
 * das Serializable Interface und können somit als Kopie zB. zwischen Clinet und 
 * Server übertragen werden.
 * 
 * @author Fabian König
 *
 */

public class Column implements Serializable{


	private static final long serialVersionUID = 1L;
	
	/**
	 * Der Wert des Zeilenonjekts entspricht dem Eintrag einer Zelle in einer Tabelle.
	 */
	private String value = "";

	/**
	 * Serialisierbare Klassen, die mittels GWT-RPC transportiert werden sollen,
	 * müssen einen No-Argument-Konstruktor besitzen. Ist kein Konstruktor
	 * explizit angegeben, so existiert in Java-Klassen der Default-Konstruktor,
	 * der dem No-Argument-Konstruktor entspricht.
	 * 
	 * Besitzt eine Klasse mind. einen explizit implementierten Konstruktor, so
	 * gelten nur diese explizit implementierten Konstruktoren. Der
	 * Default-Konstruktor gilt dann nicht. Wenn wir in einer solchen Situation
	 * aber dennoch einen No-Argument-Konstruktor benötigen, müssen wir diesen wie
	 * in diesem Beispiel explizit implementieren.
	 */
	public Column(){	
	}
	
	/**
	 * Der Konstruktor erzwingt bei der Initialisierung einen Spaltenwert.
	 * @param v, der Spaltenwert.
	 */
	public Column(String v){
		this.value = v;
	}
	
	/**
	 * Auslesen des Spaltenwerts.
	 * @return den Spaltenwert als String.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Setzen des Spaltenwerts.
	 * @param value der Spaltenwert.
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * Umwandeln des Column-Onjekts in einen String.
	 */
	public String toString(){
		return this.value;
	}

}
