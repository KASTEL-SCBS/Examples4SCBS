package confidentialityRepository;
public enum DataSets {

    /**
     * The mobile phone has to applications: The travel planner used by the user and the credit card
     * center.
     */
	MOBILEPHONE("MobilePhone", "MobilePhone"),

    /**
     * The agency server of the travel agency.
     */
	AGENCYSERVER("AgencyServer", "AgencyServer"),

    /**
     * The server of the airline.
     */
	AIRLINESERVER("AirlineServer", "AirlineServer");
	
	public final String id;
	public final String name;
	
	private DataSets(String id, String name) {
		this.id = id;
	    this.name = name;
	}
}
