package constants;

import java.util.List;

public class DataList {

	public static String release = null;
	public static String domain = null;
	public static String team = null;
	public static String status = null;
	public static String crname = null;

	public static final List<String> RELEASE_LIST = List.of("23.9", "23.10A");
	public static final List<String> DOMAIN_LIST = List.of("Defects", "Test-cases");
	public static final List<String> TEAM_LIST = List.of("Siebel CRM Dev", "Siebel CRM TA", "OSM Dev", "OSM TA", "UIM Dev", "UIM TA");
	public static final List<String> STATUS_LIST = List.of("Assigned", "Rejected", "Rejected Incomplete", "RTD", "RFR");

	public static void nullifyData() {
		release = null;
		domain = null;
		team = null;
		status = null;
		crname = null;
	}
	
}
