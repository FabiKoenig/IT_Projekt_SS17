package de.hdm.itProjektSS17.server.db;

public class ProjektMapper {

	
	private static ProjektMapper projektMapper = null;
	
	protected ProjektMapper(){
	}
	
	  public static ProjektMapper projektMapper() {
		    if (projektMapper == null) {
		      projektMapper = new ProjektMapper();
		    }

		    return projektMapper;
		  }
}
