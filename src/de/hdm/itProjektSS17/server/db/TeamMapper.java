package de.hdm.itProjektSS17.server.db;

public class TeamMapper {

	
	private static TeamMapper teamMapper = null;
	
	protected TeamMapper(){
	}
	
	  public static TeamMapper teamMapper() {
		    if (teamMapper == null) {
		      teamMapper = new TeamMapper();
		    }

		    return teamMapper;
		  }
}
