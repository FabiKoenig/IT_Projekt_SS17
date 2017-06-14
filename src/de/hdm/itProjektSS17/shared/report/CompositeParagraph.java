package de.hdm.itProjektSS17.shared.report;

import java.util.Vector;

/**
 * Klasse stellte die Menge einzelner Absätze dar, welche als Unterabschnitte in einem Vector
 * gespeichert werden.
 */
public class CompositeParagraph extends Paragraph{

	
	private static final long serialVersionUID = 1L;

	/**
	 * In diesem Vector werden Unterabschnitte gespeichert.
	 */
	private Vector<SimpleParagraph> subParagraphs = new Vector<SimpleParagraph>();
	
	
	/**
	 * Hinzufügen eines Unterabschnittes.
	 * @param p der hinzuzufügende Unterabschnitt.
	 */
	public void addSubParagraph(SimpleParagraph p) {
		this.subParagraphs.addElement(p);
	}
	  
	/**
	 * Entfernen eines Unterabschnittes.
	 * @param p der zu entfernende Unterabschnitt.
	 */
	public void removeSubParagraph(SimpleParagraph p) {
		this.subParagraphs.removeElement(p);
	}
	
	/**
	 * Auslesen der Unterabschnitte.
	 * @return Vector-Objekt, der sämtliche Unterabschnitte enthält.
	 */
	public Vector<SimpleParagraph> getSubParagraphs() {
		return this.subParagraphs;
	}
	  
	/**
	 * Auslesen der Anzahl von Unterabschnitten.
	 * @return int die Anzahl der Unterabschnitte.
	 */
	public int getParagraphsSize() {
		return this.subParagraphs.size();
	}
	
	/**
	 * Auslesen eines bestimmten Unterabschnitts.
	 * @param i Index des auszulesenden Unterabschnitt.
	 * @return den gewünschten Unterabschnitt.
	 */
	public SimpleParagraph getParagraphByIndex(int i) {
		return this.subParagraphs.elementAt(i);
	}
	
	/**
	 * Umwandeln eines CompositeParagraphs in einen String.
	 */
	public String toString() {
	    /*
	     * Wir legen einen leeren Buffer an, in den wir sukzessive sämtliche
	     * String-Repräsentationen der Unterabschnitte eintragen.
	     */
	    StringBuffer result = new StringBuffer();

	    // Schleife über alle Unterabschnitte
	    for (int i = 0; i < this.subParagraphs.size(); i++) {
	      SimpleParagraph p = this.subParagraphs.elementAt(i);

	      /*
	       * den jew. Unterabschnitt in einen String wandeln und an den Buffer hängen.
	       */
	      result.append(p.toString() + "\n");
	    }

	    /*
	     * Schließlich wird der Buffer in einen String umgewandelt und
	     * zurückgegeben.
	     */
	    return result.toString();
	  }
}
