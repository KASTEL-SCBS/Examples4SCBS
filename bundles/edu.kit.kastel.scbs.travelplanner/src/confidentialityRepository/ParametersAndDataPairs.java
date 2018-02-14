package confidentialityRepository;
public enum ParametersAndDataPairs {
    getFlightOffersTA(new String[] {"\\result", "requestData"}, new DataSets[] {DataSets.MOBILEPHONE, DataSets.AGENCYSERVER, DataSets.AIRLINESERVER}, null, null),
    getFlightOffersTP(new String[] {"\\result", "requestData"}, new DataSets[] {DataSets.MOBILEPHONE, DataSets.AGENCYSERVER, DataSets.AIRLINESERVER}, null, null),
    declassifyCCDParam(new String[] {"ccDetails"}, new DataSets[] {DataSets.MOBILEPHONE}, null, null),
    declassifiedCCD(new String[] {"\\result"}, new DataSets[] {DataSets.MOBILEPHONE, DataSets.AIRLINESERVER}, null, null),
    confirmRelease(new String[] {"airline", "\\result"}, new DataSets[] {DataSets.MOBILEPHONE}, null, null),
    setCCD(new String[] {"ccd"}, new DataSets[] {DataSets.MOBILEPHONE}, null, null),
    setFlightOffers(new String[] {"flights"}, new DataSets[] {DataSets.MOBILEPHONE, DataSets.AIRLINESERVER, DataSets.AGENCYSERVER}, null, null),
    bookSelectedParam(new String[] {"flightOffer"}, new DataSets[] {DataSets.MOBILEPHONE, DataSets.AIRLINESERVER, DataSets.AGENCYSERVER}, null, null),
    bookSelectedConfirmation(new String[] {"\\result"}, new DataSets[] {DataSets.MOBILEPHONE, DataSets.AIRLINESERVER, DataSets.AGENCYSERVER}, null, null),
    confirmBooking(new String[] {"offerId", "airline", "\\result"}, new DataSets[] {DataSets.MOBILEPHONE, DataSets.AIRLINESERVER, DataSets.AGENCYSERVER}, null, null),
    bookFlightParam0(new String[] {"offerId"}, new DataSets[] {DataSets.MOBILEPHONE, DataSets.AIRLINESERVER, DataSets.AGENCYSERVER}, null, null),
    bookFlightParam1(new String[] {"ccd_decl"}, new DataSets[] {DataSets.MOBILEPHONE, DataSets.AIRLINESERVER}, null, null),
    bookFlightConfirmation(new String[] {"\\result"}, new DataSets[] {DataSets.MOBILEPHONE, DataSets.AIRLINESERVER}, null, null),
    payCommision(new String[] {"offerId", "\\result"}, new DataSets[] {DataSets.AIRLINESERVER, DataSets.AGENCYSERVER}, null, null);

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
