package traffic_web.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

import traffic_web.tools.Assert;
import traffic_web.tools.StringKeyEntryComparator;
import traffic_web.tools.Utility;

import traffic.logic.ClassesNamesProvider;
import traffic.logic.OntologyFacade;

public class LocationQuestionsLoader {
						
	private List<Entry<String, Boolean>> districtLocationDangers;
	private List<Entry<String, Boolean>> streetLocationDangers;	
	private List<Entry<String, Boolean>> postalCodeLocationDangers;
	
	private List<List<Entry<String, Boolean>>> dangersList;
	
	private String districtLocationDangersTitle;
	private String streetLocationDangersTitle;
	private String postalCodeLocationDangersTitle;
	
	public LocationQuestionsLoader()
	{
		dangersList = new ArrayList<List<Entry<String,Boolean>>>();		
	}
	
	public void load(OntologyFacade facade, String lang, Location location)
	{
		initializeDangers(facade, location, lang);
	}			
	
	public List<Entry<String, Boolean>> getDistrictLocationDangers() {
		return districtLocationDangers;
	}
	
	public List<Entry<String, Boolean>> getStreetLocationDangers() {
		return streetLocationDangers;
	}

	public List<Entry<String, Boolean>> getPostalCodeLocationDangers() {
		return postalCodeLocationDangers;
	}	

	public String getDistrictLocationDangersTitle() {
		return districtLocationDangersTitle;
	}
	
	public String getStreetLocationDangersTitle() {
		return streetLocationDangersTitle;
	}	
	
	public String getPostalCodeLocationDangersTitle() {
		return postalCodeLocationDangersTitle;
	}
	
	private void initializeDangers(OntologyFacade facade, Location location, String lang) {
				
		this.districtLocationDangersTitle = Utility.replaceCharsIfPossible(facade.getClassDescription(ClassesNamesProvider.DISTRICT_LOCATION, lang), '.', ':');
		this.streetLocationDangersTitle = Utility.replaceCharsIfPossible(facade.getClassDescription(ClassesNamesProvider.STREET_LOCATION, lang), '.', ':');
		this.postalCodeLocationDangersTitle = Utility.replaceCharsIfPossible(facade.getClassDescription(ClassesNamesProvider.POSTAL_CODE_LOCATION, lang), '.', ':');
		
		this.districtLocationDangers = !Assert.isNullOrEmpty(location.getDistrict()) ? facade.getClassesDescriptionsWithAggregationFlag(facade.getTrafficDangersNamesConnectedWithLocation(location.getDistrict()), lang) : null;
		this.streetLocationDangers = !Assert.isNullOrEmpty(location.getStreet()) ? facade.getClassesDescriptionsWithAggregationFlag(facade.getTrafficDangersNamesConnectedWithLocation(location.getStreet()), lang) : null;	
		this.postalCodeLocationDangers = !Assert.isNullOrEmpty(location.getPostalCode()) ? facade.getClassesDescriptionsWithAggregationFlag(facade.getTrafficDangersNamesConnectedWithLocation(location.getPostalCode()), lang) : null;							
		
		sortCollections();
	}
	
	private void sortCollections()
	{		
		StringKeyEntryComparator comparator = new StringKeyEntryComparator();
		
		dangersList.add(this.districtLocationDangers);
		dangersList.add(this.streetLocationDangers);
		dangersList.add(this.postalCodeLocationDangers);
		
		for(List<Entry<String, Boolean>> current : this.dangersList)
		{
			if(current != null)
			{
				Collections.sort(current, comparator);
			}
		}
	}	
}
