package de.hdm.itProjektSS17.server.db;

public class ProjektmarktplatzMapper{

	
	private static ProjektmarktplatzMapper projektmarktplatzMapper = null;
	
	protected ProjektmarktplatzMapper(){
	}
	
	  public static ProjektmarktplatzMapper projektmarktplatzMapper() {
		    if (projektmarktplatzMapper == null) {
		      projektmarktplatzMapper = new ProjektmarktplatzMapper();
		    }

		    return projektmarktplatzMapper;
		  }
}
