package de.hdm.itProjektSS17.shared.report;


/**
 * TODO
 * @author Fabian
 *
 */
public class SimpleParagraph extends Paragraph{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String text = "";

	/**
	 * TODO
	 */
	public SimpleParagraph(){
	}
	
	/**
	 * Durch diesen Konstruktor kann bereits bei der Insantiierung eines
	 * SimpleParagraph Objektes der zugehörige Inhalt gesetzt werden.
	 * @param t Inhalt des Absatzes.
	 */
	public SimpleParagraph(String t){
		this.text = t;
	}
	
	/**
	 * Auslesen des Inhaltes eines Absatzes.
	 * @return Inhalt als String
	 */
	public String getText() {
		return text;
	}

	/**
	 * Setzen des Inhaltes eines Absatzes.
	 * @param text der Inhalt des Absatzes.
	 */
	public void setText(String text) {
		this.text = text;
	}

	
	/**
	 * Umwandeln des Simple-Paragrapg Objekts in einen String. 
	 */
	public String toString() {
		return this.text;
	}
	
	
	
}
