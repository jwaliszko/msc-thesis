package traffic_web.model;

import java.util.List;

import traffic_web.logic.Location;
import traffic_web.logic.LocationQuestionsLoader;
import traffic_web.logic.VariousQuestionsLoader;

import traffic_domain.bean.District;
import traffic_domain.bean.PostalCode;
import traffic_domain.bean.Street;

public class BoardModel {

	private VariousQuestionsLoader variousQuestions;
	private LocationQuestionsLoader locationQuestions;
	private List<String> languages;
	private String selectedLanguage;
	private List<PostalCode> postalCodes;
	private List<Street> streets;
	private List<District> districts;
	private Location selectedLocation;
	private String error;
	
	public void setVariousQuestions(VariousQuestionsLoader variousQuestions) {
		this.variousQuestions = variousQuestions;
	}

	public VariousQuestionsLoader getVariousQuestions() {
		return variousQuestions;
	}

	public void setLocationQuestions(LocationQuestionsLoader locationQuestions) {
		this.locationQuestions = locationQuestions;
	}

	public LocationQuestionsLoader getLocationQuestions() {
		return locationQuestions;
	}
	
	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}
	
	public List<String> getLanguages() {
		return languages;
	}

	public void setSelectedLanguage(String selectedLanguage) {
		this.selectedLanguage = selectedLanguage;
	}

	public String getSelectedLanguage() {
		return selectedLanguage;
	}

	public void setPostalCodes(List<PostalCode> postalCodes) {
		this.postalCodes = postalCodes;
	}

	public List<PostalCode> getPostalCodes() {
		return postalCodes;
	}

	public void setStreets(List<Street> streets) {
		this.streets = streets;
	}

	public List<Street> getStreets() {
		return streets;
	}

	public void setDistricts(List<District> districts) {
		this.districts = districts;
	}

	public List<District> getDistricts() {
		return districts;
	}

	public void setSelectedLocation(Location selectedLocation) {
		this.selectedLocation = selectedLocation;
	}

	public Location getSelectedLocation() {
		return selectedLocation;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
	}	
}
