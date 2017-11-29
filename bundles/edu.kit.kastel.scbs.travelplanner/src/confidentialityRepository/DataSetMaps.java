package confidentialityRepository;
public enum DataSetMaps {
    EXAMPLE("DSM1", "data set map 1"); // TODO: verify data set maps
	
	private final String id;
	private final String name;
	
	private DataSetMaps(String id, String name) {
		this.id = id;
		this.name = name;
	}
}
