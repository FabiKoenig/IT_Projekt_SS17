package de.hdm.itProjektSS17.server.db;

public class BeteiligungMapper {

	private static BeteiligungMapper beteiligungMapper = null;
	
	protected BeteiligungMapper(){
	}
	
	  public static BeteiligungMapper beteiligungMapper() {
		    if (beteiligungMapper == null) {
		      beteiligungMapper = new BeteiligungMapper();
		    }

		    return beteiligungMapper;
		  }
	
}
