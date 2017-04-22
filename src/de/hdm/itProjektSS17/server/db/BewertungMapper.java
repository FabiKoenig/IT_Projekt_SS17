package de.hdm.itProjektSS17.server.db;

public class BewertungMapper {

	private static BewertungMapper bewertungMapper = null;
	
	protected BewertungMapper(){
	}
	
	  public static BewertungMapper bewertungMapper() {
		    if (bewertungMapper == null) {
		      bewertungMapper = new BewertungMapper();
		    }

		    return bewertungMapper;
		  }
}
