package de.hdm.itProjektSS17.shared.report;

import java.util.Vector;

import com.google.gwt.user.client.Window;

public class HTMLReportWriter {

	private String reportText = "";
	
	
	
	public void resetReportText(){
		this.reportText = "";
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
	 public String paragraphToHTML(CompositeParagraph p) {
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
		 return this.getHeader() + this.reportText + this.getTrailer();
	}

	  
	  public void process(AlleAusschreibungenReport r){
		  
		  //Löschen des Ergebnisses der vorherigen Prozessierung
		  this.resetReportText();
		  
			
		    /*
		     * In diesen Buffer schreiben wir während der Prozessierung sukzessive
		     * unsere Ergebnisse.
		     */
		  StringBuffer result = new StringBuffer();
		  
		  
		    /*
		     * Nun werden Schritt für Schritt die einzelnen Bestandteile des Reports
		     * ausgelesen und in HTML-Form übersetzt.
		     */
		  	result.append("<H1>" + r.getTitel() + "</H1>");
		  	result.append("<table style=\"width:400px;border:1px solid silver\"><tr>");
		  	result.append("</tr><tr><td></td><td>" + r.getErstellungsdatum().toString()
		  	        + "</td></tr></table>");
		  	
		  
		  	
		  	 Vector<Row> rows = r.getRows();
		     result.append("<table style=\"width:400px\">");
		     
		     for (int i = 0; i < rows.size(); i++) {
		         Row row = rows.elementAt(i);
		         result.append("<tr>");
		         for (int k = 0; k < row.getColumnsSize(); k++) {
		           if (i == 0) {
		        	
		             result.append("<td style=\"background:silver;font-weight:bold\">" + row.getColumnByIndex(k)
		                 + "</td>");
		             
		           }
		           else {
		             if (i > 1) {
		               result.append("<td style=\"border-top:1px solid silver\">"
		                   + row.getColumnByIndex(k) + "</td>");
		             }
		             else {
		               result.append("<td valign=\"top\">" + row.getColumnByIndex(k) + "</td>");
		             }
		           }
		         }
		         result.append("</tr>");
		       }

		       result.append("</table>");
		       
		       /*
		        * Zum Schluss wird unser Arbeits-Buffer in einen String umgewandelt und der
		        * reportText-Variable zugewiesen. Dadurch wird es möglich, anschließend das
		        * Ergebnis mittels getReportText() auszulesen.
		        */
		       this.reportText = result.toString();
	  }
	  
	  
	  public void process(AlleAusschreibungenZuPartnerprofilReport r){
		 /**
		  * TODO 
		  */
	  }
	  
	  public void process(AlleBewerbungenAufEigeneAusschreibungenReport r){
		    // Zunächst löschen wir das Ergebnis vorhergehender Prozessierungen.
		    this.resetReportText();

		    /*
		     * In diesen Buffer schreiben wir während der Prozessierung sukzessive
		     * unsere Ergebnisse.
		     */
		    StringBuffer result = new StringBuffer();

		    /*
		     * Nun werden Schritt für Schritt die einzelnen Bestandteile des Reports
		     * ausgelesen und in HTML-Form übersetzt.
		     */
		    result.append("<H1>" + r.getTitel() + "</H1>");
		    result.append("<table><tr>");

		    result.append("</tr><tr><td></td><td>" + r.getErstellungsdatum().toString()
		        + "</td></tr></table>");

		    /*
		     * Da AlleBewerbungenAufEigeneAusschreibungenReport ein CompositeReport ist, enthält r
		     * eine Menge von Teil-Reports des Typs AlleBewerbungenAufEineAusschreibungDesUsers. Für
		     * jeden dieser Teil-Reports rufen wir process
		     * auf. Das Ergebnis des jew. Aufrufs fügen wir dem Buffer hinzu.
		     */
		    for (int i = 0; i < r.getSubReportsSize(); i++) {
		      /*
		       * AlleBewerbungenAufEineAusschreibungDesUsers wird als Typ der SubReports vorausgesetzt.
		       * Sollte dies in einer erweiterten Form des Projekts nicht mehr gelten,
		       * so müsste hier eine detailliertere Implementierung erfolgen.
		       */
		    	AlleBewerbungenAufEineAusschreibungDesUsers subReport = (AlleBewerbungenAufEineAusschreibungDesUsers) r
		          .getSubReportByIndex(i);

		      this.process(subReport);

		      result.append(this.reportText + "\n");

		      /*
		       * Nach jeder Übersetzung eines Teilreports und anschließendem Auslesen
		       * sollte die Ergebnisvariable zurückgesetzt werden.
		       */
		      this.resetReportText();
		    }

		    /*
		     * Zum Schluss wird unser Arbeits-Buffer in einen String umgewandelt und der
		     * reportText-Variable zugewiesen. Dadurch wird es möglich, anschließend das
		     * Ergebnis mittels getReportText() auszulesen.
		     */
		    this.reportText = result.toString();
		  }

	  
	  public void process(AlleBewerbungenMitAusschreibungenReport r){
		  /**
		   * TODO
		   */
	  }
	  
	  public void process(ProjektverflechtungenReport r){
		    // Zunächst löschen wir das Ergebnis vorhergehender Prozessierungen.
		    this.resetReportText();

		    /*
		     * In diesen Buffer schreiben wir während der Prozessierung sukzessive
		     * unsere Ergebnisse.
		     */
		    StringBuffer result = new StringBuffer();

		    /*
		     * Nun werden Schritt für Schritt die einzelnen Bestandteile des Reports
		     * ausgelesen und in HTML-Form übersetzt.
		     */
		    result.append("<H1>" + r.getTitel() + "</H1>");
		    result.append("<table><tr>");

		    result.append("</tr><tr><td></td><td>" + r.getErstellungsdatum().toString()
		        + "</td></tr></table>");

		    /*
		     * Da ProjektverflechtungenReport ein CompositeReport ist, enthält r
		     * eine Menge von Teil-Reports des Typs AllAccountsOfCustomerReport. Für
		     * jeden dieser Teil-Reports rufen wir processAllAccountsOfCustomerReport
		     * auf. Das Ergebnis des jew. Aufrufs fügen wir dem Buffer hinzu.
		     */
		    for (int i = 0; i < r.getSubReportsSize(); i++) {
		      /*
		       * AlleBewerbungenAufEineAusschreibungDesUsers wird als Typ der SubReports vorausgesetzt.
		       * Sollte dies in einer erweiterten Form des Projekts nicht mehr gelten,
		       * so müsste hier eine detailliertere Implementierung erfolgen.
		       */
		    
		      this.processSimpleReport(r.getSubReportByIndex(i));

		      result.append(this.reportText + "\n");

		      /*
		       * Nach jeder Übersetzung eines Teilreports und anschließendem Auslesen
		       * sollte die Ergebnisvariable zurückgesetzt werden.
		       */
		      this.resetReportText();
		    }

		    /*
		     * Zum Schluss wird unser Arbeits-Buffer in einen String umgewandelt und der
		     * reportText-Variable zugewiesen. Dadurch wird es möglich, anschließend das
		     * Ergebnis mittels getReportText() auszulesen.
		     */
		    this.reportText = result.toString();
	  }
	  
	  public void process(FanInFanOutReport r){
		  /**
		   * TODO
		   */
	  }
	  
	  public void process(AlleBewerbungenAufEineAusschreibungDesUsers r){
		  //Löschen des Ergebnisses der vorherigen Prozessierung
		  this.resetReportText();
		  

		    /*
		     * In diesen Buffer schreiben wir während der Prozessierung sukzessive
		     * unsere Ergebnisse.
		     */
		  StringBuffer result = new StringBuffer();
		  
		  
		    /*
		     * Nun werden Schritt für Schritt die einzelnen Bestandteile des Reports
		     * ausgelesen und in HTML-Form übersetzt.
		     */
		  	result.append("<H1>" + r.getTitel() + "</H1>");
		  	result.append("<table style=\"width:400px;border:1px solid silver\"><tr>");
		  	result.append("</tr><tr><td></td><td>" + r.getErstellungsdatum().toString()
		  	        + "</td></tr></table>");
		  	
		  	
		  	 Vector<Row> rows = r.getRows();
		     result.append("<table style=\"width:400px\">");
		     
		     for (int i = 0; i < rows.size(); i++) {
		         Row row = rows.elementAt(i);
		         result.append("<tr>");
		         for (int k = 0; k < row.getColumnsSize(); k++) {
		           if (i == 0) {
		             result.append("<td style=\"background:silver;font-weight:bold\">" + row.getColumnByIndex(k)
		                 + "</td>");
		           }
		           else {
		             if (i > 1) {
		               result.append("<td style=\"border-top:1px solid silver\">"
		                   + row.getColumnByIndex(k) + "</td>");
		             }
		             else {
		               result.append("<td valign=\"top\">" + row.getColumnByIndex(k) + "</td>");
		             }
		           }
		         }
		         result.append("</tr>");
		       }

		       result.append("</table>");
		       
		       /*
		        * Zum Schluss wird unser Arbeits-Buffer in einen String umgewandelt und der
		        * reportText-Variable zugewiesen. Dadurch wird es möglich, anschließend das
		        * Ergebnis mittels getReportText() auszulesen.
		        */
		       this.reportText = result.toString();
	  }  
	  
	  
	  public void process(FanIn r){
		  
		  //Löschen des Ergebnisses der vorherigen Prozessierung
		  this.resetReportText();
		  

		    /*
		     * In diesen Buffer schreiben wir während der Prozessierung sukzessive
		     * unsere Ergebnisse.
		     */
		  StringBuffer result = new StringBuffer();
		  
		  
		    /*
		     * Nun werden Schritt für Schritt die einzelnen Bestandteile des Reports
		     * ausgelesen und in HTML-Form übersetzt.
		     */
		  	result.append("<H1>" + r.getTitel() + "</H1>");
		  	result.append("<table style=\"width:400px;border:1px solid silver\"><tr>");
		  	result.append("<td valign=\"top\"><b>" + paragraphToHTML(r.getHeader())
	        + "</b></td>");
		  	result.append("</tr><tr><td></td><td>" + r.getErstellungsdatum().toString()
		  	        + "</td></tr></table>");
		  	
		  	
		  	 Vector<Row> rows = r.getRows();
		     result.append("<table style=\"width:400px\">");
		     
		     for (int i = 0; i < rows.size(); i++) {
		         Row row = rows.elementAt(i);
		         result.append("<tr>");
		         for (int k = 0; k < row.getColumnsSize(); k++) {
		           if (i == 0) {
		             result.append("<td style=\"background:silver;font-weight:bold\">" + row.getColumnByIndex(k)
		                 + "</td>");
		           }
		           else {
		             if (i > 1) {
		               result.append("<td style=\"border-top:1px solid silver\">"
		                   + row.getColumnByIndex(k) + "</td>");
		             }
		             else {
		               result.append("<td valign=\"top\">" + row.getColumnByIndex(k) + "</td>");
		             }
		           }
		         }
		         result.append("</tr>");
		       }

		       result.append("</table>");
		       
		       /*
		        * Zum Schluss wird unser Arbeits-Buffer in einen String umgewandelt und der
		        * reportText-Variable zugewiesen. Dadurch wird es möglich, anschließend das
		        * Ergebnis mittels getReportText() auszulesen.
		        */
		       this.reportText = result.toString();
	  }
	  
	  public void process(FanOut r){
		  
		  //Löschen des Ergebnisses der vorherigen Prozessierung
		  this.resetReportText();
		  

		    /*
		     * In diesen Buffer schreiben wir während der Prozessierung sukzessive
		     * unsere Ergebnisse.
		     */
		  StringBuffer result = new StringBuffer();
		  
		  
		    /*
		     * Nun werden Schritt für Schritt die einzelnen Bestandteile des Reports
		     * ausgelesen und in HTML-Form übersetzt.
		     */
		  	result.append("<H1>" + r.getTitel() + "</H1>");
		  	result.append("<table style=\"width:400px;border:1px solid silver\"><tr>");
		  	result.append("<td valign=\"top\"><b>" + paragraphToHTML(r.getHeader())
	        + "</b></td>");
		  	result.append("</tr><tr><td></td><td>" + r.getErstellungsdatum().toString()
		  	        + "</td></tr></table>");
		  	
		  	
		  	 Vector<Row> rows = r.getRows();
		     result.append("<table style=\"width:400px\">");
		     
		     for (int i = 0; i < rows.size(); i++) {
		         Row row = rows.elementAt(i);
		         result.append("<tr>");
		         for (int k = 0; k < row.getColumnsSize(); k++) {
		           if (i == 0) {
		             result.append("<td style=\"background:silver;font-weight:bold\">" + row.getColumnByIndex(k)
		                 + "</td>");
		           }
		           else {
		             if (i > 1) {
		               result.append("<td style=\"border-top:1px solid silver\">"
		                   + row.getColumnByIndex(k) + "</td>");
		             }
		             else {
		               result.append("<td valign=\"top\">" + row.getColumnByIndex(k) + "</td>");
		             }
		           }
		         }
		         result.append("</tr>");
		       }

		       result.append("</table>");
		       
		       /*
		        * Zum Schluss wird unser Arbeits-Buffer in einen String umgewandelt und der
		        * reportText-Variable zugewiesen. Dadurch wird es möglich, anschließend das
		        * Ergebnis mittels getReportText() auszulesen.
		        */
		       this.reportText = result.toString();
	  }
	  
	  public void process(AlleBewerbungenEinesUsers r){
		  
		  //Löschen des Ergebnisses der vorherigen Prozessierung
		  this.resetReportText();
		  

		    /*
		     * In diesen Buffer schreiben wir während der Prozessierung sukzessive
		     * unsere Ergebnisse.
		     */
		  StringBuffer result = new StringBuffer();
		  
		  
		    /*
		     * Nun werden Schritt für Schritt die einzelnen Bestandteile des Reports
		     * ausgelesen und in HTML-Form übersetzt.
		     */
		  	result.append("<H1>" + r.getTitel() + "</H1>");
		  	result.append("<table style=\"width:400px;border:1px solid silver\"><tr>");
		  	result.append("<td valign=\"top\"><b>" + paragraphToHTML(r.getHeader())
	        + "</b></td>");
		  	result.append("</tr><tr><td></td><td>" + r.getErstellungsdatum().toString()
		  	        + "</td></tr></table>");
		  	
		  	
		  	 Vector<Row> rows = r.getRows();
		     result.append("<table style=\"width:400px\">");
		     
		     for (int i = 0; i < rows.size(); i++) {
		         Row row = rows.elementAt(i);
		         result.append("<tr>");
		         for (int k = 0; k < row.getColumnsSize(); k++) {
		           if (i == 0) {
		             result.append("<td style=\"background:silver;font-weight:bold\">" + row.getColumnByIndex(k)
		                 + "</td>");
		           }
		           else {
		             if (i > 1) {
		               result.append("<td style=\"border-top:1px solid silver\">"
		                   + row.getColumnByIndex(k) + "</td>");
		             }
		             else {
		               result.append("<td valign=\"top\">" + row.getColumnByIndex(k) + "</td>");
		             }
		           }
		         }
		         result.append("</tr>");
		       }

		       result.append("</table>");
		       
		       /*
		        * Zum Schluss wird unser Arbeits-Buffer in einen String umgewandelt und der
		        * reportText-Variable zugewiesen. Dadurch wird es möglich, anschließend das
		        * Ergebnis mittels getReportText() auszulesen.
		        */
		       this.reportText = result.toString();
	  }
	  
	  public void process(AlleBeteiligungenEinesUsers r){
		  
		  //Löschen des Ergebnisses der vorherigen Prozessierung
		  this.resetReportText();
		  

		    /*
		     * In diesen Buffer schreiben wir während der Prozessierung sukzessive
		     * unsere Ergebnisse.
		     */
		  StringBuffer result = new StringBuffer();
		  
		  
		    /*
		     * Nun werden Schritt für Schritt die einzelnen Bestandteile des Reports
		     * ausgelesen und in HTML-Form übersetzt.
		     */
		  	result.append("<H1>" + r.getTitel() + "</H1>");
		  	result.append("<table style=\"width:400px;border:1px solid silver\"><tr>");
		  	result.append("<td valign=\"top\"><b>" + paragraphToHTML(r.getHeader())
	        + "</b></td>");
		  	result.append("</tr><tr><td></td><td>" + r.getErstellungsdatum().toString()
		  	        + "</td></tr></table>");
		  	
		  	
		  	 Vector<Row> rows = r.getRows();
		     result.append("<table style=\"width:400px\">");
		     
		     for (int i = 0; i < rows.size(); i++) {
		         Row row = rows.elementAt(i);
		         result.append("<tr>");
		         for (int k = 0; k < row.getColumnsSize(); k++) {
		           if (i == 0) {
		             result.append("<td style=\"background:silver;font-weight:bold\">" + row.getColumnByIndex(k)
		                 + "</td>");
		           }
		           else {
		             if (i > 1) {
		               result.append("<td style=\"border-top:1px solid silver\">"
		                   + row.getColumnByIndex(k) + "</td>");
		             }
		             else {
		               result.append("<td valign=\"top\">" + row.getColumnByIndex(k) + "</td>");
		             }
		           }
		         }
		         result.append("</tr>");
		       }

		       result.append("</table>");
		       
		       /*
		        * Zum Schluss wird unser Arbeits-Buffer in einen String umgewandelt und der
		        * reportText-Variable zugewiesen. Dadurch wird es möglich, anschließend das
		        * Ergebnis mittels getReportText() auszulesen.
		        */
		       this.reportText = result.toString();
	  }
	  
	  public void processSimpleReport(Report report){
		  
		  SimpleReport r = (SimpleReport)report;
		  
		  //Löschen des Ergebnisses der vorherigen Prozessierung
		  this.resetReportText();
		  

		    /*
		     * In diesen Buffer schreiben wir während der Prozessierung sukzessive
		     * unsere Ergebnisse.
		     */
		  StringBuffer result = new StringBuffer();
		  
		  
		    /*
		     * Nun werden Schritt für Schritt die einzelnen Bestandteile des Reports
		     * ausgelesen und in HTML-Form übersetzt.
		     */
		  	result.append("<H1>" + r.getTitel() + "</H1>");
		  	result.append("<table style=\"width:400px;border:1px solid silver\"><tr>");
		  	result.append("<td valign=\"top\"><b>" + paragraphToHTML(r.getHeader())
	        + "</b></td>");
		  	result.append("</tr><tr><td></td><td>" + r.getErstellungsdatum().toString()
		  	        + "</td></tr></table>");
		  	
		  	
		  	 Vector<Row> rows = r.getRows();
		     result.append("<table style=\"width:400px\">");
		     
		     for (int i = 0; i < rows.size(); i++) {
		         Row row = rows.elementAt(i);
		         result.append("<tr>");
		         for (int k = 0; k < row.getColumnsSize(); k++) {
		           if (i == 0) {
		             result.append("<td style=\"background:silver;font-weight:bold\">" + row.getColumnByIndex(k)
		                 + "</td>");
		           }
		           else {
		             if (i > 1) {
		               result.append("<td style=\"border-top:1px solid silver\">"
		                   + row.getColumnByIndex(k) + "</td>");
		             }
		             else {
		               result.append("<td valign=\"top\">" + row.getColumnByIndex(k) + "</td>");
		             }
		           }
		         }
		         result.append("</tr>");
		       }

		       result.append("</table>");
		       
		       /*
		        * Zum Schluss wird unser Arbeits-Buffer in einen String umgewandelt und der
		        * reportText-Variable zugewiesen. Dadurch wird es möglich, anschließend das
		        * Ergebnis mittels getReportText() auszulesen.
		        */
		       this.reportText = result.toString();
	  }
}


