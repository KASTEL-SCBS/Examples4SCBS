package confidentialityRepository;
public enum ParameterizedDataSetMapEntries {
	EXAMPLE("1234", null /* DataSetMaps.EXAMPLE */, null /* SpecificationParameters.EXAMPLE */); // TODO: verify parameterized data set map entries
	
	public final String id;
	public final DataSetMaps map;
	public final SpecificationParameters parameter;
	
	private ParameterizedDataSetMapEntries(String id, DataSetMaps map, SpecificationParameters parameter) {
		this.id = id;
		this.map = map;
		this.parameter = parameter;
	}
}