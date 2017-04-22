package de.hdm.itProjektSS17.server.db;

public class AusschreibungMapper {

	private static AusschreibungMapper ausschreibungMapper = null;
	
	protected AusschreibungMapper(){
	}
	
	  public static AusschreibungMapper ausschreibungMapper() {
		    if (ausschreibungMapper == null) {
		      ausschreibungMapper = new AusschreibungMapper();
		    }

		    return ausschreibungMapper;
		  }
	
	
}
