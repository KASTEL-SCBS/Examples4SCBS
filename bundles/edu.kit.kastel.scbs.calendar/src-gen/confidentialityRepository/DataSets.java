package confidentialityRepository;
public enum DataSets {
	METADATA("_zY5AgFzZEeevtsfSYnjvkw", "metadata"); // TODO: verify data sets.
	
	public final String id;
	public final String name;
	
	 private DataSets(String id, String name) {
		this.id = id;
	    this.name = name;
	}
}
