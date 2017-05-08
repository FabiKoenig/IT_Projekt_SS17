package de.hdm.itProjektSS17.shared.report;

public class HTMLReportWriter {

	private String text = "";
	
	
	
	public void resetReportText(){
		this.text = "";
	}
	
	/**
	 * Umwandeln eines ParagraphObjekts in HTML.
	 * @param p der Paragraph.
	 * @return HTML-Text.
	 */
	public String paragraphToHTML(Paragraph p) {
		if (p instanceof CompositeParagraph) {
		    return this.paragraphToHTML((CompositeParagraph) p);
		}
		else {
		    return this.paragraphToHTML((SimpleParagraph) p);
		}
	}	
	
	/**
	 * Umwadeln eines CompositeParagraphs in HTML.
	 * @param p der Paragraph.
	 * @return HTML-Text.
	 */
	 public String paragraph2HTML(CompositeParagraph p) {
		StringBuffer result = new StringBuffer();

		for (int i = 0; i < p.getParagraphsSize(); i++) {
		result.append("<p>" + p.getParagraphByIndex(i) + "</p>");
		}
		return result.toString();
	}	
	
	/**
	 * Umwandeln eines SimpleParagraphs in HTML.
	 * @param p der Paragraph.
	 * @return HTML Text.
	 */
	 public String paragraphToHTML(SimpleParagraph p) {
		return "<p>" + p.toString() + "</p>";
	}
	 
	 /**
	  * HTML Header Text erstellen.
	  * @return HTMl-Text.
	  */
	 public String getHeader() {
		StringBuffer result = new StringBuffer();

		result.append("<html><head><title></title></head><body>");
		return result.toString();
	}
	 
	 /**
	  * HTML Trailer Text erstellen.
	  * @return HTML-Text.
	  */
	  public String getTrailer() {
		 return "</body></html>";
	}
	  
	  /**
	   * Auslesen des Ergebnisses der zuletzt aufgerufenen Prozessierungsmethode.
	   * @return ein String in HTML-Format.
	   */
	  public String getReportText() {
		 return this.getHeader() + this.text + this.getTrailer();
	}

	  
	  public void process(AlleAusschreibungenReport r){
		  /**
		   * TODO
		   */
	  }
	  
	  public void process(AlleAusschreibungenZuPartnerprofilReport r){
		 /**
		  * TODO 
		  */
	  }
	  
	  public void process(AlleBewerbungenAufEigeneAusschreibungenReport r){
		/**
		 * TODO  
		 */
	  }
	  
	  public void process(AlleBewerbungenMitAusschreibungenReport r){
		  /**
		   * TODO
		   */
	  }
	  
	  public void process(ProjektverflechtungenReport r){
		  /**
		   * TODO
		   */
	  }
	  
	  public void process(FanInFanOutReport r){
		  /**
		   * TODO
		   */
	  }
}


