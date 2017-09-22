package confidentialityRepository;
public enum DataSetMapEntries {
    EXAMPLE_A("DSM1_E1", "EXAMPLE[A]", null /* DataSetMaps.EXAMPLE */); // TODO: verify data set map entries
	
	public final String id;
	public final String name;
	public final DataSetMaps map;
	
	private DataSetMapEntries(String id, String name, DataSetMaps map) {
		this.id = id;
		this.name = name;
		this.map = map;
	}
}
