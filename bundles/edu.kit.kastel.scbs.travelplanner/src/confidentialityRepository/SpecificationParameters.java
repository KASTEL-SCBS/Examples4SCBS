package confidentialityRepository;
	public enum SpecificationParameters {
    EXAMPLE("1234", "EXAMPLE", "exampleParameter"); // TODO: verify specification parameters
	
	public final String id;
	public final String name;
	public final String definingServiceParameter;
	
	private SpecificationParameters(String id, String name, String definingServiceParameter) {
		this.id = id;
		this.name = name;
		this.definingServiceParameter = definingServiceParameter;
	}
}