package confidentialityRepository;
public enum ParametersAndDataPairs {
	id2metadata(new String[] {"id"}, new DataSets[] {DataSets.METADATA}, null, null); // TODO: verify parameters and data pairs
	
	public final String[] parameterSources;
	public final DataSets[] dataSets;
	public final DataSetMapEntries[] dataSetMapEntries;
	public final ParameterizedDataSetMapEntries[] parameterizedDataSetMapEntries;
	
	private ParametersAndDataPairs(String[] parameterSources, DataSets[] dataSets, DataSetMapEntries[] dataSetMapEntries, ParameterizedDataSetMapEntries[] parameterizedDataSetMapEntries) {
		this.parameterSources = parameterSources;
		this.dataSets = dataSets;
		this.dataSetMapEntries = dataSetMapEntries;
		this.parameterizedDataSetMapEntries = parameterizedDataSetMapEntries;
	}	
}
